package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Author;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.impl.AuthorDaoImpl;
import by.vlad.library.model.service.AuthorService;
import by.vlad.library.validator.AuthorValidator;
import by.vlad.library.validator.impl.AuthorValidatorImpl;

import java.util.List;
import java.util.Map;

public class AuthorServiceImpl implements AuthorService {
    private static AuthorServiceImpl instance;

    public static AuthorServiceImpl getInstance(){
        if (instance == null){
            instance = new AuthorServiceImpl();
        }
        return instance;
    }

    private AuthorServiceImpl(){}

    @Override
    public List<Author> findAllAuthors() throws ServiceException {
        List<Author> authors;
        AuthorDaoImpl authorDaoImpl = AuthorDaoImpl.getInstance();

        try {
            authors = authorDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return authors;
    }

    @Override
    public boolean createNewAuthor(Map<String, String> mapData) throws ServiceException {
        boolean isCreated = false;
        AuthorValidator authorValidator = AuthorValidatorImpl.getInstance();
        String authorName = mapData.get("NEW_AUTHOR_NAME_FORM");
        String authorSurname = mapData.get("NEW_AUTHOR_SURNAME_FORM");

        if (!authorValidator.validateName(authorName) || !authorValidator.validateName(authorSurname)){
            return isCreated;
        }

        Author author = new Author();
        author.setName(authorName);
        author.setSurname(authorSurname);

        AuthorDaoImpl authorDaoImpl = AuthorDaoImpl.getInstance();

        try {
            isCreated = authorDaoImpl.insert(author);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return isCreated;
    }
}
