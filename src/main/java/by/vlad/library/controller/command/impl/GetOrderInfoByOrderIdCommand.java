package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Order;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.BookService;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.model.service.impl.BookServiceImpl;
import by.vlad.library.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ORDER_INFO_PAGE;
import static by.vlad.library.controller.command.Router.Type.FORWARD;

public class GetOrderInfoByOrderIdCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        OrderService orderService = OrderServiceImpl.getInstance();
        BookService bookService = BookServiceImpl.getInstance();

        try{
            List<Book> books = bookService.getBooksByOrderId(orderId);
            Optional<Order> optionalOrder = orderService.getOrderInfoById(orderId);

            if (optionalOrder.isPresent()){
                session.setAttribute(ORDER, optionalOrder.get());
                session.setAttribute(ORDER_BOOKS, books);
            }

            session.setAttribute(CURRENT_PAGE, ORDER_INFO_PAGE);
        }catch (ServiceException e){
            logger.error("GetBooksByOrderIdCommand execution failed");
            throw new CommandException("GetBooksByOrderIdCommand execution failed", e);
        }

        return new Router(ORDER_INFO_PAGE, FORWARD);
    }
}
