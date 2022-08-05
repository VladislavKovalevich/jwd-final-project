package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class ChangeAccountPasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> passwordData = (Map<String, String>) session.getAttribute(USER_DATA);
        String page;
        Router.Type routerType = Router.Type.REDIRECT;

        if (passwordData == null){
            passwordData = new HashMap<>();
        }

        clearWrongMessages(passwordData);
        initPasswordData(request, passwordData);

        UserService userService = UserServiceImpl.getInstance();

        try {
            if(userService.changePassword(passwordData)){
                session.removeAttribute(USER_DATA);
            }else{
                session.setAttribute(USER_DATA, passwordData);
            }

            page = CHANGE_PASSWORD_PAGE;
            session.setAttribute(CURRENT_PAGE, page);

        } catch (ServiceException e) {
            logger.error("ChangeAccountPasswordCommand execution failed");
            throw new CommandException("ChangeAccountPasswordCommand execution failed", e);
        }

        return new Router(page, routerType);
    }

    private void initPasswordData(HttpServletRequest request, Map<String, String> passwordData) {
        passwordData.put(USER_EMAIL, String.valueOf(request.getSession().getAttribute(USER_EMAIL)));
        passwordData.put(PASSWORD_FORM, request.getParameter(PASSWORD));
        passwordData.put(NEW_PASSWORD_FORM, request.getParameter(NEW_PASSWORD));
        passwordData.put(NEW_REPEAT_PASSWORD_FORM, request.getParameter(NEW_REPEAT_PASSWORD));
    }

    private void clearWrongMessages(Map<String, String> passwordData){
        passwordData.remove(SUCCESSFULLY_PASSWORD_CHANGE);
        passwordData.remove(WRONG_PASSWORD_FORM);
        passwordData.remove(WRONG_NEW_PASSWORD_FORM);
        passwordData.remove(WRONG_NEW_REPEAT_PASSWORD_FORM);
        passwordData.remove(WRONG_PASSWORD_VALUE);
    }
}
