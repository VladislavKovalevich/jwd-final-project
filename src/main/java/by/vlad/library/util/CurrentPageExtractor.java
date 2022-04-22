package by.vlad.library.util;

import jakarta.servlet.http.HttpServletRequest;

public class CurrentPageExtractor {
    private static final String CONTROLLER_PART = "/controller?";

    public static String extract(HttpServletRequest request) {
        String commandPart = request.getQueryString();
        return CONTROLLER_PART + commandPart;
    }
}
