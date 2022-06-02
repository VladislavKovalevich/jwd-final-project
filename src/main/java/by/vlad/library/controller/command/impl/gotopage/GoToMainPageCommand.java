package by.vlad.library.controller.command.impl.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.exception.CommandException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

public class GoToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router;

        if (session.getAttribute(CURRENT_PAGE).equals(ADD_BOOK_COMPONENTS_PAGE)
            || session.getAttribute(CURRENT_PAGE).equals(UPDATE_BOOK_COMPONENTS_PAGE))
        {
            session.removeAttribute(BOOK_COMPONENTS_FORM_DATA);
        }

        if (session.getAttribute(CURRENT_PAGE).equals(ADD_NEW_BOOK_PAGE)){
            session.removeAttribute(BOOK_FORM_DATA);
            session.removeAttribute(AUTHORS);
            session.removeAttribute(PUBLISHERS);
            session.removeAttribute(GENRES);
        }

        if (session.getAttribute(USER_ID) != null){
            router = new Router(PagePath.HOME_PAGE, Router.Type.FORWARD);
            session.setAttribute(CURRENT_PAGE, PagePath.HOME_PAGE);
        }else{
            router = new Router(PagePath.MAIN_PAGE, Router.Type.FORWARD);
            session.setAttribute(CURRENT_PAGE, PagePath.MAIN_PAGE);
        }

        return router;
    }
}
