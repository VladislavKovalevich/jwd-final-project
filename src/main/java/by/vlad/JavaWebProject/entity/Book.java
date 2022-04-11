package by.vlad.JavaWebProject.entity;

import java.time.Year;

public class Book extends AbstractEntity{
    private String name;
    private int copiesNumber;
    private Year releaseYear;
    private int numberOfPages;
    private double price;
    private Genre genre;

    public Book(long id) {
        super(id);
    }

    public Book(){
        super();
    }

    public static BookBuilder getBuilder(){
        return new Book().new BookBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public class BookBuilder {
        public BookBuilder withId(long id){
            Book.this.setId(id);
            return this;
        }

        public BookBuilder withName(String name){
            Book.this.setName(name);
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
            Book.this.setGenre(Genre.getRole(genre));
            return this;
        }

        public BookBuilder withPrice(double price){
            Book.this.setPrice(price);
            return this;
        }

        public Book buildBook(){
            return Book.this;
        }
    }
}
