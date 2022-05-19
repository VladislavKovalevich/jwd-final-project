package by.vlad.library.controller.command.impl.admin.book.book_components;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.PublisherService;
import by.vlad.library.model.service.impl.PublisherServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class UpdatePublisherCommand implements Command {
    private static final String PUBLISHER_UPDATED_MARKER = "genre has been updated";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        request.removeAttribute(PUBLISHER_UPDATED_MSG);

        List<Publisher> publishers;
        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        removeSessionParams(componentsData);
        initSessionMap(componentsData, request);

        PublisherService publisherService = PublisherServiceImpl.getInstance();

        try {
            publishers = publisherService.updatePublisher(componentsData);

            if (!publishers.isEmpty()){
                request.setAttribute(PUBLISHER_UPDATED_MSG, PUBLISHER_UPDATED_MARKER);
                session.setAttribute(PUBLISHERS, publishers);

                componentsData.remove(PUBLISHER_FORM);
                componentsData.remove(PUBLISHER_NAME_FORM);
            }

            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return new Router(PagePath.UPDATE_BOOK_COMPONENTS_PAGE, Router.Type.FORWARD);
    }

    private void removeSessionParams(Map<String, String> map){
        map.remove(WRONG_PUBLISHER_EXISTS_FORM);
        map.remove(WRONG_PUBLISHER_NAME_FORM);
    }

    private void initSessionMap(Map<String, String> map, HttpServletRequest request){
        map.put(PUBLISHER_FORM, request.getParameter(PUBLISHER));
        map.put(PUBLISHER_NAME_FORM,request.getParameter(PUBLISHER_NAME));
    }
}
