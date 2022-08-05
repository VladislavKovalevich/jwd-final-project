package by.vlad.library.controller.command.impl.admin.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.UPDATE_BOOK_COMPONENTS_PAGE;

public class GoToUpdateBookComponentsPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> componentsData = new HashMap<>();

        componentsData.put(GENRE_NAME_FORM, "");
        componentsData.put(PUBLISHER_NAME_FORM, "");
        componentsData.put(AUTHOR_NAME_FORM, "");
        componentsData.put(AUTHOR_SURNAME_FORM, "");

        session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        session.setAttribute(CURRENT_PAGE, UPDATE_BOOK_COMPONENTS_PAGE);

        return new Router(UPDATE_BOOK_COMPONENTS_PAGE, Router.Type.FORWARD);
    }
}
