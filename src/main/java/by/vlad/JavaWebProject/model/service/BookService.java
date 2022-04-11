package by.vlad.JavaWebProject.model.service;

import by.vlad.JavaWebProject.entity.Book;
import by.vlad.JavaWebProject.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getBooks() throws ServiceException;

    Optional<Book> getBookById(long id) throws ServiceException;
}
