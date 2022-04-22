package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Router;
import by.vlad.library.controller.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.library.controller.command.AttributeAndParamsNames.CURRENT_PAGE;
import static by.vlad.library.controller.command.AttributeAndParamsNames.USER_EMAIL;
import static by.vlad.library.controller.command.PagePath.*;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;

        if (session.getAttribute(USER_EMAIL) != null){
            session.setAttribute(CURRENT_PAGE, HOME_PAGE);
            router = new Router(HOME_PAGE);
        }else{
            session.setAttribute(CURRENT_PAGE, MAIN_PAGE);
            router = new Router(MAIN_PAGE);
        }

        return router;
    }
}