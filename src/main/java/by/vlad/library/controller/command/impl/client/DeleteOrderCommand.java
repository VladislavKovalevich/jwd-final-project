package by.vlad.library.controller.command.impl.client;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ORDERS_BY_USER_ID_PAGE;
import static by.vlad.library.controller.command.Router.Type.FORWARD;

public class DeleteOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        List<Order> orderList = (List<Order>) session.getAttribute(ORDERS);

        OrderService orderService = OrderServiceImpl.getInstance();

        try {
            if (orderService.deleteOrder(orderId)){
                orderList.removeIf(order -> order.getId() == orderId);
                session.setAttribute(ORDERS, orderList);
            }
        } catch (ServiceException e) {
            logger.error("DeleteOrderCommand execution failed");
            throw new CommandException("DeleteOrderCommand execution failed", e);
        }

        return new Router(ORDERS_BY_USER_ID_PAGE, FORWARD);
    }
}
