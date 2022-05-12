package by.vlad.library.model.dao;

import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.DaoException;

public interface PublisherDao extends BasicDao<Publisher> {
    boolean isPublisherExists(String publisherName) throws DaoException;

    boolean updatePublisher(String publisherName) throws DaoException;
}
