package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Genre;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.GenreService;
import by.vlad.library.model.service.impl.GenreServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.UPDATE_BOOK_COMPONENTS_PAGE;
import static by.vlad.library.controller.command.Router.Type.REDIRECT;

public class UpdateGenreCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        clearSessionMap(componentsData);
        fillSessionMap(componentsData, request);

        GenreService genreService = GenreServiceImpl.getInstance();

        try {
            Optional<Genre> optionalGenre = genreService.updateGenre(componentsData);

            if (optionalGenre.isPresent()){
                Genre genre = optionalGenre.get();

                List<Genre> genres = (List<Genre>) session.getAttribute(GENRES);
                genres.removeIf(g -> g.getId() == genre.getId());
                genres.add(genre);

                session.setAttribute(GENRES, genres);

                componentsData.remove(GENRE_FORM);
                componentsData.remove(GENRE_NAME_FORM);
            }

            session.setAttribute(CURRENT_PAGE, UPDATE_BOOK_COMPONENTS_PAGE);
            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        } catch (ServiceException e) {
            logger.error("UpdateGenreCommand execution failed");
            throw new CommandException("UpdateGenreCommand execution failed", e);
        }

        return new Router(UPDATE_BOOK_COMPONENTS_PAGE, REDIRECT);
    }

    private void clearSessionMap(Map<String, String> map){
        map.remove(WRONG_GENRE_EXISTS_FORM);
        map.remove(WRONG_GENRE_NAME_FORM);
    }

    private void fillSessionMap(Map<String, String> map, HttpServletRequest request){
        map.put(GENRE_FORM, request.getParameter(GENRE));
        map.put(GENRE_NAME_FORM,request.getParameter(GENRE_NAME));
    }
}
