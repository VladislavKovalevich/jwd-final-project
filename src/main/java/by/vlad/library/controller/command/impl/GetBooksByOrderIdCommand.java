package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.BookService;
import by.vlad.library.model.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class GetBooksByOrderIdCommand implements Command {
    private static final String DELIMITER = ",";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String order = request.getParameter(ORDER);
        BookService bookService = BookServiceImpl.getInstance();

        String[] orderParams = order.split(DELIMITER);

        long orderId = Long.parseLong(orderParams[0]);
        OrderStatus orderStatus = OrderStatus.getStatus(orderParams[1]);

        try{
            List<Book> books = bookService.getBooksByOrderId(orderId);
            session.setAttribute(ORDER_STATUS, orderStatus);
            session.setAttribute(ORDER_ID, orderId);
            session.setAttribute(ORDER_BOOKS, books);
        }catch (ServiceException e){
            throw new CommandException(e);
        }

        return new Router(PagePath.BOOKS_BY_ORDER_ID_PAGE, Router.Type.FORWARD);
    }
}
