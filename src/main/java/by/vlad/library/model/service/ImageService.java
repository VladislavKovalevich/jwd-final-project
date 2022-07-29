package by.vlad.library.model.service;

import by.vlad.library.entity.Book;
import by.vlad.library.exception.ServiceException;

/**
 * {@code ImageService} represent a functional of business logic to work with {@link by.vlad.library.entity.Image} class
 */
public interface ImageService {
    /**
     * Create image
     * @param imageData map with image data
     * @param book book, that will be associated with image
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    void createNewImage(byte[] imageData, Book book) throws ServiceException;

    /**
     * Update image
     * @param imageId image identifier
     * @param imageContent image content as byte array
     * @param book book, that associated with image
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    void updateImage(long imageId, byte[] imageContent, Book book) throws ServiceException;
}