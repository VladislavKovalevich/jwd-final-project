package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.RECOVERY_PASSWORD_BY_CODE_PAGE;

public class VerifyPasswordCodeCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> userFormData = (Map<String, String>) session.getAttribute(USER_DATA);
        UserService userService = UserServiceImpl.getInstance();

        userFormData.remove(WRONG_SECRET_CODE);
        userFormData.put(SECRET_CODE_FORM, request.getParameter(SECRET_CODE));

        if (userService.verifyPasswordCode(userFormData)){
            userFormData.put(CHANGE_PASSWORD_OPERATION_STATUS_CODE, String.valueOf(2));
        }

        session.setAttribute(USER_DATA, userFormData);

        return new Router(RECOVERY_PASSWORD_BY_CODE_PAGE, Router.Type.REDIRECT);
    }
}
