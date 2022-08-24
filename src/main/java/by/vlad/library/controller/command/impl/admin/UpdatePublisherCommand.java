package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.PublisherService;
import by.vlad.library.model.service.impl.PublisherServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.UPDATE_BOOK_COMPONENTS_PAGE;
import static by.vlad.library.controller.command.Router.Type.REDIRECT;

public class UpdatePublisherCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        clearSessionMap(componentsData);
        fillSessionMap(componentsData, request);

        PublisherService publisherService = PublisherServiceImpl.getInstance();

        try {
            Optional<Publisher> optionalPublisher = publisherService.updatePublisher(componentsData);

            if (optionalPublisher.isPresent()){
                Publisher publisher = optionalPublisher.get();

                List<Publisher> publishers = (List<Publisher>) session.getAttribute(PUBLISHERS);
                publishers.removeIf(p -> p.getId() == publisher.getId());
                publishers.add(publisher);

                session.setAttribute(PUBLISHERS, publishers);

                componentsData.remove(PUBLISHER_FORM);
                componentsData.remove(PUBLISHER_NAME_FORM);
            }

            session.setAttribute(CURRENT_PAGE, UPDATE_BOOK_COMPONENTS_PAGE);
            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        } catch (ServiceException e) {
            logger.error("UpdatePublisherCommand execution failed");
            throw new CommandException("UpdatePublisherCommand execution failed", e);
        }

        return new Router(UPDATE_BOOK_COMPONENTS_PAGE, REDIRECT);
    }

    private void clearSessionMap(Map<String, String> map){
        map.remove(WRONG_PUBLISHER_EXISTS_FORM);
        map.remove(WRONG_PUBLISHER_NAME_FORM);
    }

    private void fillSessionMap(Map<String, String> map, HttpServletRequest request){
        map.put(PUBLISHER_FORM, request.getParameter(PUBLISHER));
        map.put(PUBLISHER_NAME_FORM,request.getParameter(PUBLISHER_NAME));
    }
}
