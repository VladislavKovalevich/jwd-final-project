package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Author;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.AuthorDao;
import by.vlad.library.model.dao.impl.AuthorDaoImpl;
import by.vlad.library.model.service.AuthorService;
import by.vlad.library.validator.AuthorValidator;
import by.vlad.library.validator.impl.AuthorValidatorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class AuthorServiceImpl implements AuthorService {
    private static final String DELIMITER = "\\|";
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
    public List<Author> updateAuthor(Map<String, String> mapData) throws ServiceException {
        AuthorValidator authorValidator = AuthorValidatorImpl.getInstance();
        List<Author> authors = new ArrayList<>();

        String[] oldAuthorsParams = mapData.get(AUTHOR_FORM).split(DELIMITER);
        long authorId = Long.parseLong(oldAuthorsParams[0]);

        if (!authorValidator.validateAuthorParams(mapData)){
            return authors;
        }

        String name = mapData.get(AUTHOR_NAME_FORM);
        String surname = mapData.get(AUTHOR_SURNAME_FORM);

        AuthorDao authorDao = AuthorDaoImpl.getInstance();

        try {
            if(!authorDao.isAuthorExists(name, surname)){
                Author author = new Author(authorId, name, surname);

                Optional<Author> optionalAuthor = authorDao.updateAuthor(author);

                if (optionalAuthor.isPresent()){
                    authors = authorDao.findAll();
                }
            }else{
                mapData.put(WRONG_AUTHOR_EXISTS_FORM, AUTHOR_EXISTS_MARKER);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return authors;
    }

    @Override
    public boolean createNewAuthor(Map<String, String> mapData) throws ServiceException {
        boolean isCreated = false;

        AuthorValidator authorValidator = AuthorValidatorImpl.getInstance();
        String authorName = mapData.get(AUTHOR_NAME_FORM);
        String authorSurname = mapData.get(AUTHOR_SURNAME_FORM);

        if (!authorValidator.validateAuthorParams(mapData)){
            return isCreated;
        }

        AuthorDaoImpl authorDaoImpl = AuthorDaoImpl.getInstance();

        try {
            if (!authorDaoImpl.isAuthorExists(authorName, authorSurname)) {

                Author author = new Author();
                author.setName(authorName);
                author.setSurname(authorSurname);

                isCreated = authorDaoImpl.insert(author);
            }else{
                mapData.put(WRONG_AUTHOR_EXISTS_FORM, AuthorService.AUTHOR_EXISTS_MARKER);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return isCreated;
    }
}
