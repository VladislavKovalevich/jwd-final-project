package by.vlad.library.controller.command.impl.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.CURRENT_PAGE;
import static by.vlad.library.controller.command.AttributeAndParamsNames.USER_DATA;
import static by.vlad.library.controller.command.PagePath.CHANGE_PASSWORD_PAGE;

public class GoToChangeAccountPasswordPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userPasswordData = new HashMap<>();
        session.setAttribute(USER_DATA, userPasswordData);
        session.setAttribute(CURRENT_PAGE, CHANGE_PASSWORD_PAGE);

        return new Router(CHANGE_PASSWORD_PAGE, Router.Type.FORWARD);
    }
}
