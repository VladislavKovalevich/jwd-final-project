package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
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
import java.util.stream.Collectors;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class AcceptOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router;

        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        long orderStatusId = OrderStatus.ORDERED.ordinal() + 1;

        List<Book> books = (List<Book>) session.getAttribute(ORDER_BOOKS);

        BookService bookService = BookServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();

        try {
            if (bookService.isBooksCopiesExists(books)) {
                LocalDate date = LocalDate.now();
                orderService.changeBooksOrderStatus(orderId, books, orderStatusId, date);

                List<Order> orders = (List<Order>) session.getAttribute(ORDERS);

                orders = orders.stream().peek(order -> {
                    if (order.getId() == orderId){
                        order.setStatus(OrderStatus.ORDERED);
                        order.setOrderedDate(date);
                    }
                }).collect(Collectors.toList());

                session.setAttribute(ORDER, orders);

                router = new Router(PagePath.ORDERS_LIST_PAGE, Router.Type.REDIRECT);
            }else{
                router = new Router(PagePath.BOOKS_BY_ORDER_ID_PAGE, Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("AcceptOrderCommand execution failed");
            throw new CommandException("AcceptOrderCommand execution failed", e);
        }

        return router;
    }
}
