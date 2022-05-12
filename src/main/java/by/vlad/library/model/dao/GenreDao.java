package by.vlad.library.model.dao;

import by.vlad.library.entity.Genre;
import by.vlad.library.exception.DaoException;

public interface GenreDao extends BasicDao<Genre> {
    boolean isGenreExists(String name) throws DaoException;

    boolean updateGenre(String name) throws DaoException;
}
