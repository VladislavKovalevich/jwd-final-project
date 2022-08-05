package by.vlad.library.model.service;

import by.vlad.library.entity.Genre;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code GenreService} represent a functional of business logic to work with {@link Genre} class
 */
public interface GenreService {
    /**
     * {@code GENRE_EXISTS_MARKER} constant represent string marker to mark, that genre with some params is already exists
     */
    String GENRE_MAP_MARKER = "genre marker";

    /**
     * Find all genres
     * @return genres list or empty list
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    List<Genre> findAllGenres() throws ServiceException;

    /**
     * Create new genre
     * @param mapData map with new genre data
     * @return object genre boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<Genre> createNewGenre(Map<String, String> mapData) throws ServiceException;

    /**
     * Update genre info
     * @param mapData map with genre data
     * @return object genre boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<Genre> updateGenre(Map<String, String> mapData) throws ServiceException;
}
