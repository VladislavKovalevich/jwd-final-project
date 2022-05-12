package by.vlad.library.model.dao;

import by.vlad.library.entity.Book;
import by.vlad.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookDao extends BasicDao<Book> {
    List<Book> getBooks() throws DaoException;

    Optional<Book> getBookById(long id) throws DaoException;

    int findNumberOfBooks() throws DaoException;

    List<Book> findNextBooks(long lastId) throws DaoException;

    List<Book> findPrevBooks(long firstId) throws DaoException;

    boolean addNewBook(Book book) throws DaoException;

    Optional<Book> updateBook(Book book) throws DaoException;
}