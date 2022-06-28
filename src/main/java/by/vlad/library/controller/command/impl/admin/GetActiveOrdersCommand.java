package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
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

import java.util.List;

import static by.vlad.library.controller.command.AttributeAndParamsNames.CURRENT_PAGE;
import static by.vlad.library.controller.command.AttributeAndParamsNames.ORDERS;
import static by.vlad.library.controller.command.PagePath.ORDERS_LIST_PAGE;

public class GetActiveOrdersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderService orderService = OrderServiceImpl.getInstance();
        OrderStatus[] orderStatuses = {OrderStatus.ORDERED, OrderStatus.RESERVED};

        try {
            List<Order> orders =  orderService.getOrdersByStatus(orderStatuses);

            session.setAttribute(ORDERS, orders);
            session.setAttribute(CURRENT_PAGE, ORDERS_LIST_PAGE);
        } catch (ServiceException e) {
            logger.error("GetActiveOrdersCommand execution failed");
            throw new CommandException("GetActiveOrdersCommand execution failed", e);
        }

        return new Router(ORDERS_LIST_PAGE, Router.Type.FORWARD);
    }
}
