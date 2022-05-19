package by.vlad.library.validator;

import java.util.Map;

public interface BookValidator {
    String WRONG_FORMAT_MARKER = "wrong format";

    boolean validateTitle(String title);

    boolean validateCopiesNumber(String copiesNumber);

    boolean validatePagesCount(String pagesCount);

    boolean validateReleaseYear(String year);

    boolean validateDescription(String description);

    boolean validateBookData(Map<String, String> bookData);
}
