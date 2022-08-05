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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.USERS_PAGE;
import static by.vlad.library.controller.command.Router.Type.FORWARD;

public class ShowUsersListCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        List<User> users;

        try {
            users = userService.getAllUsers();
            session.setAttribute(USERS_LIST, users);

            session.setAttribute(CURRENT_PAGE, USERS_PAGE);
        } catch (ServiceException e) {
            logger.error("ShowUsersListCommand execution failed");
            throw new CommandException("ShowUsersListCommand execution failed", e);
        }

        return new Router(USERS_PAGE, FORWARD);
    }
}
