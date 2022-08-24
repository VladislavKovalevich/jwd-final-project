package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ORDERS_PAGE;
import static by.vlad.library.controller.command.PagePath.ORDER_INFO_PAGE;
import static by.vlad.library.controller.command.Router.Type.REDIRECT;

public class AcceptOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router;

        Order currentOrder = (Order) session.getAttribute(ORDER);
        OrderStatus orderStatus = OrderStatus.ACCEPTED;

        List<Book> books = (List<Book>) session.getAttribute(ORDER_BOOKS);

        BookService bookService = BookServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();

        try {
            if (bookService.isBooksCopiesExists(books)) {
                LocalDate date = LocalDate.now();

                Optional<Order> optionalOrder = orderService.changeBooksOrderStatus(currentOrder, books, orderStatus, date);

                if (optionalOrder.isPresent()){
                    Order acceptedOrder = optionalOrder.get();
                    List<Order> orders = (List<Order>) session.getAttribute(ORDERS);
                    orders.removeIf(order -> order.getId() == acceptedOrder.getId());
                    orders.add(acceptedOrder);

                    session.setAttribute(ORDERS, orders);
                }

                router = new Router(ORDERS_PAGE, REDIRECT);

                session.removeAttribute(WRONG_BOOK_COPIES_COUNT);
                session.removeAttribute(ORDER);
                session.removeAttribute(ORDER_BOOKS);
            }else{
                session.setAttribute(WRONG_BOOK_COPIES_COUNT, true);

                router = new Router(ORDER_INFO_PAGE, REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("AcceptOrderCommand execution failed");
            throw new CommandException("AcceptOrderCommand execution failed", e);
        }

        return router;
    }
}
