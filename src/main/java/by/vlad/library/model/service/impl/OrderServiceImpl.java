package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Book;
import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.entity.OrderType;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.OrderDao;
import by.vlad.library.model.dao.impl.OrderDaoImpl;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.util.ConverterToCSVString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

/**
 * {@code OrderServiceImpl} class implements functional of {@link OrderService}
 * @see Order
 * @see OrderService
 */
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger();
    private static OrderServiceImpl instance;

    private static final String PREV_PAGE = "prev";
    private static final String NEXT_PAGE = "next";

    public static OrderServiceImpl getInstance(){
        if (instance == null){
            instance = new OrderServiceImpl();
        }

        return instance;
    }

    private OrderServiceImpl(){}

    @Override
    public List<Order> getOrdersByUserId(long userId) throws ServiceException {
        List<Order> orders;
        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            orders = orderDao.findOrdersByUserId(userId);
        } catch (DaoException e) {
            logger.error("Method getOrdersByUserId from order service was failed" + e);
            throw new ServiceException("Method getOrdersByUserId from order service was failed", e);
        }
        return orders;
    }

    @Override
    public Optional<Order> getOrderInfoById(long orderId) throws ServiceException {
        Optional<Order> optionalOrder;
        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            optionalOrder = orderDao.findOrderById(orderId);
        } catch (DaoException e) {
            logger.error("Method getOrderInfoById from order service was failed" + e);
            throw new ServiceException("Method getOrderInfoById from order service was failed", e);
        }

        return optionalOrder;
    }

    @Override
    public boolean createOrder(long userId, long bookId, long orderTypeId, Map<String, Boolean> orderMap) throws ServiceException {
        boolean isCreated = false;
        Optional<Long> optionalOrderId;
        LocalDate createdDate = LocalDate.now();

        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            if (orderDao.isOrderCountLimit(userId)){
                orderMap.put(ORDERS_LIMIT, true);
                isCreated = false;
            }else {
                optionalOrderId = orderDao.insertOrder(userId, createdDate, orderTypeId);
                if (optionalOrderId.isPresent() && bookId > 0) {
                    long orderId = optionalOrderId.get();

                    if (orderDao.insertBookToOrder(orderId, bookId)) {
                        isCreated = true;
                    }
                }
            }
        } catch (DaoException e) {
            logger.error("Method createOrder from order service was failed" + e);
            throw new ServiceException("Method createOrder from order service was failed", e);
        }

        return isCreated;
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus[] orderStatuses) throws ServiceException {
        List<Order> orders;
        OrderDao orderDao = OrderDaoImpl.getInstance();

        String orderStatusesStr = getStatusString(orderStatuses);

        try {
            orders = orderDao.findOrdersByStatus(orderStatusesStr);
        } catch (DaoException e) {
            logger.error("Method getOrdersByStatus from order service was failed" + e);
            throw new ServiceException("Method getOrdersByStatus from order service was failed", e);
        }

        return orders;
    }

    @Override
    public Optional<Order> changeBooksOrderStatus(Order order, List<Book> books, OrderStatus status, LocalDate date) throws ServiceException {
        Optional<Order> optionalOrder;
        OrderDao orderDao = OrderDaoImpl.getInstance();

        ConverterToCSVString converter = ConverterToCSVString.getInstance();
        String booksIdStrBuilder = converter.convertEntityList(books);
        int listSize = books.size();

        if (status == OrderStatus.ACCEPTED){
            OrderType type = order.getType();
            order.setAcceptedDate(date);
            order.setEstimatedReturnDate(date.plusDays(type.getDays()));
        }else{
            order.setReturnedDate(date);
        }

        order.setStatus(status);

        try {
            optionalOrder = orderDao.updateStatusAndCopiesNumber(order, booksIdStrBuilder, listSize);
        } catch (DaoException e) {
            logger.error("Method changeBooksOrderStatus from order service was failed" + e);
            throw new ServiceException("Method changeBooksOrderStatus from order service was failed", e);
        }

        return optionalOrder;
    }

    @Override
    public boolean addBookToOrder(long orderId, long bookId, Map<String, Boolean> bookMap) throws ServiceException {
        boolean isAdded;
        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            if (orderDao.isLimitOfBooks(orderId)){
                bookMap.put(BOOK_LIMIT_IN_ORDER, true);
                isAdded = false;
            }else {
                if (!orderDao.bookIsAlreadyInOrder(orderId, bookId)) {
                    isAdded = orderDao.insertBookToOrder(orderId, bookId);
                } else {
                    bookMap.put(BOOK_IS_ALREADY_EXISTS, true);
                    isAdded = false;
                }
            }
        } catch (DaoException e) {
            logger.error("Method addBookToOrder from order service was failed" + e);
            throw new ServiceException("Method addBookToOrder from order service was failed", e);
        }

        return isAdded;
    }

    @Override
    public boolean deleteOrder(long orderId) throws ServiceException {
        boolean isDeleted;
        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            isDeleted = orderDao.deleteOrder(orderId);
        } catch (DaoException e) {
            logger.error("Method deleteOrder from order service was failed" + e);
            throw new ServiceException("Method deleteOrder from order service was failed", e);
        }

        return isDeleted;
    }

    @Override
    public boolean removeBookFromOrder(long orderId, long bookId) throws ServiceException {
        boolean isDeleted;
        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            isDeleted = orderDao.deleteBookFromOrder(orderId, bookId);
        } catch (DaoException e) {
            logger.error("Method removeBookFromOrder from order service was failed" + e);
            throw new ServiceException("Method removeBookFromOrder from order service was failed", e);
        }

        return isDeleted;
    }

    @Override
    public boolean checkOverdueOrders(long userId) throws ServiceException {
        boolean isOverflow;
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(OrderService.DAYS_COUNT);

        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            int ordersCount = orderDao.findOverdueOrders(userId, startDate, endDate);
            isOverflow = (ordersCount % OrderService.OVERDUE_ORDERS_COUNT == 0) && (ordersCount > 0);
        } catch (DaoException e) {
            logger.error("Method checkOverdueOrders from order service was failed" + e);
            throw new ServiceException("Method checkOverdueOrders from order service was failed", e);
        }

        return isOverflow;
    }

    @Override
    public List<Order> getUserOrdersByFilters(long userId, String direction, Map<String, Long[]> filterData, Map<String, Long> paginationData) throws ServiceException {
        List<Order> orders;

        try {

            if (direction == null || paginationData.isEmpty()) {
                direction = NEXT_PAGE;
                initOrderPaginationDataMap(userId, paginationData, filterData);
            }

            OrderDao orderDAO = OrderDaoImpl.getInstance();
            long tempPage = paginationData.get(ORDER_CURRENT_PAGE_NUM);

            if (direction.equals(NEXT_PAGE)){
                tempPage++;
            }else{
                tempPage--;
            }

            orders = orderDAO.getUserOrders(userId, tempPage, filterData);

            paginationData.put(ORDER_CURRENT_PAGE_NUM, tempPage);
        } catch (DaoException e) {
            logger.error("Method getAllBooks from book service was failed" + e);
            throw new ServiceException("Method getAllBooks from book service was failed", e);
        }

        return orders;
    }

    private void initOrderPaginationDataMap(long userId, Map<String, Long> paginationMap, Map<String, Long[]> filterMap) throws DaoException {
        long pagesNumber = OrderDaoImpl.getInstance().findNumberOfUserOrdersPage(userId, filterMap);
        paginationMap.put(ORDER_PAGES_NUMBER, pagesNumber);
        paginationMap.put(ORDER_CURRENT_PAGE_NUM, 0L);
    }

    @Override
    public Optional<Order> changeOrderStatus(Order order, OrderStatus status) throws ServiceException {
        Optional<Order> optionalOrder;
        OrderDao orderDao = OrderDaoImpl.getInstance();

        LocalDate operationDate = LocalDate.now();

        if (status == OrderStatus.REJECTED){
            order.setRejectedDate(operationDate);
        }else{
            order.setReservedDate(operationDate);
        }

        order.setStatus(status);

        try {
            optionalOrder = orderDao.updateOrderStatus(order);
        } catch (DaoException e) {
            logger.error("Method changeOrderStatus from order service was failed" + e);
            throw new ServiceException("Method changeOrderStatus from order service was failed", e);
        }

        return optionalOrder;
    }

    private String getStatusString(OrderStatus[] orderStatuses) {
        StringBuilder stringBuilder = new StringBuilder();
        for (OrderStatus status : orderStatuses) {
            String temp = status.toString();
            stringBuilder.append("'")
                    .append(Character.toUpperCase(temp.charAt(0)))
                    .append(temp.substring(1).toLowerCase(Locale.ROOT))
                    .append("',");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }
}
