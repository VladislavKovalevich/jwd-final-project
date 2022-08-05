package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.AttributeAndParamsNames;
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

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class CreateNewAccountCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA);
        Router.Type routerType = Router.Type.REDIRECT;
        String page;

        clearSessionMap(userData);
        fillSessionMap(request, userData);

        UserService userService = UserServiceImpl.getInstance();

        try {
            if(userService.createNewAccount(userData)){
                session.removeAttribute(USER_DATA);
                session.setAttribute(CREATE_ACCOUNT_FEEDBACK, true);
                page = LOGIN_PAGE;
            }else{
                session.setAttribute(USER_DATA, userData);
                page = NEW_ACCOUNT_PAGE;
            }

            session.setAttribute(CURRENT_PAGE, page);
        } catch (ServiceException e) {
            logger.error("CreateNewAccountCommand execution failed");
            throw new CommandException("CreateNewAccountCommand execution failed", e);
        }

        return new Router(page, routerType);
    }

    private void fillSessionMap(HttpServletRequest request, Map<String, String> userFormData){
        userFormData.put(NAME_FORM, request.getParameter(NAME));
        userFormData.put(SURNAME_FORM, request.getParameter(SURNAME));
        userFormData.put(EMAIL_FORM, request.getParameter(AttributeAndParamsNames.EMAIL));
        userFormData.put(PASSWORD_FORM, request.getParameter(AttributeAndParamsNames.PASSWORD));
        userFormData.put(LOGIN_FORM, request.getParameter(LOGIN));
        userFormData.put(SERIAL_NUMBER_FORM, request.getParameter(SERIAL_NUMBER));
        userFormData.put(PHONE_NUMBER_FORM, request.getParameter(PHONE_NUMBER));
        userFormData.put(REPEAT_PASSWORD_FORM, request.getParameter(REPEATED_PASSWORD));
    }

    private void clearSessionMap(Map<String, String> userData) {
        userData.remove(WRONG_EMAIL_FORM);
        userData.remove(WRONG_LOGIN_FORM);
        userData.remove(WRONG_NAME_FORM);
        userData.remove(WRONG_SURNAME_FORM);
        userData.remove(WRONG_PASSWORD_FORM);
        userData.remove(WRONG_PHONE_NUMBER_FORM);
        userData.remove(WRONG_SERIAL_NUMBER_FORM);
        userData.remove(WRONG_REPEAT_PASSWORD_FORM);
        userData.remove(WRONG_EMAIL_EXISTS_FORM);
    }
}
