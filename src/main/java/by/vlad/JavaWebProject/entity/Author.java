package by.vlad.JavaWebProject.entity;

public class Author extends AbstractEntity{
    private String name;
    private String surname;

    public Author() {
    }

    public Author(long id) {
        super(id);
    }

    public static AuthorBuilder getAuthorBuilder(){
        return new Author().new AuthorBuilder();
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

    public class AuthorBuilder{
        public AuthorBuilder withId(long id){
            Author.this.setId(id);
            return this;
        }

        public AuthorBuilder withName(String name){
            Author.this.setName(name);
            return this;
        }

        public AuthorBuilder withSurname(String surname){
            Author.this.setSurname(surname);
            return this;
        }

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
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
