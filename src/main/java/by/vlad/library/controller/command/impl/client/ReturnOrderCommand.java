package by.vlad.library.controller.command.impl.client;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.AttributeAndParamsNames.ORDERS;

public class ReturnOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        long orderStatusId = OrderStatus.RETURNED.ordinal() + 1;

        List<Book> books = (List<Book>) session.getAttribute(ORDER_BOOKS);

        OrderService orderService = OrderServiceImpl.getInstance();

        try {
            LocalDate date = LocalDate.now();
            if(orderService.changeBooksOrderStatus(orderId, books, orderStatusId, date)){
                long userId = (long) session.getAttribute(USER_ID);
                List<Order> orders = orderService.getOrdersByUserId(userId);
                session.setAttribute(ORDERS, orders);
            }
        } catch (ServiceException e) {
            logger.error("ReturnOrderCommand execution failed");
            throw new CommandException("ReturnOrderCommand execution failed", e);
        }


        return new Router(PagePath.ORDERS_LIST_BY_USER_ID_PAGE, Router.Type.REDIRECT);
    }
}
