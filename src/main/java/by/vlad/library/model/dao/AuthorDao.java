package by.vlad.library.model.dao;

import by.vlad.library.entity.Author;
import by.vlad.library.exception.DaoException;

import java.util.Optional;

public interface AuthorDao extends BasicDao<Author>{
    boolean isAuthorExists(String name, String surname) throws DaoException;

    Optional<Author> updateAuthor(Author author) throws DaoException;
}
