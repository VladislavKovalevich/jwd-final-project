package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.BookService;
import by.vlad.library.model.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.SHOW_BOOK_INFO_PAGE;

public class ShowBookByIdCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        long id = Long.parseLong(request.getParameter(BOOK_ID));
        Router router;
        BookService bookService = BookServiceImpl.getInstance();

        try {
            Optional<Book> optionalBook = bookService.getBookById(id);
            if (optionalBook.isPresent()){
                Book book = optionalBook.get();
                request.setAttribute(BOOK, book);
            }/*else {
                request.setAttribute("book_info_not_found_msg", "Not found");//заменить на константы
            }*/

            router = new Router(SHOW_BOOK_INFO_PAGE, Router.Type.FORWARD);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }
}
