package by.vlad.library.controller.command.impl.admin.book.book_components;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.GenreService;
import by.vlad.library.model.service.impl.GenreServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ADD_BOOK_COMPONENTS_PAGE;

public class AddNewGenreCommand implements Command {

    private static final String GENRE_ADDED_MARKER = "genre has been added";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        clearSessionMap(componentsData);
        componentsData.put(GENRE_NAME_FORM, request.getParameter(GENRE_NAME));

        GenreService genreService = GenreServiceImpl.getInstance();

        try {
            if (genreService.createNewGenre(componentsData)){
                componentsData.remove(GENRE_NAME_FORM);
                componentsData.put(GENRE_ADDED_MSG, GENRE_ADDED_MARKER);
            }

            session.setAttribute(CURRENT_PAGE, ADD_BOOK_COMPONENTS_PAGE);
            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(ADD_BOOK_COMPONENTS_PAGE, Router.Type.REDIRECT);
    }

    private void clearSessionMap(Map<String, String> map){
        map.remove(PUBLISHER_ADDED_MSG);
        map.remove(GENRE_ADDED_MSG);
        map.remove(AUTHOR_ADDED_MSG);
        map.remove(WRONG_GENRE_NAME_FORM);
        map.remove(WRONG_GENRE_EXISTS_FORM);
    }
}
