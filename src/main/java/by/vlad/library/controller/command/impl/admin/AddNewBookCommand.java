package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Image;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.BookService;
import by.vlad.library.model.service.ImageService;
import by.vlad.library.model.service.impl.BookServiceImpl;
import by.vlad.library.model.service.impl.ImageServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ADD_NEW_BOOK_PAGE;

public class AddNewBookCommand implements Command {
    private static final String BOOK_ADDED_MARKER = "book has been added";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String,String> bookMap = (Map<String, String>) session.getAttribute(BOOK_FORM_DATA);

        clearSessionMap(bookMap);
        fillSessionMap(request, bookMap);

        BookService bookService = BookServiceImpl.getInstance();
        ImageService imageService = ImageServiceImpl.getInstance();

        try {
            Optional<Book> optionalBook = bookService.addBook(bookMap);

            if (optionalBook.isPresent()) {
                Part part = request.getPart(IMAGE);

                if (part != null) {
                    InputStream in = part.getInputStream();
                    byte[] image = in.readAllBytes();
                    imageService.createNewImage(image, optionalBook.get());
                }

                List<Book> books = (List<Book>) session.getAttribute(BOOKS_LIST);
                books.add(optionalBook.get());

                session.setAttribute(BOOKS_LIST, books);

                bookMap.clear();
                bookMap.put(ADD_BOOK_MSG, BOOK_ADDED_MARKER);
            }

            session.setAttribute(CURRENT_PAGE, ADD_NEW_BOOK_PAGE);
            session.setAttribute(BOOK_FORM_DATA, bookMap);

        } catch (ServiceException | ServletException | IOException e) {
            throw new CommandException(e);
        }

        return new Router(ADD_NEW_BOOK_PAGE, Router.Type.REDIRECT);
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
