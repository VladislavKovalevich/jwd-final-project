package by.vlad.JavaWebProject.controller.command.impl;

import by.vlad.JavaWebProject.controller.command.Command;
import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.exception.CommandException;
import by.vlad.JavaWebProject.exception.ServiceException;
import by.vlad.JavaWebProject.model.service.UserService;
import by.vlad.JavaWebProject.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.vlad.JavaWebProject.controller.command.AttributeAndParamsNames.*;
import static by.vlad.JavaWebProject.controller.command.PagePath.*;

public class UpdateUserAccountDataCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> userData;
        HttpSession session = request.getSession();
        Router router;

        userData = getUserData(request);

        UserService service = UserServiceImpl.getInstance();

        try {
            if(service.updatePersonalData(userData)){
                //обновилось
                session.setAttribute(USER_NAME, userData.get(UPDATE_NAME));
                session.setAttribute(USER_SURNAME, userData.get(UPDATE_SURNAME));
                session.setAttribute(USER_EMAIL, userData.get(UPDATE_EMAIL));
                router = new Router(HOME_PAGE, Router.Type.FORWARD);
            }else{
                //не обновилось
                request.setAttribute("error_msg", userData.get("error_msg"));
                router = new Router(CHANGE_ACCOUNT_DATA_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }

    private Map<String, String> getUserData(HttpServletRequest request) {
        Map<String,String> userData = new HashMap<>();

        userData.put(UPDATE_NAME, request.getParameter(UPDATE_NAME));
        userData.put(UPDATE_SURNAME, request.getParameter(UPDATE_SURNAME));
        userData.put(UPDATE_EMAIL, request.getParameter(UPDATE_EMAIL));
        userData.put("old_email", String.valueOf(request.getSession().getAttribute(USER_EMAIL)));

        return userData;
    }
}
