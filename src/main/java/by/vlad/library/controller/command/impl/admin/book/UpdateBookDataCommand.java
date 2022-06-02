package by.vlad.library.controller.command.impl.admin.book;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.BookService;
import by.vlad.library.model.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class UpdateBookDataCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String,String> bookMap = (Map<String, String>) session.getAttribute(BOOK_FORM_DATA);
        Router router;

        clearSessionMap(bookMap);
        fillSessionMap(request, bookMap);

        BookService bookService = BookServiceImpl.getInstance();
        Book book;

        try {
            Optional<Book> optionalBook = bookService.updateBook(bookMap);
            if (optionalBook.isPresent()){
                book = optionalBook.get();

                //session.removeAttribute(GENRES);
                //session.removeAttribute(AUTHORS);
                //session.removeAttribute(PUBLISHERS);
                session.removeAttribute(BOOK_FORM_DATA);

                request.setAttribute(BOOK, book);
                session.setAttribute(CURRENT_PAGE, HOME_PAGE);
                router = new Router(SHOW_BOOK_INFO_PAGE, Router.Type.FORWARD);
            }else{
                session.setAttribute(BOOK_FORM_DATA, bookMap);
                session.setAttribute(CURRENT_PAGE, UPDATE_BOOK_DATA_PAGE);
                router = new Router(UPDATE_BOOK_DATA_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }

    private void fillSessionMap(HttpServletRequest request, Map<String, String> booksMap) {
        booksMap.put(TITLE_FORM, request.getParameter(TITLE));
        booksMap.put(AUTHOR_FORM, request.getParameter(AUTHOR));
        booksMap.put(PUBLISHER_FORM, request.getParameter(PUBLISHER));
        booksMap.put(GENRE_FORM, request.getParameter(GENRE));
        booksMap.put(COPIES_NUMBER_FORM, request.getParameter(COPIES_NUMBER));
        booksMap.put(RELEASE_YEAR_FORM, request.getParameter(RELEASE_YEAR));
        booksMap.put(PAGES_COUNT_FORM, request.getParameter(PAGES_COUNT));
        booksMap.put(DESCRIPTION_FORM, request.getParameter(DESCRIPTION));
    }

    private void clearSessionMap(Map<String, String> booksMap) {
        booksMap.remove(WRONG_TITLE_FORM);
        booksMap.remove(WRONG_COPIES_NUMBER_FORM);
        booksMap.remove(WRONG_RELEASE_YEAR_FORM);
        booksMap.remove(WRONG_PAGES_COUNT_FORM);
        booksMap.remove(WRONG_DESCRIPTION_FORM);
    }
}
