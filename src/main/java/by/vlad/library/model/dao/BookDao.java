package by.vlad.library.model.dao;

import by.vlad.library.entity.Book;
import by.vlad.library.exception.DaoException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookDao extends BasicDao<Book> {
    List<Book> getBooks(long limit, Map<String, String> filterMap) throws DaoException;

    Optional<Book> getBookById(long id) throws DaoException;

    int findNumberOfPage(Map<String, String> filterMap) throws DaoException;

    Optional<Book> addNewBook(Book book) throws DaoException;

    Optional<Book> updateBook(Book book) throws DaoException;

    List<Book> getBooksByOrderId(long orderId) throws DaoException;

    List<Integer> findBooksCopiesNumber(String bookCopiesMap) throws DaoException;
}