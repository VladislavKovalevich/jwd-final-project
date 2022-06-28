package by.vlad.library.controller.command.impl.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.User;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.model.service.impl.UserServiceImpl;
import by.vlad.library.controller.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.CHANGE_ACCOUNT_DATA_PAGE;

public class GoToUpdateAccountDataPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        long id = (long) session.getAttribute(USER_ID);

        Map<String, String> userData = new HashMap<>();
        UserService userService = UserServiceImpl.getInstance();

        try {
            Optional<User> optionalUser = userService.findUserById(id);

            if (optionalUser.isPresent()){
                User user = optionalUser.get();

                userData.put(NAME_FORM, user.getName());
                userData.put(SURNAME_FORM, user.getSurname());
                userData.put(EMAIL_FORM, user.getEmail());
                userData.put(LOGIN_FORM, user.getLogin());
                userData.put(SERIAL_NUMBER_FORM, user.getPassportSerialNumber());
                userData.put(PHONE_NUMBER_FORM, user.getMobilePhone());

                session.setAttribute(USER_FORM_DATA, userData);
            }
        } catch (ServiceException e) {
            logger.error("GoToUpdateAccountDataPageCommand execution failed");
            throw new CommandException("GoToUpdateAccountDataPageCommand execution failed", e);
        }

        session.setAttribute(CURRENT_PAGE, CurrentPageExtractor.extract(request));
        return new Router(CHANGE_ACCOUNT_DATA_PAGE, Router.Type.FORWARD);
    }
}
