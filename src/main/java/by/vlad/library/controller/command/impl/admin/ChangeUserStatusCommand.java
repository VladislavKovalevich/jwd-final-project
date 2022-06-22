package by.vlad.library.controller.command.impl.admin;

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

public class ChangeUserStatusCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(USER_ID_REQ_PARAM);
        boolean status = Boolean.parseBoolean(request.getParameter(USER_STATUS));
        UserService userService = UserServiceImpl.getInstance();

        try {
            if(userService.changeAccountStatus(email, status)){
                List<User> users;
                users = userService.getAllUsers();
                request.setAttribute(USERS_LIST, users);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new Router(USERS_LIST_PAGE, Router.Type.FORWARD);
    }
}
