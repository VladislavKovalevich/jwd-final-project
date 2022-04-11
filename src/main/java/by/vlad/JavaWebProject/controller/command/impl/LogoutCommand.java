package by.vlad.JavaWebProject.controller.command.impl;

import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.controller.command.Command;

import jakarta.servlet.http.HttpServletRequest;

import static by.vlad.JavaWebProject.controller.command.PagePath.MAIN_PAGE;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Router(MAIN_PAGE, Router.Type.REDIRECT);// в константу
    }
}