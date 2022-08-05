package by.vlad.library.controller.command.impl;

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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class UpdateUserAccountDataCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> userFormData = new HashMap<>();
        HttpSession session = request.getSession();
        String page;
        Router.Type routerType = Router.Type.REDIRECT;

        cleanSessionMap(userFormData);
        fillSessionMap(request, userFormData);

        UserService service = UserServiceImpl.getInstance();

        try {
            if(service.updatePersonalData(userFormData)){
                session.removeAttribute(USER_DATA);

                session.setAttribute(USER_LOGIN, userFormData.get(LOGIN_FORM));

                long userId = (long) session.getAttribute(USER_ID);
                Optional<User> user = service.findUserById(userId);

                user.ifPresent(value -> session.setAttribute(USER_INFO, value));

                page = ACCOUNT_PAGE;
            }else{
                session.setAttribute(USER_DATA, userFormData);
                page = CHANGE_ACCOUNT_DATA_PAGE;
            }

            session.setAttribute(CURRENT_PAGE, page);
        } catch (ServiceException e) {
            logger.error("UpdateUserAccountDataCommand execution failed");
            throw new CommandException("UpdateUserAccountDataCommand execution failed", e);
        }

        return new Router(page, routerType);
    }

    private void fillSessionMap(HttpServletRequest request, Map<String, String> userData) {
        userData.put(NAME_FORM, request.getParameter(NAME));
        userData.put(EMAIL_FORM, (String) request.getSession().getAttribute(USER_EMAIL));
        userData.put(SURNAME_FORM, request.getParameter(SURNAME));
        userData.put(LOGIN_FORM, request.getParameter(LOGIN));
        userData.put(SERIAL_NUMBER_FORM, request.getParameter(SERIAL_NUMBER));
        userData.put(PHONE_NUMBER_FORM, request.getParameter(PHONE_NUMBER));
        userData.put(USER_EMAIL, String.valueOf(request.getSession().getAttribute(USER_EMAIL)));
    }

    private void cleanSessionMap(Map<String, String> userData) {
        userData.remove(WRONG_LOGIN_FORM);
        userData.remove(WRONG_NAME_FORM);
        userData.remove(WRONG_SURNAME_FORM);
        userData.remove(WRONG_PHONE_NUMBER_FORM);
        userData.remove(WRONG_SERIAL_NUMBER_FORM);
    }
}
