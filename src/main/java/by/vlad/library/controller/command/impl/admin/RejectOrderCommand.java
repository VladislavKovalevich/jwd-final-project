package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class RejectOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        long orderStatusId = OrderStatus.REJECTED.ordinal() + 1;

        OrderService orderService = OrderServiceImpl.getInstance();

        try {
            orderService.changeOrderStatus(orderId, orderStatusId);
        }catch (ServiceException e){
            logger.error("RejectOrderCommand execution failed");
            throw new CommandException("RejectOrderCommand execution failed", e);
        }

        return new Router(PagePath.ORDERS_LIST_PAGE, Router.Type.REDIRECT);
    }
}
