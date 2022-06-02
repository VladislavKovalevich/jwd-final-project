package by.vlad.library.model.dao;

import by.vlad.library.entity.Order;
import by.vlad.library.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao extends BasicDao<Order> {
    List<Order> findOrdersByUserId(long userId) throws DaoException;

    Order findOrderById(long orderId) throws DaoException;

    boolean insertOrder(long userId, LocalDate createdDate) throws DaoException;
}
