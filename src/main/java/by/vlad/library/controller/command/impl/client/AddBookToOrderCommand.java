package by.vlad.library.controller.command.impl.client;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class AddBookToOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();
        long bookId = Long.parseLong(request.getParameter(BOOK_ID));
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));

        try {
            if (orderService.addBookToOrder(orderId, bookId)){
                request.getSession().setAttribute(BOOK_OPERATION_FEEDBACK, true);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(PagePath.SHOW_BOOK_INFO_PAGE, Router.Type.REDIRECT);
    }
}