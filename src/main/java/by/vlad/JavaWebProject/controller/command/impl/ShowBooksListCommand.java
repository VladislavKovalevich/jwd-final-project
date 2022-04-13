package by.vlad.JavaWebProject.controller.command.impl;

import by.vlad.JavaWebProject.controller.command.Command;
import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.entity.Book;
import by.vlad.JavaWebProject.exception.CommandException;
import by.vlad.JavaWebProject.exception.ServiceException;
import by.vlad.JavaWebProject.model.service.BookService;
import by.vlad.JavaWebProject.model.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.vlad.JavaWebProject.controller.command.AttributeAndParamsNames.BOOKS_LIST;
import static by.vlad.JavaWebProject.controller.command.PagePath.SHOW_BOOKS_LIST_PAGE;

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
                request.setAttribute("not_found_msg", "Books list is empty");
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
