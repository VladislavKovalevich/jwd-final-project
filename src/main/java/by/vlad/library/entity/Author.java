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

    /**
     * Constructor - creating new object
     */
    public Author() {
    }

    /**
     * Constructor - creating new object with param
     * @param id - {@link AbstractEntity#id}
     */
    public Author(long id) {
        super(id);
    }

    /**
     * Constructor - creating new object with some params
     * @param id - {@link AbstractEntity#id}
     * @param name - {@link Author#name}
     * @param surname - {@link Author#surname}
     */
    public Author(long id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    /**
     * {@code getAuthorBuilder} method to get {@link AuthorBuilder}
     * @return {@link AuthorBuilder}
     */
    public static AuthorBuilder getAuthorBuilder(){
        return new Author().new AuthorBuilder();
    }

    /**
     * method-getter for {@link Author#name} field
     * @return author name
     */
    public String getName() {
        return name;
    }

    /**
     * Method-setter for {@link Author#name} filed
     * @param name - author name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * method-getter for {@link Author#surname} field
     * @return author surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Method-setter for {@link Author#surname} filed
     * @param surname - author surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * {@code AuthorBuilder} class to build {@link Author}
     */
    public class AuthorBuilder{
        /**
         * Method defines {@link AbstractEntity#id} field
         * @param id - author id
         * @return - builder object {@link AuthorBuilder}
         */
        public AuthorBuilder withId(long id){
            Author.this.setId(id);
            return this;
        }

        /**
         * Method defines {@link Author#name} field
         * @param name - author name
         * @return - builder object {@link AuthorBuilder}
         */
        public AuthorBuilder withName(String name){
            Author.this.setName(name);
            return this;
        }

        /**
         * Method defines {@link Author#surname} field
         * @param surname - author name
         * @return - builder object {@link AuthorBuilder}
         */
        public AuthorBuilder withSurname(String surname){
            Author.this.setSurname(surname);
            return this;
        }

        /**
         * Method build author entity
         * @return - author object {@link Author}
         */
        public Author buildAuthor(){
            return Author.this;
        }
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
