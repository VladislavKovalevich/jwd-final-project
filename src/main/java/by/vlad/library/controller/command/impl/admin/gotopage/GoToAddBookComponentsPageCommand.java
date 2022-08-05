package by.vlad.library.controller.command.impl.admin.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.AttributeAndParamsNames.AUTHOR_SURNAME_FORM;
import static by.vlad.library.controller.command.PagePath.ADD_BOOK_COMPONENTS_PAGE;

public class GoToAddBookComponentsPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        HashMap<String, String> componentsData = new HashMap<>();

        componentsData.put(GENRE_NAME_FORM, "");
        componentsData.put(PUBLISHER_NAME_FORM, "");
        componentsData.put(AUTHOR_NAME_FORM, "");
        componentsData.put(AUTHOR_SURNAME_FORM, "");

        session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        session.setAttribute(CURRENT_PAGE, ADD_BOOK_COMPONENTS_PAGE);

        return new Router(ADD_BOOK_COMPONENTS_PAGE, Router.Type.FORWARD);
    }
}
