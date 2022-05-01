package by.vlad.library.model.service;

import by.vlad.library.entity.Author;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface AuthorService {
    List<Author> findAllAuthors() throws ServiceException;

    boolean createNewAuthor(Map<String, String> mapData) throws ServiceException;
}
