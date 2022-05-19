package by.vlad.library.controller.command.impl.admin.book.book_components;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Genre;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.GenreService;
import by.vlad.library.model.service.impl.GenreServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class UpdateGenreCommand implements Command {
    private static final String GENRES_UPDATED_MARKER = "genre has been updated";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        request.removeAttribute(GENRE_UPDATED_MSG);

        List<Genre> genres;
        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        removeSessionParams(componentsData);
        initSessionMap(componentsData, request);

        GenreService genreService = GenreServiceImpl.getInstance();

        try {
            genres = genreService.updateGenre(componentsData);

            if (!genres.isEmpty()){
                request.setAttribute(GENRE_UPDATED_MSG, GENRES_UPDATED_MARKER);
                session.setAttribute(GENRES, genres);

                componentsData.remove(GENRE_FORM);
                componentsData.remove(GENRE_NAME_FORM);
            }

            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(PagePath.UPDATE_BOOK_COMPONENTS_PAGE, Router.Type.FORWARD);
    }

    private void removeSessionParams(Map<String, String> map){
        map.remove(WRONG_GENRE_EXISTS_FORM);
        map.remove(WRONG_GENRE_NAME_FORM);
    }

    private void initSessionMap(Map<String, String> map, HttpServletRequest request){
        map.put(GENRE_FORM, request.getParameter(GENRE));
        map.put(GENRE_NAME_FORM,request.getParameter(GENRE_NAME));
    }
}
