package by.vlad.library.model.dao;

import by.vlad.library.entity.Image;
import by.vlad.library.exception.DaoException;

/**
 * {@code BookDao} extends {@link BasicDao} interface and
 *  represent dao functional of the {@link Image} class
 * @see BasicDao
 * @see Image
 */
public interface ImageDao extends BasicDao<Image> {
    /**
     * Insert image in database (insert new image and update books image id)
     * @param image image entity
     * @param bookId book unique identifier
     * @return true if image inserted and book updated, false - if not
     * @throws DaoException if request from database was failed
     */
    boolean insertImage(Image image, long bookId) throws DaoException;

    /**
     * Update image in database
     * @param image image entity
     * @return true if image updated, false - if not
     * @throws DaoException if request from database was failed
     */
    boolean updateImage(Image image) throws DaoException;
}
