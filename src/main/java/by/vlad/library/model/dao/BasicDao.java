package by.vlad.library.model.dao;

import by.vlad.library.entity.AbstractEntity;
import by.vlad.library.exception.DaoException;

import java.util.List;

public interface BasicDao<T extends AbstractEntity> {
    boolean insert(T t) throws DaoException;
    boolean delete(T t) throws DaoException;
    T update(T t) throws DaoException;
    List<T> findAll() throws DaoException;
}
