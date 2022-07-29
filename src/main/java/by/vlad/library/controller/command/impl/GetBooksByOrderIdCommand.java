package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
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

public class GetBooksByOrderIdCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String DELIMITER = ",";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long orderId = Long.parseLong(request.getParameter(ORDER_ID));
        OrderService orderService = OrderServiceImpl.getInstance();
        BookService bookService = BookServiceImpl.getInstance();

        //long orderId = Long.parseLong(orderParams[0]);
        //OrderStatus orderStatus = OrderStatus.getStatus(orderParams[1]);

        try{
            List<Book> books = bookService.getBooksByOrderId(orderId);
            Optional<Order> optionalOrder = orderService.getOrderInfoById(orderId);

            if (optionalOrder.isPresent()){
                session.setAttribute(ORDER, optionalOrder.get());
                session.setAttribute(ORDER_BOOKS, books);
            }

            optionalOrder.ifPresent(order -> session.setAttribute(ORDER, order));
        }catch (ServiceException e){
            logger.error("GetBooksByOrderIdCommand execution failed");
            throw new CommandException("GetBooksByOrderIdCommand execution failed", e);
        }

        return new Router(PagePath.BOOKS_BY_ORDER_ID_PAGE, Router.Type.FORWARD);
    }
}
