package by.vlad.library.model.service;

import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code PublisherService} represent a functional of business logic to work with {@link Publisher} class
 */
public interface PublisherService {
    /**
     * {@code PUBLISHER_EXISTS_MARKER} constant represent string marker to mark, that publisher with some params is already exists
     */
    String PUBLISHER_MAP_MARKER = "publisher marker";

    /**
     * Find all publishers
     * @return publisher list or empty list
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    List<Publisher> findAllPublishers() throws ServiceException;

    /**
     * Create new publisher
     * @param mapData map with new publisher data
     * @return object publisher boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<Publisher> addNewPublisher(Map<String, String> mapData) throws ServiceException;

    /**
     * Update new publisher
     * @param mapData map with publisher data
     * @return object publisher boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<Publisher> updatePublisher(Map<String, String> mapData) throws ServiceException;
}
