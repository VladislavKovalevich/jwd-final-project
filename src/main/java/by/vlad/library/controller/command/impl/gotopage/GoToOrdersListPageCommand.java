package by.vlad.library.controller.command.impl.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Role;
import by.vlad.library.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ORDERS_BY_USER_ID_PAGE;
import static by.vlad.library.controller.command.PagePath.ORDERS_PAGE;

public class GoToOrdersListPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page;
        Router.Type type = Router.Type.FORWARD;

        Role role = Role.getRole(String.valueOf(session.getAttribute(USER_ROLE)));

        if (role == Role.ADMIN){
            page = ORDERS_PAGE;
        }else{
            page = ORDERS_BY_USER_ID_PAGE;
        }

        session.removeAttribute(ORDER);
        session.removeAttribute(ORDER_BOOKS);

        session.setAttribute(CURRENT_PAGE, page);

        return new Router(page, type);
    }
}
