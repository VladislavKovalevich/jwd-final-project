package by.vlad.JavaWebProject.controller.command.impl;

import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.controller.command.Command;
import by.vlad.JavaWebProject.entity.User;
import by.vlad.JavaWebProject.exception.CommandException;
import by.vlad.JavaWebProject.exception.ServiceException;
import by.vlad.JavaWebProject.model.service.UserService;
import by.vlad.JavaWebProject.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import java.util.Locale;
import java.util.Optional;

import static by.vlad.JavaWebProject.controller.command.AttributeAndParamsNames.*;
import static by.vlad.JavaWebProject.controller.command.PagePath.*;

public class LoginCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(EMAIL);//TODO константы в отдельный класс
        String password = request.getParameter(PASSWORD);
        UserService userService = UserServiceImpl.getInstance();

        HttpSession session = request.getSession();
        Router router;

        try {
            Optional<User> optionalUser = userService.authenticate(login, password);
            if (optionalUser.isPresent()){
                User user = optionalUser.get();

                session.setAttribute(USER_NAME, user.getName());
                session.setAttribute(USER_SURNAME, user.getSurname());
                session.setAttribute(USER_EMAIL, user.getEmail());
                session.setAttribute(USER_ROLE, user.getRole().name().toUpperCase(Locale.ROOT));
                session.setAttribute(USER_BALANCE, user.getBalance());
                session.setAttribute(CURRENT_PAGE, HOME_PAGE);
                router = new Router(HOME_PAGE, Router.Type.FORWARD);
            }else{
                request.setAttribute("login_msg", "incorrect login or password");
                session.setAttribute(CURRENT_PAGE, LOGIN_PAGE);
                router = new Router(LOGIN_PAGE, Router.Type.FORWARD);
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }
}
