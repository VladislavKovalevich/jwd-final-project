package by.vlad.library.controller.command.impl.admin.book_components;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.AuthorService;
import by.vlad.library.model.service.impl.AuthorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ADD_BOOK_COMPONENTS_PAGE;

public class AddNewAuthorCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        clearWrongMsg(componentsData);
        fillComponentsData(componentsData, request);

        AuthorService authorService = AuthorServiceImpl.getInstance();

        try {

            if (authorService.createNewAuthor(componentsData)){
                componentsData.remove(AUTHOR_NAME_FORM);
                componentsData.remove(AUTHOR_SURNAME_FORM);
            }

            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(ADD_BOOK_COMPONENTS_PAGE, Router.Type.FORWARD);
    }

    private void fillComponentsData(Map<String, String> componentsData, HttpServletRequest request) {
        componentsData.put(AUTHOR_NAME_FORM,request.getParameter(AUTHOR_NAME));
        componentsData.put(AUTHOR_SURNAME_FORM,request.getParameter(AUTHOR_SURNAME));
    }

    private void clearWrongMsg(Map<String, String> componentsData) {
        componentsData.remove(WRONG_AUTHOR_NAME_FORM);
        componentsData.remove(WRONG_AUTHOR_SURNAME_FORM);
    }
}
