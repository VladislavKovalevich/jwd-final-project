package by.vlad.library.controller.command.impl.client.order;

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

public class CreateOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute(USER_ID);

        OrderService orderService = OrderServiceImpl.getInstance();

        try{
            List<Order> orders =  orderService.createOrder(userId);
            request.setAttribute(ORDERS, orders);
            session.setAttribute(CURRENT_PAGE, PagePath.ORDERS_LIST_BY_USER_ID);
        }catch (ServiceException e){
            throw new CommandException(e);
        }

        return new Router(PagePath.ORDERS_LIST_BY_USER_ID, Router.Type.REDIRECT);
    }
}
