package by.vlad.library.validator;

public interface PublisherValidator {
    String WRONG_FORMAT_MARKER = "wrong format";

    boolean validatePublisherName(String publisherName);
}
