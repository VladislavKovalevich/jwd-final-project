package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderType;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.BookService;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.model.service.impl.BookServiceImpl;
import by.vlad.library.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.SHOW_BOOK_INFO_PAGE;

public class ShowBookByIdCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long id = Long.parseLong(request.getParameter(BOOK_ID));
        Router router;

        BookService bookService = BookServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();

        try {
            Optional<Book> optionalBook = bookService.getBookById(id);
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                session.setAttribute(BOOK, book);

                if (session.getAttribute(USER_ID) != null) {
                    long userId = (long) session.getAttribute(USER_ID);
                    List<Order> orders = orderService.getOrdersByUserId(userId);
                    OrderType[] orderTypes = OrderType.values();
                    Map<String, Boolean> bookOrderMap = new HashMap<>();

                    session.setAttribute(BOOK_ORDER_DATA, bookOrderMap);
                    session.setAttribute(ORDER_TYPES, orderTypes);
                    session.setAttribute(USER_ORDERS, orders);
                }
            }

            session.setAttribute(CURRENT_PAGE, SHOW_BOOK_INFO_PAGE);

            router = new Router(SHOW_BOOK_INFO_PAGE, Router.Type.FORWARD);

        } catch (ServiceException e) {
            logger.error("ShowBookByIdCommand execution failed");
            throw new CommandException("ShowBookByIdCommand execution failed", e);
        }

        return router;
    }
}
