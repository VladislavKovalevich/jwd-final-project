package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Author;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.AuthorService;
import by.vlad.library.model.service.impl.AuthorServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ADD_BOOK_COMPONENTS_PAGE;

public class AddNewAuthorCommand implements Command {
    private static final String AUTHOR_ADDED_MARKER = "author has been added";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        clearSessionMap(componentsData);
        fillSessionMap(componentsData, request);

        AuthorService authorService = AuthorServiceImpl.getInstance();

        try {

            Optional<Author> optionalAuthor = authorService.createNewAuthor(componentsData);
            if (optionalAuthor.isPresent()){
                Author author = optionalAuthor.get();

                List<Author> authors = (List<Author>) session.getAttribute(AUTHORS);
                authors.add(author);

                session.setAttribute(AUTHORS, authors);

                componentsData.clear();
                componentsData.put(AUTHOR_OPERATION_FEEDBACK, AUTHOR_ADDED_MARKER);
            }

            session.setAttribute(CURRENT_PAGE, ADD_BOOK_COMPONENTS_PAGE);
            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(ADD_BOOK_COMPONENTS_PAGE, Router.Type.REDIRECT);
    }

    private void fillSessionMap(Map<String, String> componentsData, HttpServletRequest request) {
        componentsData.put(AUTHOR_NAME_FORM,request.getParameter(AUTHOR_NAME));
        componentsData.put(AUTHOR_SURNAME_FORM,request.getParameter(AUTHOR_SURNAME));
    }

    private void clearSessionMap(Map<String, String> componentsData) {
        componentsData.remove(PUBLISHER_OPERATION_FEEDBACK);
        componentsData.remove(GENRE_OPERATION_FEEDBACK);
        componentsData.remove(AUTHOR_OPERATION_FEEDBACK);
        componentsData.remove(WRONG_AUTHOR_NAME_FORM);
        componentsData.remove(WRONG_AUTHOR_SURNAME_FORM);
        componentsData.remove(WRONG_AUTHOR_EXISTS_FORM);
    }
}
