package by.vlad.library.controller.filter;

import by.vlad.library.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.*;

/**
 * {@code PageSecurityFilter} class implements functional of {@link Filter}
 * Restricts access to the page depending on the user's role.
 */
@WebFilter(urlPatterns = {"/jsp/*"})
public class PageSecurityFilter implements Filter {
    private Set<String> guestPages;
    private Set<String> clientPages;
    private Set<String> adminPages;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        guestPages = Set.of(
                BOOKS_PAGE,
                LOGIN_PAGE,
                BOOK_INFO_PAGE,
                NEW_ACCOUNT_PAGE,
                RECOVERY_PASSWORD_BY_CODE_PAGE
        );

        clientPages = Set.of(
                BOOKS_PAGE,
                BOOK_INFO_PAGE,
                ORDERS_BY_USER_ID_PAGE,
                ORDER_INFO_PAGE,
                CHANGE_PASSWORD_PAGE,
                CHANGE_ACCOUNT_DATA_PAGE,
                ACCOUNT_PAGE
        );

        adminPages = Set.of(
                BOOKS_PAGE,
                BOOK_INFO_PAGE,
                ORDERS_PAGE,
                CHANGE_PASSWORD_PAGE,
                CHANGE_ACCOUNT_DATA_PAGE,
                ADD_NEW_BOOK_PAGE,
                UPDATE_BOOK_DATA_PAGE,
                UPDATE_BOOK_COMPONENTS_PAGE,
                ADD_BOOK_COMPONENTS_PAGE,
                USERS_PAGE,
                ACCOUNT_PAGE,
                ORDER_INFO_PAGE
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getServletPath();

        HttpSession session = request.getSession();

        Role role = session.getAttribute(USER_ROLE) == null
                ? Role.GUEST
                : Role.getRole(session.getAttribute(USER_ROLE).toString());

        boolean isAccept =
                switch (role) {
                    case CLIENT -> clientPages.stream().anyMatch(requestURI::contains);
                    case ADMIN -> adminPages.stream().anyMatch(requestURI::contains);
                    case GUEST -> guestPages.stream().anyMatch(requestURI::contains);
                };

        if (isAccept){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

    }

    @Override
    public void destroy() {
    }
}
