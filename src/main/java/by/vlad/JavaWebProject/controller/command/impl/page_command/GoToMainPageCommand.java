package by.vlad.JavaWebProject.controller.command.impl.page_command;

import by.vlad.JavaWebProject.controller.command.Command;
import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.controller.command.PagePath;
import by.vlad.JavaWebProject.exception.CommandException;
import by.vlad.JavaWebProject.util.CurrentPageExtractor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.JavaWebProject.controller.command.AttributeAndParamsNames.CURRENT_PAGE;

public class GoToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.setAttribute(CURRENT_PAGE, CurrentPageExtractor.extract(request));
        return new Router(PagePath.MAIN_PAGE, Router.Type.FORWARD);
    }
}
