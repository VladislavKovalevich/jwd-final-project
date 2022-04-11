package by.vlad.JavaWebProject.model.dao;

import by.vlad.JavaWebProject.entity.Book;
import by.vlad.JavaWebProject.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    List<Book> getBooks() throws DaoException;
    Optional<Book> getBookById(long id) throws DaoException;
}