package by.vlad.library.controller.command.impl.admin.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Author;
import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.AuthorService;
import by.vlad.library.model.service.PublisherService;
import by.vlad.library.model.service.impl.AuthorServiceImpl;
import by.vlad.library.model.service.impl.PublisherServiceImpl;
import by.vlad.library.controller.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ADD_NEW_BOOK_PAGE;

public class GoToAddNewBookPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> booksData = new HashMap<>();

        PublisherService publisherService = PublisherServiceImpl.getInstance();
        AuthorService authorService = AuthorServiceImpl.getInstance();

        List<Author> authors;
        List<Publisher> publishers;

        try {
            authors = authorService.findAllAuthors();
            publishers = publisherService.findAllPublishers();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        session.setAttribute(PUBLISHERS, publishers);
        session.setAttribute(AUTHORS, authors);
        session.setAttribute(BOOK_FORM_DATA, booksData);
        session.setAttribute(CURRENT_PAGE, CurrentPageExtractor.extract(request));

        return new Router(ADD_NEW_BOOK_PAGE, Router.Type.FORWARD);
    }
}