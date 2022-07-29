package by.vlad.library.controller.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.controller.command.PagePath.INDEX_PAGE;

/**
 * {@code SessionListenerImpl} class implements functional of {@link HttpSessionListener}
 */
@WebListener
public class SessionListenerImpl implements HttpSessionListener {
    private static final String DEFAULT_LANGUAGE = "en_US";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.setAttribute(LOCALE, DEFAULT_LANGUAGE);
        session.setAttribute(CURRENT_PAGE, INDEX_PAGE);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
