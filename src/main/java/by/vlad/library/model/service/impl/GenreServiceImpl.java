package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Genre;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.GenreDao;
import by.vlad.library.model.dao.impl.GenreDaoImpl;
import by.vlad.library.model.service.GenreService;
import by.vlad.library.validator.GenreValidator;
import by.vlad.library.validator.impl.GenreValidatorImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class GenreServiceImpl implements GenreService {
    private static final String DELIMITER = "\\|";
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

    @Override
    public Optional<Genre> createNewGenre(Map<String, String> mapData) throws ServiceException {
        Optional<Genre> optionalGenre = Optional.empty();
        String genreName = mapData.get(GENRE_NAME_FORM);

        GenreValidator genreValidator = GenreValidatorImpl.getInstance();

        if (!genreValidator.validateGenreName(genreName)){
            mapData.put(WRONG_GENRE_NAME_FORM, GenreValidator.WRONG_FORMAT_MARKER);
            return optionalGenre;
        }

        GenreDao genreDao = GenreDaoImpl.getInstance();

        try {
            if(!genreDao.isGenreExists(genreName)){
                Genre genre = new Genre(genreName);

                optionalGenre = genreDao.addGenre(genre);
            }else{
                mapData.put(WRONG_GENRE_EXISTS_FORM, GenreService.GENRE_EXISTS_MARKER);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return optionalGenre;
    }

    @Override
    public Optional<Genre> updateGenre(Map<String, String> mapData) throws ServiceException {
        GenreValidator genreValidator = GenreValidatorImpl.getInstance();
        Optional<Genre> optionalGenre = Optional.empty();

        String[] genreParams = mapData.get(GENRE_FORM).split(DELIMITER);
        long genreId = Long.parseLong(genreParams[0]);
        String genreName = mapData.get(GENRE_NAME_FORM);

        if (!genreValidator.validateGenreName(genreName)){
            mapData.put(WRONG_GENRE_NAME_FORM, GenreValidator.WRONG_FORMAT_MARKER);
            return optionalGenre;
        }

        GenreDao genreDao = GenreDaoImpl.getInstance();

        try {
            if(!genreDao.isGenreExists(genreName)){
                Genre genre = new Genre(genreId, genreName);
                optionalGenre = genreDao.updateGenre(genre);
            }else{
                mapData.put(WRONG_GENRE_EXISTS_FORM, GenreService.GENRE_EXISTS_MARKER);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return optionalGenre;
    }
}
