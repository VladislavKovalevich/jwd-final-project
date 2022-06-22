package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class ChangeAccountPasswordCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> passwordData;
        HttpSession session = request.getSession();
        Router router;

        passwordData = (Map<String, String>) session.getAttribute(USER_FORM_DATA);

        clearWrongMessages(passwordData);
        initPasswordData(request, passwordData);

        UserService userService = UserServiceImpl.getInstance();

        try {
            if(userService.changePassword(passwordData)){
                session.removeAttribute(USER_FORM_DATA);
                session.setAttribute(CURRENT_PAGE, CHANGE_PASSWORD_PAGE);
                router = new Router(CHANGE_PASSWORD_PAGE, Router.Type.REDIRECT);
            }else{
                session.setAttribute(USER_FORM_DATA, passwordData);
                session.setAttribute(CURRENT_PAGE, CHANGE_PASSWORD_PAGE);
                router = new Router(CHANGE_PASSWORD_PAGE, Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }

    private void initPasswordData(HttpServletRequest request, Map<String, String> passwordData) {
        passwordData.put(USER_EMAIL, String.valueOf(request.getSession().getAttribute(USER_EMAIL)));
        passwordData.put(PASSWORD_FORM, request.getParameter(PASSWORD));
        passwordData.put(NEW_PASSWORD_FORM, request.getParameter(NEW_PASSWORD));
        passwordData.put(NEW_REPEAT_PASSWORD_FORM, request.getParameter(NEW_REPEAT_PASSWORD));
    }

    private void clearWrongMessages(Map<String, String> passwordData){
        passwordData.remove(WRONG_PASSWORD_FORM);
        passwordData.remove(WRONG_NEW_PASSWORD_FORM);
        passwordData.remove(WRONG_NEW_REPEAT_PASSWORD_FORM);
        passwordData.remove(WRONG_PASSWORD_VALUE);
    }
}
