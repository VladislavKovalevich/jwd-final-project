package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Router;
import by.vlad.library.controller.command.Command;
import by.vlad.library.entity.User;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class LoginCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> userFormData = (Map<String, String>) session.getAttribute(USER_FORM_DATA);

        cleanErrorMessages(userFormData);
        fillUserMap(userFormData, request);

        UserService userService = UserServiceImpl.getInstance();

        Router router;

        try {
            Optional<User> optionalUser = userService.authenticate(userFormData);
            if (optionalUser.isPresent()){
                User user = optionalUser.get();

                session.removeAttribute(USER_FORM_DATA);

                session.setAttribute(USER_EMAIL, user.getEmail());
                session.setAttribute(USER_ROLE, user.getRole().name().toUpperCase(Locale.ROOT));
                session.setAttribute(USER_LOGIN, user.getLogin());
                session.setAttribute(USER_ID, user.getId());

                session.setAttribute(CURRENT_PAGE, HOME_PAGE);

                request.setAttribute(USER_SURNAME, user.getSurname());
                request.setAttribute(USER_NAME, user.getName());

                router = new Router(HOME_PAGE, Router.Type.FORWARD);
            }else{
                session.setAttribute(USER_FORM_DATA, userFormData);
                session.setAttribute(CURRENT_PAGE, LOGIN_PAGE);
                router = new Router(LOGIN_PAGE, Router.Type.FORWARD);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }

    private void fillUserMap(Map<String, String> userFormData, HttpServletRequest request){
        userFormData.put(EMAIL_FORM, request.getParameter(EMAIL));
        userFormData.put(PASSWORD_FORM, request.getParameter(PASSWORD));
    }

    private void cleanErrorMessages(Map<String, String> userFormData){
        userFormData.remove(WRONG_EMAIL_OR_PASS);
        userFormData.remove(NOT_FOUND_USER);
    }
}
