package by.vlad.library.validator;

/**
 * {@code GenreValidator} interface represent functional to validate
 * input data of {@link by.vlad.library.entity.Publisher} class
 */
public interface PublisherValidator {
    /**
     * {@code WRONG_FORMAT_MARKER} constant represent string to mark wrong format data
     */
    String WRONG_FORMAT_MARKER = "wrong format";

    /**
     * method to validate publisher name
     * @param publisherName publisher name
     * @return true if data is valid, false - if not
     */
    boolean validatePublisherName(String publisherName);
}
