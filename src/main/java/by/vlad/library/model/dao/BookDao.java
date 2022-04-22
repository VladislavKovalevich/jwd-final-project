package by.vlad.library.model.dao;

import by.vlad.library.entity.Book;
import by.vlad.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    List<Book> getBooks() throws DaoException;
    Optional<Book> getBookById(long id) throws DaoException;
}