package by.vlad.library.model.service;

import by.vlad.library.entity.Order;
import by.vlad.library.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByUserId(long userId) throws ServiceException;

    List<Order> createOrder(long userId) throws ServiceException;
}
