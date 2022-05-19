package by.vlad.library.controller.command.impl.admin.book.book_components;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.GenreService;
import by.vlad.library.model.service.PublisherService;
import by.vlad.library.model.service.impl.GenreServiceImpl;
import by.vlad.library.model.service.impl.PublisherServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ADD_BOOK_COMPONENTS_PAGE;

public class AddNewPublisherCommand implements Command {
    private static final String PUBLISHER_ADDED_MARKER = "publisher has been added";
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        request.removeAttribute(PUBLISHER_ADDED_MSG);

        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        componentsData.put(PUBLISHER_NAME_FORM, request.getParameter(PUBLISHER_NAME));
        componentsData.remove(WRONG_PUBLISHER_NAME_FORM);
        componentsData.remove(WRONG_PUBLISHER_EXISTS_FORM);

        PublisherService genreService = PublisherServiceImpl.getInstance();

        try {
            if (genreService.addNewPublisher(componentsData)){
                componentsData.remove(PUBLISHER_NAME_FORM);
                request.setAttribute(PUBLISHER_ADDED_MSG, PUBLISHER_ADDED_MARKER);
            }

            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(ADD_BOOK_COMPONENTS_PAGE, Router.Type.FORWARD);
    }
}
