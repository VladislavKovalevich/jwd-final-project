package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Order;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.OrderDao;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean insert(Order order) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Order order) throws DaoException {
        return false;
    }

    @Override
    public Order update(Order order) throws DaoException {
        return null;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return null;
    }
}
