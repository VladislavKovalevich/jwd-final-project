package by.vlad.library.controller.command.impl.admin.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Author;
import by.vlad.library.entity.Genre;
import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.AuthorService;
import by.vlad.library.model.service.GenreService;
import by.vlad.library.model.service.PublisherService;
import by.vlad.library.model.service.impl.AuthorServiceImpl;
import by.vlad.library.model.service.impl.GenreServiceImpl;
import by.vlad.library.model.service.impl.PublisherServiceImpl;
import by.vlad.library.controller.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ADD_NEW_BOOK_PAGE;

public class GoToAddNewBookPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> booksData = new HashMap<>();

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
            logger.error("GoToAddNewBookPageCommand execution failed");
            throw new CommandException("GoToAddNewBookPageCommand execution failed", e);
        }

        session.setAttribute(PUBLISHERS, publishers);
        session.setAttribute(AUTHORS, authors);
        session.setAttribute(GENRES, genres);

        session.setAttribute(BOOK_FORM_DATA, booksData);
        session.setAttribute(CURRENT_PAGE, CurrentPageExtractor.extract(request));

        return new Router(ADD_NEW_BOOK_PAGE, Router.Type.FORWARD);
    }
}