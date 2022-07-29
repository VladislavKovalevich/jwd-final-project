package by.vlad.library.model.dao;

import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.DaoException;

import java.util.Optional;

/**
 * {@code AuthorDao} extends {@link BasicDao} interface and
 *  represent dao functional of the {@link Publisher} class
 * @see BasicDao
 * @see Publisher
 */
public interface PublisherDao extends BasicDao<Publisher> {
    /**
     * Find publisher from database by name
     * @param publisher publisher entity
     * @return true if entity exists, false - if not
     * @throws DaoException if request from database was failed
     */
    boolean isPublisherExists(Publisher publisher) throws DaoException;

    /**
     * Update publisher in database
     * @param publisher publisher entity
     * @return publisher object boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<Publisher> updatePublisher(Publisher publisher) throws DaoException;

    /**
     * Insert publisher in database
     * @param publisher publisher entity
     * @return publisher object boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<Publisher> addPublisher(Publisher publisher) throws DaoException;
}
