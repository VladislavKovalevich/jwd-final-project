package by.vlad.library.model.service;

import by.vlad.library.entity.Author;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code AuthorService} represent a functional of business logic to work with {@link Author} class
 */
public interface AuthorService {
    /**
     * {@code AUTHOR_EXISTS_MARKER} constant represent string marker to mark, that author with some params is already exists
     */
    String AUTHOR_MAP_MARKER = "author marker";

    /**
     * Find all authors
     * @return author list or empty list
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    List<Author> findAllAuthors() throws ServiceException;

    /**
     * Update author info
     * @param mapData map with author data
     * @return object author boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<Author> updateAuthor(Map<String, String> mapData) throws ServiceException;

    /**
     * Create new author
     * @param mapData map with new author data
     * @return object author boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<Author> createNewAuthor(Map<String, String> mapData) throws ServiceException;
}