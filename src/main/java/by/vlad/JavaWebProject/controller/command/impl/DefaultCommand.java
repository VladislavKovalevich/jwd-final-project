package by.vlad.JavaWebProject.controller.command.impl;

import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.controller.command.Command;

import jakarta.servlet.http.HttpServletRequest;

import static by.vlad.JavaWebProject.controller.command.PagePath.LOGIN_PAGE;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(LOGIN_PAGE);
    }
}