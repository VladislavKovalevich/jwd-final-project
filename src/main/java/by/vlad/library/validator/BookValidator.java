package by.vlad.library.validator;

import java.util.Map;

/**
 * {@code BookValidator} interface represent functional to validate
 * input data of {@link by.vlad.library.entity.Book} class
 */
public interface BookValidator {
    /**
     * {@code WRONG_FORMAT_MARKER} constant represent string to mark wrong format data
     */
    String WRONG_FORMAT_MARKER = "wrong format";

    /**
     * method to validate book title
     * @param title book title
     * @return true if data is valid, false - if not
     */
    boolean validateTitle(String title);

    /**
     * method to validate book copies number
     * @param copiesNumber book copies number
     * @return true if data is valid, false - if not
     */
    boolean validateCopiesNumber(String copiesNumber);

    /**
     * method to validate book number of pages
     * @param pagesCount book number of pages
     * @return true if data is valid, false - if not
     */
    boolean validatePagesCount(String pagesCount);

    /**
     * method to validate book release year
     * @param year book release year
     * @return true if data is valid, false - if not
     */
    boolean validateReleaseYear(String year);

    /**
     * method to validate book description
     * @param description book description
     * @return true if data is valid, false - if not
     */
    boolean validateDescription(String description);

    /**
     * method to validate book data before create or update operation
     * @param bookData map with book data
     * @return true if data is valid, false - if not
     */
    boolean validateBookData(Map<String, String> bookData);
}
