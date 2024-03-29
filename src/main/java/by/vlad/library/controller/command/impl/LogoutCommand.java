package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Router;
import by.vlad.library.controller.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.library.controller.command.AttributeAndParamsNames.LOCALE;
import static by.vlad.library.controller.command.PagePath.INDEX_PAGE;
import static by.vlad.library.controller.command.Router.Type.REDIRECT;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String string = String.valueOf(session.getAttribute(LOCALE));

        session.invalidate();

        HttpSession newSession = request.getSession();
        newSession.setAttribute(LOCALE, string);

        return new Router(INDEX_PAGE, REDIRECT);
    }
}