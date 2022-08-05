package by.vlad.library.entity;

/**
 * {@code Genre} class represent a books genre
 * @see AbstractEntity
 */
public class Genre extends AbstractEntity{
    /** genre name */
    private String name;

    public Genre(){
    }

    public Genre(long id){
        super(id);
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

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
