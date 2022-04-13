package by.vlad.JavaWebProject.controller.command.impl;

import by.vlad.JavaWebProject.controller.command.Command;
import by.vlad.JavaWebProject.controller.command.Router;
import by.vlad.JavaWebProject.exception.CommandException;
import by.vlad.JavaWebProject.exception.ServiceException;
import by.vlad.JavaWebProject.model.service.UserService;
import by.vlad.JavaWebProject.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static by.vlad.JavaWebProject.controller.command.AttributeAndParamsNames.*;
import static by.vlad.JavaWebProject.controller.command.PagePath.CREATE_NEW_ACCOUNT_PAGE;
import static by.vlad.JavaWebProject.controller.command.PagePath.HOME_PAGE;

public class CreateNewAccountCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Map<String, String> userData;
        Router router;

        userData = getUserData(request);
        UserService userService = UserServiceImpl.getInstance();

        try {
            if(userService.createNewAccount(userData)){

                router = new Router(HOME_PAGE, Router.Type.FORWARD);
            }else{
                request.setAttribute("error_msg", userData.get("error_msg"));
                router = new Router(CREATE_NEW_ACCOUNT_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return router;
    }

    private Map<String, String> getUserData(HttpServletRequest request){
        Map<String, String> newUserData = new HashMap<>();

        newUserData.put(NEW_NAME, request.getParameter(NEW_NAME));
        newUserData.put(NEW_SURNAME, request.getParameter(NEW_SURNAME));
        newUserData.put(NEW_EMAIL, request.getParameter(NEW_EMAIL));
        newUserData.put(NEW_PASSWORD, request.getParameter(NEW_PASSWORD));
        newUserData.put(REPEATED_PASSWORD, request.getParameter(REPEATED_PASSWORD));

        return newUserData;
    }
}
