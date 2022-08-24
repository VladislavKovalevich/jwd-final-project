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

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.Router.Type.REDIRECT;

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

        Map<String, Boolean> bookOrderMap = (Map<String, Boolean>) session.getAttribute(OPERATION_FEEDBACK_MAP_SES);
        bookOrderMap.clear();

        try{
            if(orderService.createOrder(userId, bookId, orderTypeId, bookOrderMap)) {
                session.setAttribute(USER_ORDERS, orderService.getOrdersByUserId(userId));
                bookOrderMap.put(ORDER_OPERATION_FEEDBACK, true);
            }

            session.setAttribute(OPERATION_FEEDBACK_MAP_SES, bookOrderMap);

        }catch (ServiceException e){
            logger.info("CreateOrderCommand execution failed");
            throw new CommandException("CreateOrderCommand execution failed", e);
        }

        return new Router(PagePath.BOOK_INFO_PAGE, REDIRECT);
    }
}
