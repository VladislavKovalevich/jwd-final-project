package by.vlad.JavaWebProject.controller.command.impl.page_command;

import by.vlad.JavaWebProject.controller.command.Command;
import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.controller.command.PagePath;
import by.vlad.JavaWebProject.exception.CommandException;
import by.vlad.JavaWebProject.util.CurrentPageExtractor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public class GoToLoginPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = new HashMap<>();
        session.setAttribute("current_page", CurrentPageExtractor.extract(request));
        return new Router(PagePath.LOGIN_PAGE, Router.Type.FORWARD);
    }
}
