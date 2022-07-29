package by.vlad.library.exception;

/**
 * {@code CommandException} class represent a checked exception from {@link by.vlad.library.model.dao}
 * @see Exception
 */
public class DaoException extends Exception{
    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }

    public DaoException(Exception e) {
        super(e);
    }
}
