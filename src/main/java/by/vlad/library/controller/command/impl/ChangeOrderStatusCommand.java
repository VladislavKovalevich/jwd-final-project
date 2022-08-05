package by.vlad.library.controller.command.impl;

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
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class ChangeOrderStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Order currentOrder = (Order) session.getAttribute(ORDER);
        OrderStatus orderStatus = OrderStatus.getStatus(request.getParameter(ORDER_STATUS));

        OrderService orderService = OrderServiceImpl.getInstance();

        String page;

        if (orderStatus == OrderStatus.RESERVED){
            page = PagePath.ORDERS_BY_USER_ID_PAGE;
        }else{
            page= PagePath.ORDERS_PAGE;
        }

        try {
            Optional<Order> optionalOrder = orderService.changeOrderStatus(currentOrder, orderStatus);
            if (optionalOrder.isPresent()){
                Order updatedOrder = optionalOrder.get();

                List<Order> orders = (List<Order>) session.getAttribute(ORDERS);

                orders.removeIf(order -> order.getId() == updatedOrder.getId());
                orders.add(updatedOrder);

                session.setAttribute(ORDERS, orders);

                session.removeAttribute(ORDER);
                session.removeAttribute(ORDER_BOOKS);
            }
        }catch (ServiceException e){
            logger.error("RejectOrderCommand execution failed");
            throw new CommandException("RejectOrderCommand execution failed", e);
        }

        return new Router(page, Router.Type.REDIRECT);
    }
}
