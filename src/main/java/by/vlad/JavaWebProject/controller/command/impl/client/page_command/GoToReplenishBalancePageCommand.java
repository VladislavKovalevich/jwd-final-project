package by.vlad.JavaWebProject.controller.command.impl.client.page_command;

import by.vlad.JavaWebProject.controller.command.Command;
import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.exception.CommandException;
import by.vlad.JavaWebProject.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.JavaWebProject.controller.command.AttributeAndParamsNames.CURRENT_PAGE;
import static by.vlad.JavaWebProject.controller.command.PagePath.REPLENISH_BALANCE_PAGE;

public class GoToReplenishBalancePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.setAttribute(CURRENT_PAGE, CurrentPageExtractor.extract(request));
        Router router = new Router(REPLENISH_BALANCE_PAGE, Router.Type.FORWARD);
        return router;
    }
}