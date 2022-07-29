package by.vlad.library.entity;

/**
 * {@code Genre} class represent a books genre
 * @see AbstractEntity
 */
public class Genre extends AbstractEntity{
    /** genre name */
    private String name;

    /** Constructor */
    public Genre(){
    }

    /**
     * Constructor with param
     * @param id - genre id
     */
    public Genre(long id){
        super(id);
    }

    /**
     * Constructor with param
     * @param name - genre name
     */
    public Genre(String name) {
        this.name = name;
    }

    /**
     * Constructor with params
     * @param id - genre id
     * @param name - genre name
     */
    public Genre(long id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * Getter method for {@link Genre#name}
     * @return - field value {@link Genre#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for {@link Genre#name} filed
     * @param name - genre name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Genre genre = (Genre) o;

        return name.equals(genre.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.getId())
                .append('|')
                .append(name)
                .toString();
    }
}
