package by.vlad.library.controller.command.impl.admin;

import by.vlad.library.controller.command.Command;
import by.vlad.library.controller.command.Router;
import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.CommandException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.PublisherService;
import by.vlad.library.model.service.impl.PublisherServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.ADD_BOOK_COMPONENTS_PAGE;

public class AddNewPublisherCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String PUBLISHER_ADDED_MARKER = "publisher has been added";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();

        Map<String, String> componentsData = (Map<String, String>) session.getAttribute(BOOK_COMPONENTS_FORM_DATA);

        clearSessionMap(componentsData);
        componentsData.put(PUBLISHER_NAME_FORM, request.getParameter(PUBLISHER_NAME));

        PublisherService publisherService = PublisherServiceImpl.getInstance();

        try {
            Optional<Publisher> optionalPublisher = publisherService.addNewPublisher(componentsData);
            if (optionalPublisher.isPresent()){
                Publisher publisher = optionalPublisher.get();

                List<Publisher> publishers = (List<Publisher>) session.getAttribute(PUBLISHERS);
                publishers.add(publisher);

                session.setAttribute(PUBLISHERS, publishers);

                componentsData.remove(PUBLISHER_NAME_FORM);
                componentsData.put(PUBLISHER_OPERATION_FEEDBACK, PUBLISHER_ADDED_MARKER);
            }

            session.setAttribute(CURRENT_PAGE, ADD_BOOK_COMPONENTS_PAGE);
            session.setAttribute(BOOK_COMPONENTS_FORM_DATA, componentsData);
        } catch (ServiceException e) {
            logger.error("AddNewPublisherCommand execution failed");
            throw new CommandException("AddNewPublisherCommand execution failed", e);
        }

        return new Router(ADD_BOOK_COMPONENTS_PAGE, Router.Type.REDIRECT);
    }

    private void clearSessionMap(Map<String, String> map){
        map.remove(PUBLISHER_OPERATION_FEEDBACK);
        map.remove(GENRE_OPERATION_FEEDBACK);
        map.remove(AUTHOR_OPERATION_FEEDBACK);
        map.remove(WRONG_PUBLISHER_NAME_FORM);
        map.remove(WRONG_PUBLISHER_EXISTS_FORM);
    }
}
