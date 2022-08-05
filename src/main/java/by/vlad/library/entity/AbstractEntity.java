package by.vlad.library.entity;

/**
 * {@code AbstractEntity} class represent an abstract entity from database
 */
public abstract class AbstractEntity {
    /** Entity identifier */
    private long id;

    public AbstractEntity() {
    }

    public AbstractEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

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
