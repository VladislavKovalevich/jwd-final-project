package by.vlad.JavaWebProject.model.service.impl;

import by.vlad.JavaWebProject.entity.Author;
import by.vlad.JavaWebProject.exception.DaoException;
import by.vlad.JavaWebProject.exception.ServiceException;
import by.vlad.JavaWebProject.model.dao.AuthorDAO;
import by.vlad.JavaWebProject.model.dao.impl.AuthorDAOImpl;
import by.vlad.JavaWebProject.model.service.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private static AuthorServiceImpl instance;

    private AuthorServiceImpl(){}

    public static AuthorServiceImpl getInstance() {
        if (instance == null){
            instance = new AuthorServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Author> getAuthorsByBookId(long bookId) throws ServiceException {
        List<Author> authors;
        AuthorDAO authorDAO = AuthorDAOImpl.getInstance();

        try {
            authors = authorDAO.getAuthorsByBookId(bookId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return authors;
    }
}
