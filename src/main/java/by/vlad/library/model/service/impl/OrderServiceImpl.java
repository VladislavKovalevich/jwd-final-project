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
    public boolean changeBooksOrderStatus(long orderId, OrderType orderType, List<Book> books, long statusId, LocalDate date) throws ServiceException {
        boolean isAccepted;
        OrderDao orderDao = OrderDaoImpl.getInstance();
        LocalDate operationDate = LocalDate.now();
        ConverterToCSVString converter = ConverterToCSVString.getInstance();

        String booksIdStrBuilder = converter.convertEntityList(books);
        int listSize = books.size();

        try {
            isAccepted = orderDao.updateStatusAndCopiesNumber(orderId, orderType, booksIdStrBuilder, listSize, operationDate, statusId);
        } catch (DaoException e) {
            logger.error("Method changeBooksOrderStatus from order service was failed" + e);
            throw new ServiceException("Method changeBooksOrderStatus from order service was failed", e);
        }

        return isAccepted;
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
    public boolean changeOrderStatus(long orderId, long statusId) throws ServiceException {
        boolean isUpdated;
        OrderDao orderDao = OrderDaoImpl.getInstance();
        LocalDate operationDate = LocalDate.now();

        try {
            isUpdated = orderDao.updateOrderStatus(orderId, operationDate, statusId);
        } catch (DaoException e) {
            logger.error("Method changeOrderStatus from order service was failed" + e);
            throw new ServiceException("Method changeOrderStatus from order service was failed", e);
        }

        return isUpdated;
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
