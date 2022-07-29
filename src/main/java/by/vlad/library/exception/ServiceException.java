package by.vlad.library.exception;

/**
 * {@code CommandException} class represent a checked exception from {@link by.vlad.library.model.service}
 * @see Exception
 */
public class ServiceException extends Exception{
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }

    public ServiceException(Exception e) {
        super(e);
    }
}
