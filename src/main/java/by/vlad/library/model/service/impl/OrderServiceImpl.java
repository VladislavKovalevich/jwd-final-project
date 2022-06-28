package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Book;
import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.OrderDao;
import by.vlad.library.model.dao.impl.OrderDaoImpl;
import by.vlad.library.model.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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
    public boolean createOrder(long userId, long bookId, long orderTypeId) throws ServiceException {
        boolean isCreated = false;
        Optional<Long> optionalOrderId;
        LocalDate createdDate = LocalDate.now();

        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            optionalOrderId = orderDao.insertOrder(userId, createdDate, orderTypeId);
            if (optionalOrderId.isPresent() && bookId > 0){
                long orderId = optionalOrderId.get();

                if(orderDao.insertBookToOrder(orderId, bookId)){
                    isCreated = true;
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

        String orderStatusesStr = getStatusString(orderStatuses);//Arrays.toString(orderStatuses);

        try {
            orders = orderDao.findOrdersByStatus(orderStatusesStr);
        } catch (DaoException e) {
            logger.error("Method getOrdersByStatus from order service was failed" + e);
            throw new ServiceException("Method getOrdersByStatus from order service was failed", e);
        }

        return orders;
    }

    @Override
    public boolean changeBooksOrderStatus(long orderId, List<Book> books, long statusId, LocalDate date) throws ServiceException {
        boolean isAccepted;
        OrderDao orderDao = OrderDaoImpl.getInstance();
        LocalDate orderedDate = LocalDate.now();

        StringBuilder booksIdStrBuilder = getBooksIdString(books);
        int listSize = books.size();

        try {
            isAccepted = orderDao.updateStatusAndCopiesNumber(orderId, booksIdStrBuilder, listSize, orderedDate, statusId);
        } catch (DaoException e) {
            logger.error("Method changeBooksOrderStatus from order service was failed" + e);
            throw new ServiceException("Method changeBooksOrderStatus from order service was failed", e);
        }

        return isAccepted;
    }

    @Override
    public boolean addBookToOrder(long orderId, long bookId) throws ServiceException {
        boolean isAdded;
        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            isAdded = orderDao.insertBookToOrder(orderId, bookId);
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
        LocalDate reserveDate = LocalDate.now();

        try {
            isUpdated = orderDao.updateOrderStatus(orderId, reserveDate, statusId);
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

    private StringBuilder getBooksIdString(List<Book> books){
        StringBuilder builder = new StringBuilder();

        for (Book book : books) {
            builder.append(book.getId())
                    .append(',');
        }

        builder.deleteCharAt(builder.length() - 1);

        return builder;
    }
}
