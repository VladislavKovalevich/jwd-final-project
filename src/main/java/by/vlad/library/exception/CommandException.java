package by.vlad.library.exception;

/**
 * {@code CommandException} class represent a checked exception from {@link by.vlad.library.controller.command}
 * @see Exception
 */
public class CommandException extends Exception{
    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
