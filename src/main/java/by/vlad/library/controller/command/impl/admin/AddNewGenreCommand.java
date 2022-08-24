package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Genre;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.GenreService;
import by.vlad.library.model.service.impl.GenreServiceImpl;
import jakarta.servlet.ServletContext;
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

public class AddNewGenreCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        clearSessionMap(componentsData);
        componentsData.put(GENRE_NAME_FORM, request.getParameter(GENRE_NAME));

        GenreService genreService = GenreServiceImpl.getInstance();

        try {
            Optional<Genre> optionalGenre = genreService.createNewGenre(componentsData);
            if (optionalGenre.isPresent()){
                Genre genre = optionalGenre.get();

                List<Genre> genres = (List<Genre>) session.getAttribute(GENRES);
                genres.add(genre);

                session.setAttribute(GENRES, genres);

                componentsData.remove(GENRE_FORM);
                componentsData.remove(GENRE_NAME_FORM);
            }

            session.setAttribute(CURRENT_PAGE, ADD_BOOK_COMPONENTS_PAGE);
            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        } catch (ServiceException e) {
            logger.error("AddNewGenreCommand execution failed");
            throw new CommandException("AddNewGenreCommand execution failed", e);
        }

        return new Router(ADD_BOOK_COMPONENTS_PAGE, REDIRECT);
    }

    private void clearSessionMap(Map<String, String> map){
        map.remove(WRONG_GENRE_NAME_FORM);
        map.remove(WRONG_GENRE_EXISTS_FORM);
    }
}
