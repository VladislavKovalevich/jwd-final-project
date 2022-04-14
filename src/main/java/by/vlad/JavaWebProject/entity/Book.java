package by.vlad.JavaWebProject.entity;

import java.time.Year;

public class Book extends AbstractEntity{
    private String title;
    private int copiesNumber;
    private Year releaseYear;
    private int numberOfPages;
    private String description;
    private Genre genre;
    private Author author;
    private Publisher publisher;

    public Book(long id) {
        super(id);
    }

    public Book(){
        super();
    }

    public static BookBuilder getBuilder(){
        return new Book().new BookBuilder();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCopiesNumber() {
        return copiesNumber;
    }

    public void setCopiesNumber(int copiesNumber) {
        this.copiesNumber = copiesNumber;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public class BookBuilder {
        public BookBuilder withId(long id){
            Book.this.setId(id);
            return this;
        }

        public BookBuilder withName(String name){
            Book.this.setTitle(name);
            return this;
        }

        public BookBuilder withCopiesNumber(int copiesNumber){
            Book.this.setCopiesNumber(copiesNumber);
            return this;
        }

        public BookBuilder withNumberOfPages(int numberOfPage){
            Book.this.setNumberOfPages(numberOfPage);
            return this;
        }

        public BookBuilder withReleaseYear(Year year){
            Book.this.setReleaseYear(year);
            return this;
        }

        public BookBuilder withGenre(String genre){
            Book.this.setGenre(Genre.getGenre(genre));
            return this;
        }

        public BookBuilder withDescription(String description){
            Book.this.setDescription(description);
            return this;
        }

        public BookBuilder withAuthor(Author author){
            Book.this.setAuthor(author);
            return this;
        }

        public BookBuilder withPublisher(Publisher publisher){
            Book.this.setPublisher(publisher);
            return this;
        }

        public Book buildBook(){
            return Book.this;
        }
    }
}
