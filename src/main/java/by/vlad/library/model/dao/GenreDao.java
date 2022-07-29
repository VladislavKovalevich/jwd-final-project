package by.vlad.library.model.dao;

import by.vlad.library.entity.Genre;
import by.vlad.library.exception.DaoException;

import java.util.Optional;

/**
 * {@code AuthorDao} extends {@link BasicDao} interface and
 *  represent dao functional of the {@link Genre} class
 * @see BasicDao
 * @see Genre
 */
public interface GenreDao extends BasicDao<Genre> {
    /**
     * Find genre from database by name
     * @param genre genre entity
     * @return true if entity exists, false - if not
     * @throws DaoException if request from database was failed
     */
    boolean isGenreExists(Genre genre) throws DaoException;

    /**
     * Update genre in database
     * @param genre genre entity
     * @return genre object boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<Genre> updateGenre(Genre genre) throws DaoException;

    /**
     * Insert genre in database
     * @param genre genre entity
     * @return genre object boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<Genre> addGenre(Genre genre) throws DaoException;
}
