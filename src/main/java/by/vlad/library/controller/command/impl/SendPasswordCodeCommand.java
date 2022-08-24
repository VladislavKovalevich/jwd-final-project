package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.RECOVERY_PASSWORD_BY_CODE_PAGE;
import static by.vlad.library.controller.command.Router.Type.REDIRECT;

public class SendPasswordCodeCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> userFormData = (Map<String, String>) session.getAttribute(USER_DATA);

        if (userFormData == null){
            userFormData = new HashMap<>();
        }else {
            userFormData.remove(WRONG_EMAIL_FORM);

            if (userFormData.get(EMAIL_FORM) == null) {
                userFormData.put(EMAIL_FORM, request.getParameter(EMAIL));
            }
        }

        userFormData.put(CHANGE_PASSWORD_OPERATION_STATUS_CODE, String.valueOf(0));

        UserService service = UserServiceImpl.getInstance();

        if (service.sendPasswordCode(userFormData)){
            userFormData.remove(WRONG_EMAIL_FORM);
            userFormData.remove(WRONG_EMAIL_EXISTS_FORM);

            userFormData.put(CHANGE_PASSWORD_OPERATION_STATUS_CODE, String.valueOf(1));
        }else{
            userFormData.remove(EMAIL_FORM);
        }

        session.setAttribute(USER_DATA, userFormData);

        return new Router(RECOVERY_PASSWORD_BY_CODE_PAGE, REDIRECT);
    }
}
