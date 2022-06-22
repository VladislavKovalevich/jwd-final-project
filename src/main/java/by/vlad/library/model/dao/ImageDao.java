package by.vlad.library.model.dao;

import by.vlad.library.entity.Image;
import by.vlad.library.exception.DaoException;

public interface ImageDao extends BasicDao<Image> {
    boolean insertImage(Image image, long bookId) throws DaoException;

    boolean updateImage(long imageId, byte[] imageContent) throws DaoException;
}
