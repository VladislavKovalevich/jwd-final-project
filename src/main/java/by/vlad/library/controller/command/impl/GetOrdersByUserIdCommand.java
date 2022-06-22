package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Order;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ORDERS_LIST_BY_USER_ID_PAGE;

public class GetOrdersByUserIdCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        OrderService orderService = OrderServiceImpl.getInstance();

        try {
            long userId = (long) session.getAttribute(USER_ID);

            List<Order> orders = orderService.getOrdersByUserId(userId);

            session.setAttribute(ORDERS, orders);
            session.setAttribute(CURRENT_PAGE, ORDERS_LIST_BY_USER_ID_PAGE);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(PagePath.ORDERS_LIST_BY_USER_ID_PAGE, Router.Type.FORWARD);
    }
}
