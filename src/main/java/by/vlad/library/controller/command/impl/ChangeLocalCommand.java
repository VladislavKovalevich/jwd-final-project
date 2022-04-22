package by.vlad.library.controller.command.impl;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class ChangeLocalCommand implements Command {
    private enum Language{
        RU("ru_RU"),
        EN("en_US");

        private String local;

        Language(String local){
            this.local = local;
        }

        public String getLocal() {
            return local;
        }
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        HttpSession session = request.getSession();
        String currentPage = String.valueOf(session.getAttribute(CURRENT_PAGE));

        Language newLocal = Language.valueOf(request.getParameter(LANGUAGE));

        switch (newLocal){

            case EN -> {
                session.setAttribute(LOCALE, Language.EN.getLocal());
            }

            default -> {
                session.setAttribute(LOCALE, Language.RU.getLocal());
            }
        }

        router = new Router(currentPage, Router.Type.FORWARD);

        return router;
    }
}
