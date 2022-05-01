package by.vlad.library.validator;

import java.util.Map;

public interface BookValidator {
    boolean validateTitle(String title);

    boolean validateCopiesNumber(String copiesNumber);

    boolean validatePagesCount(String pagesCount);

    boolean validateReleaseYear(String year);

    boolean validateDescription(String description);

    boolean validateCreatedBook(Map<String, String> bookData);
}
