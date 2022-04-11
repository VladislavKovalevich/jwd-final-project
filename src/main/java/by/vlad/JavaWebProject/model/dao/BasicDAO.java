package by.vlad.JavaWebProject.model.dao;

import by.vlad.JavaWebProject.entity.AbstractEntity;
import by.vlad.JavaWebProject.exception.DaoException;

import java.util.List;

public abstract class BasicDAO<T extends AbstractEntity> {
    public abstract boolean insert(T t) throws DaoException;
    public abstract boolean delete(T t) throws DaoException;
    public abstract T update(T t) throws DaoException;
    public abstract List<T> findAll() throws DaoException;
}
