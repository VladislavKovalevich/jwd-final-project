package by.vlad.library.validator;

/**
 * {@code GenreValidator} interface represent functional to validate
 * input data of {@link by.vlad.library.entity.Genre} class
 */
public interface GenreValidator {
    /**
     * {@code WRONG_FORMAT_MARKER} constant represent string to mark wrong format data
     */
    String WRONG_FORMAT_MARKER = "wrong format";

    /**
     * method to validate genre name
     * @param name genre name
     * @return true if data is valid, false - if not
     */
    boolean validateGenreName(String name);
}
