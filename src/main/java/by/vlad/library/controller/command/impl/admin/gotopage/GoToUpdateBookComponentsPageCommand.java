package by.vlad.library.controller.command.impl.admin.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.controller.util.CurrentPageExtractor;
import by.vlad.library.entity.Author;
import by.vlad.library.entity.Genre;
import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.AuthorService;
import by.vlad.library.model.service.BookService;
import by.vlad.library.model.service.GenreService;
import by.vlad.library.model.service.PublisherService;
import by.vlad.library.model.service.impl.AuthorServiceImpl;
import by.vlad.library.model.service.impl.BookServiceImpl;
import by.vlad.library.model.service.impl.GenreServiceImpl;
import by.vlad.library.model.service.impl.PublisherServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.UPDATE_BOOK_COMPONENTS_PAGE;

public class GoToUpdateBookComponentsPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> componentsData = new HashMap<>();

        session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);

        PublisherService publisherService = PublisherServiceImpl.getInstance();
        AuthorService authorService = AuthorServiceImpl.getInstance();
        GenreService genreService = GenreServiceImpl.getInstance();

        List<Author> authors;
        List<Publisher> publishers;
        List<Genre> genres;

        try {
            authors = authorService.findAllAuthors();
            publishers = publisherService.findAllPublishers();
            genres = genreService.findAllGenres();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        session.setAttribute(PUBLISHERS, publishers);
        session.setAttribute(AUTHORS, authors);
        session.setAttribute(GENRES, genres);

        session.setAttribute(CURRENT_PAGE, CurrentPageExtractor.extract(request));

        return new Router(UPDATE_BOOK_COMPONENTS_PAGE, Router.Type.FORWARD);
    }
}
