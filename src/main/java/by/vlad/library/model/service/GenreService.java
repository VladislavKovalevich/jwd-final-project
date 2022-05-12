package by.vlad.library.model.service;

import by.vlad.library.entity.Genre;
import by.vlad.library.exception.ServiceException;

import java.util.List;

public interface GenreService {
    List<Genre> findAllGenres() throws ServiceException;
}
