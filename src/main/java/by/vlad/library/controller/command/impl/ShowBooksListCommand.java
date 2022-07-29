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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.SHOW_BOOKS_LIST_PAGE;

public class ShowBooksListCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        BookService bookService = BookServiceImpl.getInstance();
        GenreService genreService = GenreServiceImpl.getInstance();
        PublisherService publisherService = PublisherServiceImpl.getInstance();
        AuthorService authorService = AuthorServiceImpl.getInstance();

        HttpSession session = request.getSession();
        Router router;

        Map<String, Long[]> filterData = (Map<String, Long[]>) session.getAttribute(FILTER_DATA);
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
            session.setAttribute(BOOKS_LIST, bookList);

            session.setAttribute(FILTER_DATA, filterData);
            session.setAttribute(PAGINATION_DATA, paginationData);

            session.setAttribute(CURRENT_PAGE, SHOW_BOOKS_LIST_PAGE);

            router = new Router(SHOW_BOOKS_LIST_PAGE, Router.Type.REDIRECT);
        } catch (ServiceException e) {
            logger.error("ShowBooksListCommand execution failed");
            throw new CommandException("ShowBooksListCommand execution failed", e);
        }

        return router;
    }


    private void convertArrayToCSVString(HttpServletRequest request, String reqParam, Map<String, Long[]> map){
        Enumeration<String> paramEnum = request.getParameterNames();
        List<Long> idList = new ArrayList<>();

        while(paramEnum.hasMoreElements()){
            String s = paramEnum.nextElement();
            if (s.matches(reqParam + "\\d+")){
                idList.add(Long.valueOf(s.split(reqParam)[1]));
            }
        }

        if (!idList.isEmpty()) {
            map.put(reqParam, idList.toArray(new Long[0]));
        }
    }

    private void fillFilterMap(HttpServletRequest request, Map<String, Long[]> map){
        convertArrayToCSVString(request, GENRE, map);
        convertArrayToCSVString(request, AUTHOR, map);
        convertArrayToCSVString(request, PUBLISHER, map);
        String isExists = request.getParameter(IS_EXISTS);

        if (isExists != null){
            map.put(IS_EXISTS, new Long[]{0L});
        }
    }
}
