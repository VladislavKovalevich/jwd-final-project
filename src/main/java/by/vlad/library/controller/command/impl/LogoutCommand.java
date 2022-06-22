package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Router;
import by.vlad.library.controller.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.library.controller.command.PagePath.INDEX_PAGE;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new Router(INDEX_PAGE, Router.Type.REDIRECT);
    }
}