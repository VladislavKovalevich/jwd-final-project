package by.vlad.JavaWebProject.model.service;

import by.vlad.JavaWebProject.entity.Author;
import by.vlad.JavaWebProject.exception.ServiceException;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthorsByBookId(long id) throws ServiceException;
}
