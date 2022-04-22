package by.vlad.library.model.service;

import by.vlad.library.entity.Book;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBooks() throws ServiceException;

    Optional<Book> getBookById(long id) throws ServiceException;
}
