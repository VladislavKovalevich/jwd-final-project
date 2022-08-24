package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.controller.util.RequestStringConverter;
import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.entity.OrderType;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.OrderService;
import by.vlad.library.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ORDERS_BY_USER_ID_PAGE;

public class GetOrdersByUserIdCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, Long[]> filterData = (Map<String, Long[]>) session.getAttribute(FILTER_DATA);
        Map<String, Long> paginationData = (Map<String, Long>) session.getAttribute(PAGINATION_DATA);
        String direction = request.getParameter(ORDER_PAGE_DIRECTION);

        Router.Type type = Router.Type.REDIRECT;

        if (paginationData == null) {
            paginationData = new HashMap<>();
        }

        if (filterData == null || direction == null) {
            filterData = new HashMap<>();
        }

        if (direction == null){
            type = Router.Type.FORWARD;
        }

        fillOrderFilterMap(request, filterData);

        OrderService orderService = OrderServiceImpl.getInstance();

        try {
            long userId = (long) session.getAttribute(USER_ID);

            List<Order> orders = orderService.getUserOrdersByFilters(userId, direction, filterData, paginationData);

            if (session.getAttribute(ORDER_STATUSES) == null) {
                OrderStatus[] orderStatus = OrderStatus.values();
                session.setAttribute(ORDER_STATUSES, orderStatus);
            }

            if (session.getAttribute(ORDER_TYPES) == null) {
                OrderType[] orderType = OrderType.values();
                session.setAttribute(ORDER_TYPES, orderType);
            }

            session.setAttribute(FILTER_DATA, filterData);
            session.setAttribute(PAGINATION_DATA, paginationData);

            session.setAttribute(ORDERS, orders);
            session.setAttribute(CURRENT_PAGE, ORDERS_BY_USER_ID_PAGE);

        } catch (ServiceException e) {
            logger.error("GetOrdersByUserIdCommand execution failed");
            throw new CommandException("GetOrdersByUserIdCommand execution failed", e);
        }

        return new Router(PagePath.ORDERS_BY_USER_ID_PAGE, type);
    }

    private void fillOrderFilterMap(HttpServletRequest request, Map<String, Long[]> map) {
        RequestStringConverter converter = RequestStringConverter.getInstance();
        converter.convertStringToIdArray(request, ORDER_TYPE, map);
        converter.convertStringToIdArray(request, ORDER_STATUS, map);

        if (request.getParameter(SORT_PARAM) != null) {
            map.put(ORDER_SORT_PARAM, new Long[]{Long.valueOf(request.getParameter(SORT_PARAM))});
        }else{
            map.put(ORDER_SORT_PARAM, new Long[]{1L});
        }

        if (request.getParameter(SORT_TYPE) != null) {
            map.put(ORDER_SORT_TYPE, new Long[]{Long.valueOf(request.getParameter(SORT_TYPE))});
        }else{
            map.put(ORDER_SORT_TYPE, new Long[]{1L});
        }
    }
}
