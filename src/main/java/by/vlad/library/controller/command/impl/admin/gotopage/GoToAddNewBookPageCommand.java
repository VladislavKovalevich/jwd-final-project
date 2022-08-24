package by.vlad.library.controller.command.impl.admin.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ADD_NEW_BOOK_PAGE;
import static by.vlad.library.controller.command.Router.Type.FORWARD;

public class GoToAddNewBookPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> booksData = new HashMap<>();

        session.setAttribute(BOOK_FORM_DATA, booksData);
        session.setAttribute(CURRENT_PAGE, ADD_NEW_BOOK_PAGE);

        return new Router(ADD_NEW_BOOK_PAGE, FORWARD);
    }
}