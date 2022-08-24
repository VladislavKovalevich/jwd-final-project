package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.RECOVERY_PASSWORD_BY_CODE_PAGE;
import static by.vlad.library.controller.command.PagePath.LOGIN_PAGE;

public class RecoveryPasswordByCodeCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA);

        clearWrongMessages(userData);
        initPasswordData(request, userData);

        UserService service = UserServiceImpl.getInstance();

        String page;
        Router.Type type = Router.Type.REDIRECT;

        try {
            if (service.changePasswordByCode(userData)){
                userData.clear();
                userData.put(SUCCESSFULLY_PASSWORD_CHANGE, UserService.USER_MAP_MARKER);
                page = LOGIN_PAGE;
            }else{
                page = RECOVERY_PASSWORD_BY_CODE_PAGE;
            }
        } catch (ServiceException e) {
            logger.error("ChangeAccountPasswordCommand execution failed");
            throw new CommandException("ChangeAccountPasswordCommand execution failed", e);
        }

        session.setAttribute(CURRENT_PAGE, page);

        return new Router(page, type);
    }

    private void initPasswordData(HttpServletRequest request, Map<String, String> passwordData) {
        passwordData.put(NEW_PASSWORD_FORM, request.getParameter(NEW_PASSWORD));
        passwordData.put(NEW_REPEAT_PASSWORD_FORM, request.getParameter(NEW_REPEAT_PASSWORD));
    }

    private void clearWrongMessages(Map<String, String> passwordData){
        passwordData.remove(WRONG_NEW_PASSWORD_FORM);
        passwordData.remove(WRONG_NEW_REPEAT_PASSWORD_FORM);
        passwordData.remove(WRONG_PASSWORD_VALUE);
    }
}
