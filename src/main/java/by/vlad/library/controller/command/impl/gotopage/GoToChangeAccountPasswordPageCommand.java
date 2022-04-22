package by.vlad.library.controller.command.impl.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.library.controller.command.AttributeAndParamsNames.CURRENT_PAGE;
import static by.vlad.library.controller.command.PagePath.CHANGE_PASSWORD_PAGE;

public class GoToChangeAccountPasswordPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.setAttribute(CURRENT_PAGE, CurrentPageExtractor.extract(request));
        Router router = new Router(CHANGE_PASSWORD_PAGE, Router.Type.FORWARD);
        return router;
    }
}
