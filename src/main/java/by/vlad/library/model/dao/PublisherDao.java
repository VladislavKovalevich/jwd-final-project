package by.vlad.library.model.dao;

import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.DaoException;

import java.util.Optional;

public interface PublisherDao extends BasicDao<Publisher> {
    boolean isPublisherExists(String publisherName) throws DaoException;

    Optional<Publisher> updatePublisher(Publisher publisher) throws DaoException;
}
