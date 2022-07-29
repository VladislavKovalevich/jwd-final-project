package by.vlad.library.controller.filter;

import by.vlad.library.controller.command.CommandType;
import by.vlad.library.controller.command.PagePath;
import by.vlad.library.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

import static by.vlad.library.controller.command.AttributeAndParamsNames.COMMAND;
import static by.vlad.library.controller.command.AttributeAndParamsNames.USER_ROLE;
import static by.vlad.library.controller.command.CommandType.*;

/**
 * {@code ControllerSecurityFilter} class implements functional of {@link Filter}
 * Restricts access to the commands depending on the user's role.
 */
@WebFilter(urlPatterns = {"/controller"}, servletNames = {"controller"})
public class ControllerSecurityFilter implements Filter {
    private Set<CommandType> guestCommandsSet;
    private Set<CommandType> clientCommandsSet;
    private Set<CommandType> adminCommandSet;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        guestCommandsSet = Set.of(
                LOGIN,
                LOGOUT,
                CREATE_NEW_ACCOUNT,
                SHOW_BOOKS_LIST,
                SHOW_BOOK_INFO,
                CHANGE_LOCAL,
                GO_TO_LOGIN_PAGE,
                GO_TO_MAIN_PAGE,
                GO_TO_CREATE_NEW_ACCOUNT_PAGE
        );

        clientCommandsSet = Set.of(
                LOGOUT,
                UPDATE_USER_ACCOUNT_DATA,
                CHANGE_ACCOUNT_PASSWORD,
                SHOW_BOOKS_LIST,
                SHOW_BOOK_INFO,
                CHANGE_LOCAL,
                GET_ORDERS_BY_USER_ID,
                GET_BOOKS_BY_ORDER_ID,
                CREATE_ORDER,
                RESERVE_ORDER,
                RETURN_ORDER,
                DELETE_ORDER,
                ADD_BOOK_TO_ORDER,
                REMOVE_BOOK_FROM_ORDER,
                GO_TO_CHANGE_PASSWORD_PAGE,
                GO_TO_ORDER_LIST_PAGE,
                GO_TO_UPDATE_ACCOUNT_DATA_PAGE,
                GO_TO_MAIN_PAGE
        );

        adminCommandSet = Set.of(
                LOGOUT,
                UPDATE_USER_ACCOUNT_DATA,
                CHANGE_ACCOUNT_PASSWORD,
                CHANGE_LOCAL,
                SHOW_USERS_LIST,
                SHOW_BOOKS_LIST,
                SHOW_BOOK_INFO,
                CHANGE_USER_STATUS,
                ADD_NEW_GENRE,
                ADD_NEW_AUTHOR,
                ADD_NEW_PUBLISHER,
                ADD_NEW_BOOK,
                UPDATE_AUTHOR,
                UPDATE_GENRE,
                UPDATE_PUBLISHER,
                UPDATE_BOOK_DATA,
                ACCEPT_ORDER,
                REJECT_ORDER,
                GET_ORDERS_LIST,
                GET_ORDERS_BY_USER_ID,
                GET_BOOKS_BY_ORDER_ID,
                GO_TO_ADD_BOOK_COMPONENTS_PAGE,
                GO_TO_ADD_NEW_BOOK_PAGE,
                GO_TO_UPDATE_BOOK_COMPONENTS_PAGE,
                GO_TO_UPDATE_BOOK_DATA_PAGE,
                GO_TO_ORDER_LIST_PAGE,
                GO_TO_MAIN_PAGE,
                GO_TO_CHANGE_PASSWORD_PAGE,
                GO_TO_UPDATE_ACCOUNT_DATA_PAGE
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();

        String commandName = httpRequest.getParameter(COMMAND);
        if (commandName == null){
            httpResponse.sendRedirect(PagePath.INDEX_PAGE);
        }

        CommandType command = getCommandType(commandName);
        Role userRole = session.getAttribute(USER_ROLE) == null ? Role.GUEST
                : Role.getRole(session.getAttribute(USER_ROLE).toString());

        boolean isAccept =
        switch (userRole){
            case GUEST -> guestCommandsSet.stream().anyMatch(commandType -> commandType == command);
            case CLIENT -> clientCommandsSet.stream().anyMatch(commandType -> commandType == command);
            case ADMIN -> adminCommandSet.stream().anyMatch(commandType -> commandType == command);
        };

        if (isAccept){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Override
    public void destroy() {

    }
}
