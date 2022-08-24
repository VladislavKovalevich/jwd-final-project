package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Author;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.AuthorService;
import by.vlad.library.model.service.impl.AuthorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ADD_BOOK_COMPONENTS_PAGE;
import static by.vlad.library.controller.command.Router.Type.REDIRECT;

public class AddNewAuthorCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

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

                componentsData.remove(AUTHOR_NAME_FORM);
                componentsData.remove(AUTHOR_SURNAME_FORM);
            }

            session.setAttribute(CURRENT_PAGE, ADD_BOOK_COMPONENTS_PAGE);
            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);

        } catch (ServiceException e) {
            logger.error("AddNewAuthorCommand execution failed");
            throw new CommandException("AddNewAuthorCommand execution failed", e);
        }

        return new Router(ADD_BOOK_COMPONENTS_PAGE, REDIRECT);
    }

    private void fillSessionMap(Map<String, String> componentsData, HttpServletRequest request) {
        componentsData.put(AUTHOR_NAME_FORM,request.getParameter(AUTHOR_NAME));
        componentsData.put(AUTHOR_SURNAME_FORM,request.getParameter(AUTHOR_SURNAME));
    }

    private void clearSessionMap(Map<String, String> componentsData) {
        componentsData.remove(WRONG_AUTHOR_NAME_FORM);
        componentsData.remove(WRONG_AUTHOR_SURNAME_FORM);
        componentsData.remove(WRONG_AUTHOR_EXISTS_FORM);
    }
}
