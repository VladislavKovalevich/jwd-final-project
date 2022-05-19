package by.vlad.library.controller.command.impl.admin.book.book_components;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Author;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.AuthorService;
import by.vlad.library.model.service.impl.AuthorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class UpdateAuthorCommand implements Command {
    private static final String AUTHOR_UPDATED_MARKER = "author has been update";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        request.removeAttribute(AUTHOR_UPDATED_MSG);

        List<Author> authors;
        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        initSessionMap(componentsData, request);
        removeSessionParams(componentsData);

        AuthorService authorService = AuthorServiceImpl.getInstance();

        try {
            authors = authorService.updateAuthor(componentsData);

            if (!authors.isEmpty()){
                request.setAttribute(AUTHOR_UPDATED_MSG, AUTHOR_UPDATED_MARKER);
                session.setAttribute(AUTHORS, authors);

                componentsData.remove(AUTHOR_FORM);
                componentsData.remove(AUTHOR_NAME_FORM);
                componentsData.remove(AUTHOR_SURNAME_FORM);
            }

            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(PagePath.UPDATE_BOOK_COMPONENTS_PAGE, Router.Type.FORWARD);
    }

    private void removeSessionParams(Map<String, String> map){
        map.remove(WRONG_AUTHOR_EXISTS_FORM);
        map.remove(WRONG_AUTHOR_NAME_FORM);
        map.remove(WRONG_AUTHOR_SURNAME_FORM);
    }

    private void initSessionMap(Map<String, String> map, HttpServletRequest request){
        map.put(AUTHOR_FORM, request.getParameter(AUTHOR));
        map.put(AUTHOR_NAME_FORM,request.getParameter(AUTHOR_NAME));
        map.put(AUTHOR_SURNAME_FORM,request.getParameter(AUTHOR_SURNAME));
    }
}
