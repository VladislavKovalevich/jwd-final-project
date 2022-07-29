package by.vlad.library.entity;

/**
 * {@code AbstractEntity} class represent an abstract entity from database
 */
public abstract class AbstractEntity {
    /** Entity identifier */
    private long id;

    /**
     * Constructor - creating new object
     * @see AbstractEntity#AbstractEntity()
     */
    public AbstractEntity() {
    }

    /**
     * Constructor - creating new object
     * @see AbstractEntity#AbstractEntity(long) with param
     */
    public AbstractEntity(long id) {
        this.id = id;
    }

    /**
     * method-getter for name field {@link AbstractEntity#id}
     * @return author name
     */
    public long getId() {
        return id;
    }

    /**
     * Method-setter for name filed {@link AbstractEntity#id}
     * @param id - entity identifier
     */
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(id)
                .append('|')
                .toString();
    }
}
