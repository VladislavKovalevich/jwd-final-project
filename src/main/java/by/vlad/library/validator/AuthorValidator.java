package by.vlad.library.validator;

import java.util.Map;

public interface AuthorValidator {
    String WRONG_FORMAT_MARKER = "wrong format";

    boolean validateName(String name);

    boolean validateAuthorParams(Map<String, String> authorMap);
}
