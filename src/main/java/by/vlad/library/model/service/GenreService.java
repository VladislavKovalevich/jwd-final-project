package by.vlad.library.model.service;

import by.vlad.library.entity.Genre;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GenreService {
    String GENRE_EXISTS_MARKER = "genre with this params is already exists";

    List<Genre> findAllGenres() throws ServiceException;

    Optional<Genre> createNewGenre(Map<String, String> mapData) throws ServiceException;

    Optional<Genre> updateGenre(Map<String, String> mapData) throws ServiceException;
}
