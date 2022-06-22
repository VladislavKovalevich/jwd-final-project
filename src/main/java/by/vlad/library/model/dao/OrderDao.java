package by.vlad.library.model.dao;

import by.vlad.library.entity.Order;
import by.vlad.library.exception.DaoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderDao extends BasicDao<Order> {
    List<Order> findOrdersByUserId(long userId) throws DaoException;

    List<Order> findAllOrders() throws DaoException;

    Optional<Long> insertOrder(long userId, LocalDate createdDate, long orderTypeId) throws DaoException;

    boolean insertBookToOrder(long orderId, long bookId) throws DaoException;

    boolean deleteBookFromOrder(long orderId, long bookId) throws DaoException;

    boolean deleteOrder(long orderId) throws DaoException;
}
