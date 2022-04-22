package by.vlad.library.controller.command.impl.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.CURRENT_PAGE;
import static by.vlad.library.controller.command.AttributeAndParamsNames.USER_FORM_DATA;
import static by.vlad.library.controller.command.PagePath.CREATE_NEW_ACCOUNT_PAGE;

public class GoToCreateNewAccountPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String,String> userFormData = new HashMap<>();
        session.setAttribute(USER_FORM_DATA, userFormData);
        session.setAttribute(CURRENT_PAGE, CurrentPageExtractor.extract(request));
        return new Router(CREATE_NEW_ACCOUNT_PAGE, Router.Type.FORWARD);
    }
}
