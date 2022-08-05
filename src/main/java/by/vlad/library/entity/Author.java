package by.vlad.library.entity;

/**
 * {@code Author} class represent author of book
 * @see AbstractEntity
 */
public class Author extends AbstractEntity{
    /** author name */
    private String name;
    /** author surname */
    private String surname;

    public Author() {
    }

    public Author(long id) {
        super(id);
    }

    public Author(long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (!name.equals(author.name)) return false;
        return surname.equals(author.surname);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.getId()).append('|')
                .append(name).append('|')
                .append(surname)
                .toString();
    }
}
