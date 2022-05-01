package by.vlad.library.model.dao;

import by.vlad.library.entity.AbstractEntity;
import by.vlad.library.exception.DaoException;

import java.util.List;

public interface BasicDao<T extends AbstractEntity> {
    public abstract boolean insert(T t) throws DaoException;
    public abstract boolean delete(T t) throws DaoException;
    public abstract T update(T t) throws DaoException;
    public abstract List<T> findAll() throws DaoException;
}
