package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.CHANGE_PASSWORD_PAGE;
import static by.vlad.library.controller.command.PagePath.HOME_PAGE;

public class ChangeAccountPasswordCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> passwordData;
        Router router;

        passwordData = initPasswordData(request);

        UserService userService = UserServiceImpl.getInstance();

        try {
            if(userService.changePassword(passwordData)){
                router = new Router(HOME_PAGE, Router.Type.FORWARD);
            }else{
                request.setAttribute("error_msg", passwordData.get("error_msg"));
                router = new Router(CHANGE_PASSWORD_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }

    private Map<String, String> initPasswordData(HttpServletRequest request) {
        Map<String, String> passwordData = new HashMap<>();

        passwordData.put(USER_EMAIL, String.valueOf(request.getSession().getAttribute(USER_EMAIL)));
        passwordData.put(PASSWORD, request.getParameter(PASSWORD));
        //passwordData.put(NEW_PASS, request.getParameter(NEW_PASS));
        //passwordData.put(REPEAT_NEW_PASS, request.getParameter(REPEAT_NEW_PASS));

        return passwordData;
    }
}
