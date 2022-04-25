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

import java.util.HashMap;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class UpdateUserAccountDataCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> userFormData = new HashMap<>();
        HttpSession session = request.getSession();
        Router router;

        cleanWrongMessages(userFormData);
        fillUserDataMap(request, userFormData);

        UserService service = UserServiceImpl.getInstance();

        try {
            if(service.updatePersonalData(userFormData)){
                session.removeAttribute(USER_FORM_DATA);

                session.setAttribute(USER_EMAIL, userFormData.get(EMAIL_FORM));
                session.setAttribute(USER_LOGIN, userFormData.get(LOGIN_FORM));

                session.setAttribute(CURRENT_PAGE, HOME_PAGE);

                router = new Router(HOME_PAGE, Router.Type.FORWARD);
            }else{
                request.setAttribute(USER_FORM_DATA, userFormData);
                router = new Router(CHANGE_ACCOUNT_DATA_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }

    private void fillUserDataMap(HttpServletRequest request, Map<String, String> userData) {
        userData.put(NAME_FORM, request.getParameter(NAME));
        userData.put(SURNAME_FORM, request.getParameter(SURNAME));
        userData.put(EMAIL_FORM, request.getParameter(EMAIL));
        userData.put(LOGIN_FORM, request.getParameter(LOGIN));
        userData.put(SERIAL_NUMBER_FORM, request.getParameter(SERIAL_NUMBER));
        userData.put(PHONE_NUMBER_FORM, request.getParameter(PHONE_NUMBER));
        userData.put(USER_EMAIL, String.valueOf(request.getSession().getAttribute(USER_EMAIL)));
    }

    private void cleanWrongMessages(Map<String, String> userData) {
        userData.remove(WRONG_EMAIL_FORM);
        userData.remove(WRONG_LOGIN_FORM);
        userData.remove(WRONG_NAME_FORM);
        userData.remove(WRONG_SURNAME_FORM);
        userData.remove(WRONG_PHONE_NUMBER_FORM);
        userData.remove(WRONG_SERIAL_NUMBER_FORM);
    }
}
