package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Order;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.OrderDao;
import by.vlad.library.model.dao.impl.OrderDaoImpl;
import by.vlad.library.model.service.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public List<Order> createOrder(long userId) throws ServiceException {
        List<Order> orders;
        LocalDate createdDate = LocalDate.now();

        OrderDao orderDao = OrderDaoImpl.getInstance();

        try {
            if (orderDao.insertOrder(userId, createdDate)){
                orders = orderDao.findOrdersByUserId(userId);
            }else{
                orders = new ArrayList<>();//TODO
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return orders;
    }
}
