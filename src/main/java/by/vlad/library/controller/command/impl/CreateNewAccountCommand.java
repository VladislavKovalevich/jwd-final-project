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

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class CreateNewAccountCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_FORM_DATA);
        Router router;

        clearSessionMap(userData);
        fillSessionMap(request, userData);

        UserService userService = UserServiceImpl.getInstance();

        try {
            if(userService.createNewAccount(userData)){
                session.removeAttribute(USER_FORM_DATA);
                session.setAttribute(CURRENT_PAGE, MAIN_PAGE);
                router = new Router(MAIN_PAGE, Router.Type.REDIRECT);
            }else{
                request.setAttribute(USER_FORM_DATA, userData);
                router = new Router(CREATE_NEW_ACCOUNT_PAGE, Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
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
