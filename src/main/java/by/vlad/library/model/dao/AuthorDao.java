package by.vlad.library.model.dao;

import by.vlad.library.entity.Author;
import by.vlad.library.exception.DaoException;

public interface AuthorDao extends BasicDao<Author>{
    boolean isAuthorExists(String name, String surname) throws DaoException;

    boolean updateAuthor(String name, String surname) throws DaoException;
}
