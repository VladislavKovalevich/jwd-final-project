package by.vlad.library.controller.command.impl.gotopage;

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

import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ACCOUNT_PAGE;

public class GoToAccountPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        long userId = (long) session.getAttribute(USER_ID);
        UserService userService = UserServiceImpl.getInstance();

        try {
            User user;
            Optional<User> optionalUser = userService.findUserById(userId);

            if (optionalUser.isPresent()) {
                user = optionalUser.get();

                request.setAttribute(USER_INFO, user);
            }

            session.setAttribute(CURRENT_PAGE, ACCOUNT_PAGE);
        } catch (ServiceException e) {
            logger.error("GoToAccountPageCommand execution failed");
            throw new CommandException("GoToAccountPageCommand execution failed", e);
        }

        return new Router(ACCOUNT_PAGE, Router.Type.FORWARD);
    }
}
