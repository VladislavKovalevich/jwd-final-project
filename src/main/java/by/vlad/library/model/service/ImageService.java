package by.vlad.library.model.service;

import by.vlad.library.entity.Book;
import by.vlad.library.exception.ServiceException;

public interface ImageService {
    void createNewImage(byte[] imageData, Book book) throws ServiceException;
    void updateImage(long imageId, byte[] imageContent, Book book) throws ServiceException;
}
