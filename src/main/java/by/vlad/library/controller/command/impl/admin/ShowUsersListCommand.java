package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.User;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.USERS_LIST_PAGE;
import static by.vlad.library.controller.command.Router.Type.FORWARD;

public class ShowUsersListCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();

        Map<String, String> paginationData = (Map<String, String>) session.getAttribute(PAGINATION_DATA);

        if (paginationData == null){
            paginationData = new HashMap<>();
        }

        List<User> users;
        Router router;

        try {
            users = userService.getAllUsers();

            session.setAttribute(CURRENT_PAGE, USERS_LIST_PAGE);
            session.setAttribute(PAGINATION_DATA, paginationData);
            request.setAttribute(USERS_LIST, users);

            router = new Router(USERS_LIST_PAGE, FORWARD);
        } catch (ServiceException e) {
            //logg
            throw new CommandException(e);
        }


        return router;
    }
}
