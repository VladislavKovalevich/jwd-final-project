package by.vlad.library.model.dao;

import by.vlad.library.entity.Genre;
import by.vlad.library.exception.DaoException;

import java.util.Optional;

public interface GenreDao extends BasicDao<Genre> {
    boolean isGenreExists(String name) throws DaoException;

    Optional<Genre> updateGenre(Genre genre) throws DaoException;
}
