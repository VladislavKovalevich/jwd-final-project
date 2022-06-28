package by.vlad.library.controller.command.impl.client;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class AddBookToOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderService orderService = OrderServiceImpl.getInstance();
        long bookId = Long.parseLong(request.getParameter(BOOK_ID));
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));

        try {
            if (orderService.addBookToOrder(orderId, bookId)){
                session.setAttribute(BOOK_OPERATION_FEEDBACK, true);
                session.removeAttribute(ORDER_OPERATION_FEEDBACK);
            }
        } catch (ServiceException e) {
            logger.error("AddBookToOrderCommand execution failed");
            throw new CommandException("AddBookToOrderCommand execution failed", e);
        }

        return new Router(PagePath.SHOW_BOOK_INFO_PAGE, Router.Type.REDIRECT);
    }
}