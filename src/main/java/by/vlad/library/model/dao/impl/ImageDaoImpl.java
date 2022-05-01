package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Image;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.ImageDao;

import java.util.List;

public class ImageDaoImpl implements ImageDao {
    @Override
    public boolean insert(Image image) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Image image) throws DaoException {
        return false;
    }

    @Override
    public Image update(Image image) throws DaoException {
        return null;
    }

    @Override
    public List<Image> findAll() throws DaoException {
        return null;
    }
}
