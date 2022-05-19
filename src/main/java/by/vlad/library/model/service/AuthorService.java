package by.vlad.library.model.service;

import by.vlad.library.entity.Author;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AuthorService {
    String AUTHOR_EXISTS_MARKER = "author with this params is already exists";

    List<Author> findAllAuthors() throws ServiceException;

    List<Author> updateAuthor(Map<String, String> mapData) throws ServiceException;

    boolean createNewAuthor(Map<String, String> mapData) throws ServiceException;
}
