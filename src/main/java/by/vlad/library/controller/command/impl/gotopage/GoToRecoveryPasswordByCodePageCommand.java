package by.vlad.library.controller.command.impl.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.RECOVERY_PASSWORD_BY_CODE_PAGE;

public class GoToRecoveryPasswordByCodePageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA);

        userData.clear();
        userData.put(CHANGE_PASSWORD_OPERATION_STATUS_CODE, String.valueOf(0));

        session.setAttribute(CURRENT_PAGE, RECOVERY_PASSWORD_BY_CODE_PAGE);

        return new Router(RECOVERY_PASSWORD_BY_CODE_PAGE, Router.Type.REDIRECT);
    }
}
