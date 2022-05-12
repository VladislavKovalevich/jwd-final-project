package by.vlad.library.controller.command.impl.gotopage;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.exception.CommandException;
import by.vlad.library.controller.util.CurrentPageExtractor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.library.controller.command.AttributeAndParamsNames.CURRENT_PAGE;
import static by.vlad.library.controller.command.AttributeAndParamsNames.USER_ID;

public class GoToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router;

        if (session.getAttribute(USER_ID) != null){
            router = new Router(PagePath.HOME_PAGE, Router.Type.FORWARD);
        }else{
            router = new Router(PagePath.MAIN_PAGE, Router.Type.FORWARD);
        }

        session.setAttribute(CURRENT_PAGE, CurrentPageExtractor.extract(request));

        return router;
    }
}
