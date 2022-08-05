package by.vlad.library.controller.command.impl.client;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.OrderServiceImpl;
import by.vlad.library.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.AttributeAndParamsNames.ORDERS;
import static by.vlad.library.controller.command.PagePath.*;

public class ReturnOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Order currentOrder = (Order) session.getAttribute(ORDER);
        List<Book> books = (List<Book>) session.getAttribute(ORDER_BOOKS);

        OrderService orderService = OrderServiceImpl.getInstance();

        String page = ORDERS_BY_USER_ID_PAGE;

        try {
            LocalDate date = LocalDate.now();

            OrderStatus orderStatus = date.isAfter(currentOrder.getEstimatedReturnDate())
                    ? OrderStatus.OVERDUE
                    : OrderStatus.RETURNED;

            Optional<Order> optionalOrder = orderService.changeBooksOrderStatus(currentOrder, books, orderStatus, date);
            if (optionalOrder.isPresent()) {
                Order updatedOrder = optionalOrder.get();
                List<Order> orders = (List<Order>) session.getAttribute(ORDERS);

                orders.removeIf(order -> order.getId() == updatedOrder.getId());
                orders.add(updatedOrder);

                session.setAttribute(ORDERS, orders);

                long userId = (long) session.getAttribute(USER_ID);
                boolean overdueOrders = orderService.checkOverdueOrders(userId);

                if (overdueOrders && orderStatus == OrderStatus.OVERDUE) {
                    String email = (String) session.getAttribute(USER_EMAIL);

                    UserService userService = UserServiceImpl.getInstance();
                    userService.changeAccountStatus(email, true);

                    session.setAttribute(RETURN_ORDER_BANNED_FLAG, true);

                    page = ORDER_INFO_PAGE;
                }

                session.removeAttribute(ORDER);
                session.removeAttribute(ORDER_BOOKS);

            }
        } catch (ServiceException e) {
            logger.error("ReturnOrderCommand execution failed");
            throw new CommandException("ReturnOrderCommand execution failed", e);
        }


        return new Router(page, Router.Type.REDIRECT);
    }
}
