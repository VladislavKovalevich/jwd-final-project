package by.vlad.library.controller.command.impl.client;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class RemoveBookFromOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long bookId = Long.parseLong(request.getParameter(BOOK_ID));
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));

        List<Book> orderBooks = (List<Book>) session.getAttribute(ORDER_BOOKS);
        OrderService orderService = OrderServiceImpl.getInstance();

        try {
            if (orderService.removeBookFromOrder(orderId, bookId)){
                orderBooks.removeIf(book -> book.getId() == bookId);
                session.setAttribute(ORDER_BOOKS, orderBooks);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(PagePath.BOOKS_BY_ORDER_ID_PAGE, Router.Type.FORWARD);
    }
}
