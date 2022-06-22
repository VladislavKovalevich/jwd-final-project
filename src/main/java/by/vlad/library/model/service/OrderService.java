package by.vlad.library.model.service;

import by.vlad.library.entity.Order;
import by.vlad.library.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByUserId(long userId) throws ServiceException;

    boolean createOrder(long userId, long bookId, long orderId) throws ServiceException;

    List<Order> getAllOrders() throws ServiceException;

    boolean addBookToOrder(long orderId, long bookId) throws ServiceException;

    boolean deleteOrder(long orderId) throws ServiceException;

    boolean removeBookFromOrder(long orderId, long bookId) throws ServiceException;
}
