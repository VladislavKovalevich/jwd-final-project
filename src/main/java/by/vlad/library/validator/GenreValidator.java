package by.vlad.library.validator;

public interface GenreValidator {
    String WRONG_FORMAT_MARKER = "wrong format";

    boolean validateGenreName(String name);
}
