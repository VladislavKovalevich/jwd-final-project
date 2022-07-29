package by.vlad.library.entity;

/**
 * {@code Publisher} class represent a publisher
 * @see AbstractEntity
 */
public class Publisher extends AbstractEntity{
    /** publisher name */
    private String name;

    /**
     * Constructor - default
     */
    public Publisher() {
    }

    /**
     * Constructor - with param
     * @param id - publisher id
     */
    public Publisher(long id) {
        super(id);
    }

    /**
     * Constructor - with params
     * @param id - publisher id
     * @param name - publisher name
     */
    public Publisher(long id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * Getter method for {@link Publisher#name}
     * @return - field value {@link Publisher#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for {@link Publisher#name}
     * @param name - publisher name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        return name.equals(publisher.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.getId()).append('|')
                .append(name)
                .toString();
    }
}
