package by.vlad.library.model.dao;

import by.vlad.library.entity.AbstractEntity;
import by.vlad.library.exception.DaoException;

import java.util.List;

/**
 * {@code BasicDao} interface represent common functional to dao classes
 * @param <T> classes that extends of {@link AbstractEntity}
 */
public interface BasicDao<T extends AbstractEntity> {
    /**
     * Insert entity in database
     * @param t entity extends {@link AbstractEntity}
     * @return true - if entity was inserted, false - if was not
     * @throws DaoException if request from database was failed
     */
    boolean insert(T t) throws DaoException;

    /**
     * Delete entity from database
     * @param t entity extends {@link AbstractEntity}
     * @return true - if entity was deleted, false - if was not
     * @throws DaoException if request from database was failed
     */
    boolean delete(T t) throws DaoException;

    /**
     * Update entity in database
     * @param t entity extends {@link AbstractEntity}
     * @return updated entity
     * @throws DaoException if request from database was failed
     */
    T update(T t) throws DaoException;

    /**
     * Find all entity from database
     * @return entity list from table or empty list if table is empty
     * @throws DaoException if request from database was failed
     */
    List<T> findAll() throws DaoException;
}
