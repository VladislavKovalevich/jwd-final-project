package by.vlad.JavaWebProject.model.dao;

import by.vlad.JavaWebProject.entity.Author;
import by.vlad.JavaWebProject.exception.DaoException;

import java.util.List;

public interface AuthorDAO {
    List<Author> getAuthorsByBookId(long bookId) throws DaoException;
}
