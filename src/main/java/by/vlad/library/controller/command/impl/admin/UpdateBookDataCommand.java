package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.BookService;
import by.vlad.library.model.service.ImageService;
import by.vlad.library.model.service.impl.BookServiceImpl;
import by.vlad.library.model.service.impl.ImageServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class UpdateBookDataCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> bookMap = (Map<String, String>) session.getAttribute(BOOK_FORM_DATA);
        Router router;

        clearSessionMap(bookMap);
        fillSessionMap(request, bookMap);

        BookService bookService = BookServiceImpl.getInstance();
        ImageService imageService = ImageServiceImpl.getInstance();

        try {
            Part part = request.getPart(IMAGE);

            List<Book> books = (List<Book>) session.getAttribute(BOOKS_LIST);
            Book temp = (Book) session.getAttribute(BOOK);

            int index = books.indexOf(temp);

            Optional<Book> optionalBook = bookService.updateBook(bookMap);
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                book.setImage(temp.getImage());

                try (InputStream in = part.getInputStream()) {
                    byte[] imageContent = in.readAllBytes();

                    if(imageContent.length > 0) {

                        long imageId = book.getImage().getId();

                        if (imageId > 0) {
                            imageService.updateImage(imageId, imageContent, book);
                        } else {
                            imageService.createNewImage(imageContent, book);
                        }
                    }
                }

                books.set(index, book);

                session.removeAttribute(BOOK_FORM_DATA);

                session.setAttribute(BOOKS_LIST, books);
                session.setAttribute(BOOK, book);
                session.setAttribute(CURRENT_PAGE, SHOW_BOOK_INFO_PAGE);

                router = new Router(SHOW_BOOK_INFO_PAGE, Router.Type.FORWARD);
            } else {
                session.setAttribute(BOOK_FORM_DATA, bookMap);
                session.setAttribute(CURRENT_PAGE, UPDATE_BOOK_DATA_PAGE);

                router = new Router(UPDATE_BOOK_DATA_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException | IOException | ServletException e) {
            logger.error("UpdateBookDataCommand execution failed");
            throw new CommandException("UpdateBookDataCommand execution failed", e);
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
        booksMap.remove(WRONG_TITLE_EXISTS_FORM);
        booksMap.remove(WRONG_TITLE_FORM);
        booksMap.remove(WRONG_COPIES_NUMBER_FORM);
        booksMap.remove(WRONG_RELEASE_YEAR_FORM);
        booksMap.remove(WRONG_PAGES_COUNT_FORM);
        booksMap.remove(WRONG_DESCRIPTION_FORM);
    }
}
