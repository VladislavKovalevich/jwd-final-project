package by.vlad.library.controller.command;

/**
 * {@code Router} class represent complex from response of {@link Command}
 * It includes the page to which the transition should be made and sending type
 */
public class Router {
    private String page;
    private Type type = Type.FORWARD;

    /**
     * {@code Type} enum represent a sending type
     */
    public enum Type{
        FORWARD, REDIRECT;
    }

    public Router(String page) {
        this.page = page;
    }

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public String getPage(){
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect() {
        this.type = Type.REDIRECT;
    }

    public Type getType() {
        return type;
    }
}