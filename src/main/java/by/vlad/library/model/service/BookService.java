package by.vlad.library.model.service;

import by.vlad.library.entity.Book;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks(String direction, Map<String, Long> paginationData) throws ServiceException;

    Optional<Book> getBookById(long id) throws ServiceException;

    boolean addBook(Map<String, String> bookData) throws ServiceException;

    Optional<Book> updateBook(Map<String, String> bookData) throws ServiceException;
}
