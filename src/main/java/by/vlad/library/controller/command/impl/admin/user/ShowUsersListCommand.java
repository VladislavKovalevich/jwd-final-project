package by.vlad.library.controller.command.impl.admin.user;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.User;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.USERS_LIST_PAGE;
import static by.vlad.library.controller.command.Router.Type.FORWARD;

public class ShowUsersListCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        List<User> users;

        try {
            users = userService.getAllUsers();
            request.setAttribute(USERS_LIST, users);
        } catch (ServiceException e) {
            //logg
            throw new CommandException(e);
        }

        return new Router(USERS_LIST_PAGE, FORWARD);
    }
}
