package by.vlad.library.validator;

import java.util.Map;

/**
 * {@code AuthorValidator} interface represent functional to validate
 * input data of {@link by.vlad.library.entity.Author} class
 */
public interface AuthorValidator {
    /**
     * {@code WRONG_FORMAT_MARKER} constant represent string to mark wrong format data
     */
    String WRONG_FORMAT_MARKER = "wrong format";

    /**
     * method to validate author name or surname
     * @param name author name or surname
     * @return true if data is valid, false - if not
     */
    boolean validateName(String name);

    /**
     * method to validate author data
     * @param authorMap map with author data
     * @return true if data is valid, false - if not
     */
    boolean validateAuthorParams(Map<String, String> authorMap);
}
