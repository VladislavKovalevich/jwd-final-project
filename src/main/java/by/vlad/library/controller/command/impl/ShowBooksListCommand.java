package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
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

import java.util.*;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.SHOW_BOOKS_LIST_PAGE;

public class ShowBooksListCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        BookService bookService = BookServiceImpl.getInstance();
        GenreService genreService = GenreServiceImpl.getInstance();
        PublisherService publisherService = PublisherServiceImpl.getInstance();
        AuthorService authorService = AuthorServiceImpl.getInstance();

        HttpSession session = request.getSession();
        Router router;

        Map<String, String> filterData = (Map<String, String>) session.getAttribute(FILTER_DATA);
        Map<String, Long> paginationData = (Map<String, Long>) session.getAttribute(PAGINATION_DATA);
        String direction = request.getParameter(PAGE_DIRECTION);

        if (paginationData == null){
            paginationData = new HashMap<>();
        }

        if (filterData == null || direction == null){
            filterData = new HashMap<>();
        }

        fillFilterMap(request, filterData);

        try {
            List<Book> bookList = bookService.getAllBooks(direction, paginationData, filterData);

            session.setAttribute(AUTHORS, authorService.findAllAuthors());
            session.setAttribute(PUBLISHERS, publisherService.findAllPublishers());
            session.setAttribute(GENRES, genreService.findAllGenres());

            session.setAttribute(FILTER_DATA, filterData);
            session.setAttribute(PAGINATION_DATA, paginationData);
            session.setAttribute(CURRENT_PAGE, SHOW_BOOKS_LIST_PAGE);

            session.setAttribute(BOOKS_LIST, bookList);

            router = new Router(SHOW_BOOKS_LIST_PAGE, Router.Type.REDIRECT);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }


    private void convertArrayToCSVString(HttpServletRequest request, String reqParam, Map<String, String> map){
        Enumeration<String> paramEnum = request.getParameterNames();
        List<Long> idList = new ArrayList<>();

        while(paramEnum.hasMoreElements()){
            String s = paramEnum.nextElement();
            if (s.matches(reqParam + "\\d+")){
                idList.add(Long.valueOf(s.split(reqParam)[1]));
            }
        }

        if (!idList.isEmpty()) {
            map.put(reqParam, idList.toString().substring(1, idList.toString().length() - 1));
        }
    }

    private void fillFilterMap(HttpServletRequest request, Map<String, String> map){
        convertArrayToCSVString(request, GENRE, map);
        convertArrayToCSVString(request, AUTHOR, map);
        convertArrayToCSVString(request, PUBLISHER, map);
    }
}
