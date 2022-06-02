package by.vlad.library.controller.command.impl.admin.book.book_components;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Author;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.AuthorService;
import by.vlad.library.model.service.impl.AuthorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ADD_BOOK_COMPONENTS_PAGE;
import static by.vlad.library.controller.command.PagePath.UPDATE_BOOK_COMPONENTS_PAGE;

public class UpdateAuthorCommand implements Command {
    private static final String AUTHOR_UPDATED_MARKER = "author has been update";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        List<Author> authors;
        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        fillSessionMap(componentsData, request);
        clearSessionMap(componentsData);

        AuthorService authorService = AuthorServiceImpl.getInstance();

        try {
            authors = authorService.updateAuthor(componentsData);

            if (!authors.isEmpty()){
                componentsData.put(AUTHOR_UPDATED_MSG, AUTHOR_UPDATED_MARKER);
                session.setAttribute(AUTHORS, authors);

                componentsData.remove(AUTHOR_FORM);
                componentsData.remove(AUTHOR_NAME_FORM);
                componentsData.remove(AUTHOR_SURNAME_FORM);
            }

            session.setAttribute(CURRENT_PAGE, UPDATE_BOOK_COMPONENTS_PAGE);
            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(UPDATE_BOOK_COMPONENTS_PAGE, Router.Type.FORWARD);
    }

    private void clearSessionMap(Map<String, String> map){
        map.remove(GENRE_UPDATED_MSG);
        map.remove(PUBLISHER_UPDATED_MSG);
        map.remove(AUTHOR_UPDATED_MSG);
        map.remove(WRONG_AUTHOR_EXISTS_FORM);
        map.remove(WRONG_AUTHOR_NAME_FORM);
        map.remove(WRONG_AUTHOR_SURNAME_FORM);
    }

    private void fillSessionMap(Map<String, String> map, HttpServletRequest request){
        map.put(AUTHOR_FORM, request.getParameter(AUTHOR));
        map.put(AUTHOR_NAME_FORM,request.getParameter(AUTHOR_NAME));
        map.put(AUTHOR_SURNAME_FORM,request.getParameter(AUTHOR_SURNAME));
    }
}
