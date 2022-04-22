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

import java.util.List;

import static by.vlad.library.controller.command.AttributeAndParamsNames.BOOKS_LIST;
import static by.vlad.library.controller.command.PagePath.SHOW_BOOKS_LIST_PAGE;

public class ShowBooksListCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        BookService bookService = BookServiceImpl.getInstance();
        List<Book> bookList;
        Router router;
        HttpSession session = request.getSession();

        try {
            bookList = bookService.getBooks();
            if (bookList.isEmpty()){
                request.setAttribute("not_found_msg", "Books list is empty");//заменить на константы
            }else{
                request.setAttribute(BOOKS_LIST, bookList);
            }
            router = new Router(SHOW_BOOKS_LIST_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }
}
