package by.vlad.library.model.service;

import by.vlad.library.entity.Author;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.impl.AuthorServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class AuthorServiceImplTest {
    AuthorServiceImpl authorService = AuthorServiceImpl.getInstance();

    @Test
    public void testFindAllAuthors() throws ServiceException {
        List<Author> authorList = authorService.findAllAuthors();
        Assert.assertEquals(authorList.size(), 19);
    }

    @Test
    public void testCreateNewAuthorNegative() throws ServiceException {
        Map<String, String> map = new HashMap<>();
        map.put(AUTHOR_NAME_FORM, "Александр");
        map.put(AUTHOR_SURNAME_FORM, "Дюма");

        Optional<Author> actual = authorService.createNewAuthor(map);
        Assert.assertEquals(actual, Optional.empty());
    }

    @Test
    public void testUpdateAuthorNegative() throws ServiceException {
        Map<String, String> map = new HashMap<>();
        map.put(AUTHOR_FORM, "3|Александр");
        map.put(AUTHOR_NAME_FORM, "Александр");
        map.put(AUTHOR_SURNAME_FORM, "Дюма");

        Optional<Author> actual = authorService.updateAuthor(map);
        Assert.assertEquals(actual, Optional.empty());
    }
}
