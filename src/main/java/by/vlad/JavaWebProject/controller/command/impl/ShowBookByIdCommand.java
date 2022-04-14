package by.vlad.JavaWebProject.controller.command.impl;

import by.vlad.JavaWebProject.controller.command.Command;
import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.entity.Book;
import by.vlad.JavaWebProject.exception.CommandException;
import by.vlad.JavaWebProject.exception.ServiceException;
import by.vlad.JavaWebProject.model.service.BookService;
import by.vlad.JavaWebProject.model.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static by.vlad.JavaWebProject.controller.command.AttributeAndParamsNames.*;
import static by.vlad.JavaWebProject.controller.command.PagePath.SHOW_BOOK_INFO_PAGE;

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
            }else {
                request.setAttribute("book_info_not_found_msg", "Not found :)");
            }

            router = new Router(SHOW_BOOK_INFO_PAGE, Router.Type.FORWARD);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }
}
