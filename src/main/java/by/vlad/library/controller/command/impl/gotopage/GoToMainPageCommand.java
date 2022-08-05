package by.vlad.library.controller.command.impl.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class GoToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        String page = (String) session.getAttribute(CURRENT_PAGE);

        switch (page){
            case ADD_BOOK_COMPONENTS_PAGE, UPDATE_BOOK_COMPONENTS_PAGE -> session.removeAttribute(BOOK_COMPONENTS_FORM_DATA);
            case ADD_NEW_BOOK_PAGE -> session.removeAttribute(BOOK_FORM_DATA);
            case BOOK_INFO_PAGE -> {
                session.removeAttribute(OPERATION_FEEDBACK_MAP_SES);
                session.removeAttribute(BOOK);
            }
            case ACCOUNT_PAGE -> session.removeAttribute(USER_INFO);
        }

        session.setAttribute(CURRENT_PAGE, BOOKS_PAGE);

        return new Router(BOOKS_PAGE, Router.Type.FORWARD);
    }
}
