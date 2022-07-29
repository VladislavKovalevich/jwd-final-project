package by.vlad.library.controller.command;

import by.vlad.library.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * {@code Command} interface represent functional of command
 */
@FunctionalInterface
public interface Command {

    /**
     * Method to execute command
     * @param request - request from controller, type {@link HttpServletRequest}
     * @return router - router that contains path and sending type {@link Router.Type} for response, type {@link Router}
     * @throws CommandException - if service method throw {@link by.vlad.library.exception.ServiceException}
     */
    Router execute(HttpServletRequest request) throws CommandException;
}