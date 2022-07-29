package by.vlad.library.model.dao;

import by.vlad.library.entity.Author;
import by.vlad.library.exception.DaoException;

import java.util.Optional;

/**
 * {@code AuthorDao} extends {@link BasicDao} interface and
 *  represent dao functional of the {@link Author} class
 * @see BasicDao
 * @see Author
 */
public interface AuthorDao extends BasicDao<Author>{
    /**
     * Find author from database by name and surname
     * @param author author entity
     * @return true if author exists, false - if not
     * @throws DaoException if request from database was failed
     */
    boolean isAuthorExists(Author author) throws DaoException;

    /**
     * Update author in database
     * @param author author entity
     * @return author object boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<Author> updateAuthor(Author author) throws DaoException;

    /**
     * Insert author in database
     * @param author author entity
     * @return author object boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<Author> addAuthor(Author author) throws  DaoException;
}
