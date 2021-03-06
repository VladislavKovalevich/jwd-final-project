package by.vlad.library.controller.command.impl.client;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.OrderType;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class CreateOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute(USER_ID);
        long bookId = Long.parseLong(request.getParameter(BOOK_ID));
        OrderType orderType = OrderType.getType(request.getParameter(ORDER_TYPE));
        long orderTypeId = orderType.ordinal() + 1;

        OrderService orderService = OrderServiceImpl.getInstance();

        try{
            if(orderService.createOrder(userId, bookId, orderTypeId)) {
                session.setAttribute(ORDER_OPERATION_FEEDBACK, true);
                session.removeAttribute(BOOK_OPERATION_FEEDBACK);
            }
        }catch (ServiceException e){
            logger.error("CreateOrderCommand execution failed");
            throw new CommandException("CreateOrderCommand execution failed", e);
        }

        return new Router(PagePath.SHOW_BOOK_INFO_PAGE, Router.Type.REDIRECT);
    }
}
