package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Router;
import by.vlad.library.controller.command.Command;
import by.vlad.library.entity.User;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.UserServiceImpl;
import by.vlad.library.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.*;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String page;
        Router.Type routerType = Router.Type.REDIRECT;

        Map<String, String> userFormData = (Map<String, String>) session.getAttribute(USER_DATA);
        session.removeAttribute(USER_DATA);

        if (userFormData == null){
            userFormData = new HashMap<>();
        }

        cleanErrorMessages(userFormData);
        fillUserMap(userFormData, request);

        UserService userService = UserServiceImpl.getInstance();

        try {
            Optional<User> optionalUser = userService.authenticate(userFormData);
            if (optionalUser.isPresent()){
                User user = optionalUser.get();

                if (user.isBanned()){
                    userFormData.put(USER_IS_BANNED, UserValidator.WRONG_FORMAT_MARKER);

                    session.setAttribute(USER_DATA, userFormData);
                    page = LOGIN_PAGE;
                }else {
                    session.setAttribute(USER_EMAIL, user.getEmail());
                    session.setAttribute(USER_ROLE, user.getRole().name().toUpperCase(Locale.ROOT));
                    session.setAttribute(USER_LOGIN, user.getLogin());
                    session.setAttribute(USER_ID, user.getId());
                    page = BOOKS_PAGE;

                }
            }else{
                session.setAttribute(USER_DATA, userFormData);
                page = LOGIN_PAGE;
            }

            session.setAttribute(CURRENT_PAGE, page);
        } catch (ServiceException e) {
            logger.error("LoginCommand execution failed");
            throw new CommandException("LoginCommand execution failed", e);
        }

        return new Router(page, routerType);
    }

    private void fillUserMap(Map<String, String> userFormData, HttpServletRequest request){
        userFormData.put(EMAIL_FORM, request.getParameter(EMAIL));
        userFormData.put(PASSWORD_FORM, request.getParameter(PASSWORD));
    }

    private void cleanErrorMessages(Map<String, String> userFormData){
        userFormData.remove(SUCCESSFULLY_PASSWORD_CHANGE);
        userFormData.remove(WRONG_EMAIL_OR_PASS);
        userFormData.remove(NOT_FOUND_USER);
        userFormData.remove(USER_IS_BANNED);
    }
}
