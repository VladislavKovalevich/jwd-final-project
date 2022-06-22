package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Order;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.OrderDao;
import by.vlad.library.model.dao.impl.OrderDaoImpl;
import by.vlad.library.model.service.OrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
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
            throw new ServiceException(e);
        }
        return orders;
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
            throw new ServiceException(e);
        }

        return isCreated;
    }

    @Override
    public List<Order> getAllOrders() throws ServiceException {
        List<Order> orders;
        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            orders = orderDao.findAllOrders();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return orders;
    }

    @Override
    public boolean addBookToOrder(long orderId, long bookId) throws ServiceException {
        boolean isAdded;
        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            isAdded = orderDao.insertBookToOrder(orderId, bookId);
        } catch (DaoException e) {
            throw new ServiceException(e);
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
            throw new ServiceException(e);
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
            throw new ServiceException(e);
        }

        return isDeleted;
    }
}
