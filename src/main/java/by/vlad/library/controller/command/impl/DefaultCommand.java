package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Router;
import by.vlad.library.controller.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.library.controller.command.AttributeAndParamsNames.CURRENT_PAGE;
import static by.vlad.library.controller.command.PagePath.*;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;

        session.setAttribute(CURRENT_PAGE, SHOW_BOOKS_LIST_PAGE);
        router = new Router(SHOW_BOOKS_LIST_PAGE);

        return router;
    }
}