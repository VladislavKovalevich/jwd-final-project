package by.vlad.library.controller.command.impl.client;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
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

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class ReserveOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        long orderStatusId = OrderStatus.RESERVED.ordinal() + 1;

        OrderService orderService = OrderServiceImpl.getInstance();

        try {
            if(orderService.changeOrderStatus(orderId, orderStatusId)){
                HttpSession session = request.getSession();
                long userId = (long) session.getAttribute(USER_ID);
                List<Order> orders = orderService.getOrdersByUserId(userId);
                session.setAttribute(ORDERS, orders);
            }
        }catch (ServiceException e){
            logger.error("ReserveOrderCommand execution failed");
            throw new CommandException("ReserveOrderCommand execution failed", e);
        }

        return new Router(PagePath.ORDERS_LIST_BY_USER_ID_PAGE, Router.Type.REDIRECT);
    }
}