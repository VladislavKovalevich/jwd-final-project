package by.vlad.library.controller.command.impl.admin.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.BookService;
import by.vlad.library.model.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.UPDATE_BOOK_DATA_PAGE;
import static by.vlad.library.controller.command.Router.Type.FORWARD;

public class GoToUpdateBookDataPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long bookId = Long.parseLong(request.getParameter(BOOK_ID));

        Map<String, String> booksData = new HashMap<>();

        BookService bookService = BookServiceImpl.getInstance();

        Book book;

        try {
            Optional<Book> optionalBook;

            optionalBook = bookService.getBookById(bookId);

            if (optionalBook.isPresent()){
                book = optionalBook.get();

                booksData.put(BOOK_ID, String.valueOf(bookId));
                booksData.put(TITLE_FORM, book.getTitle());
                booksData.put(DESCRIPTION_FORM, book.getDescription());
                booksData.put(COPIES_NUMBER_FORM, String.valueOf(book.getCopiesNumber()));
                booksData.put(PAGES_COUNT_FORM, String.valueOf(book.getNumberOfPages()));
                booksData.put(RELEASE_YEAR_FORM, String.valueOf(book.getReleaseYear()));
                booksData.put(AUTHOR_FORM, String.valueOf(book.getAuthor()));
                booksData.put(PUBLISHER_FORM, String.valueOf(book.getPublisher()));
                booksData.put(GENRE_FORM, String.valueOf(book.getGenre()));

                if (book.getImage() != null) {
                    booksData.put(IMAGE_ID, String.valueOf(book.getImage().getId()));
                }
            }
        } catch (ServiceException e) {
            logger.error("GoToUpdateBookDataPageCommand execution failed");
            throw new CommandException("GoToUpdateBookDataPageCommand execution failed", e);
        }

        session.setAttribute(BOOK_FORM_DATA, booksData);
        session.setAttribute(CURRENT_PAGE, UPDATE_BOOK_DATA_PAGE);

        return new Router(UPDATE_BOOK_DATA_PAGE, FORWARD);
    }
}
