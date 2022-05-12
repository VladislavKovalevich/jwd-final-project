package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Genre;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.GenreDao;
import by.vlad.library.model.dao.impl.GenreDaoImpl;
import by.vlad.library.model.service.GenreService;

import java.util.List;

public class GenreServiceImpl implements GenreService {
    private static GenreServiceImpl instance;

    public static GenreServiceImpl getInstance(){
        if (instance == null){
            instance = new GenreServiceImpl();
        }
        return instance;
    }

    private GenreServiceImpl(){}

    @Override
    public List<Genre> findAllGenres() throws ServiceException {
        List<Genre> genres;

        GenreDao genreDao = GenreDaoImpl.getInstance();

        try {
            genres = genreDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return genres;
    }
}
