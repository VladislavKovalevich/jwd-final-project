package by.vlad.library.controller.command.impl.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Role;
import by.vlad.library.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.library.controller.command.AttributeAndParamsNames.USER_ROLE;

public class GoToOrdersListPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router;
        Role role = Role.getRole(String.valueOf(session.getAttribute(USER_ROLE)));

        if (role == Role.ADMIN){
            router = new Router(PagePath.ORDERS_LIST_PAGE, Router.Type.REDIRECT);
        }else{
            router = new Router(PagePath.ORDERS_LIST_BY_USER_ID_PAGE, Router.Type.REDIRECT);
        }

        return router;
    }
}
