package by.vlad.library.model.service;

import by.vlad.library.entity.Book;
import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getOrdersByUserId(long userId) throws ServiceException;

    Optional<Order> getOrderInfoById(long orderId) throws ServiceException;

    boolean createOrder(long userId, long bookId, long orderId) throws ServiceException;

    List<Order> getOrdersByStatus(OrderStatus[] orderStatuses) throws ServiceException;

    boolean changeBooksOrderStatus(long orderId, List<Book> books, long statusId, LocalDate date) throws ServiceException;

    boolean changeOrderStatus(long orderId, long statusId) throws ServiceException;

    boolean addBookToOrder(long orderId, long bookId) throws ServiceException;

    boolean deleteOrder(long orderId) throws ServiceException;

    boolean removeBookFromOrder(long orderId, long bookId) throws ServiceException;
}
