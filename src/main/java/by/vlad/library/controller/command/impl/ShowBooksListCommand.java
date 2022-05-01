package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.BookService;
import by.vlad.library.model.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.SHOW_BOOKS_LIST_PAGE;

public class ShowBooksListCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        BookService bookService = BookServiceImpl.getInstance();
        HttpSession session = request.getSession();
        List<Book> bookList;
        Router router;

        Map<String, Long> paginationData = (Map<String, Long>) session.getAttribute(PAGINATION_DATA);
        String direction = request.getParameter(PAGE_DIRECTION);

        if (paginationData == null){
            paginationData = new HashMap<>();
        }

        try {
            bookList = bookService.getAllBooks(direction, paginationData);

            session.setAttribute(PAGINATION_DATA, paginationData);
            session.setAttribute(CURRENT_PAGE, SHOW_BOOKS_LIST_PAGE);

            request.setAttribute(BOOKS_LIST, bookList);

            router = new Router(SHOW_BOOKS_LIST_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }
}
