package by.vlad.library.entity;

import java.time.Year;

/**
 * {@code Book} class represent a book entity
 * @see AbstractEntity
 */
public class Book extends AbstractEntity{
    /** book title */
    private String title;

    /** number of copies of the book */
    private int copiesNumber;

    /** the year, when the book was published */
    private Year releaseYear;

    /** number of pages in a book */
    private int numberOfPages;

    /** description of book */
    private String description;

    /** book genre
     * @see Genre
     */
    private Genre genre;

    /** book author
     * @see Author
     */
    private Author author;

    /** book publisher house
     * @see Publisher
     */
    private Publisher publisher;

    /** image of books cover
     * @see Image
     */
    private Image image;

    public Book(long id) {
        super(id);
    }

    public Book(){
        super();
    }

    /**
     * {@code getBuilder} method to get {@link BookBuilder}
     * @return {@link BookBuilder}
     */
    public static BookBuilder getBuilder(){
        return new Book().new BookBuilder();
    }

    /**
     * Getter method for {@link Book#title}
     * @return - field value {@link Book#title}
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method for {@link Book#title}
     * @param title - value for field {@link Book#title}
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter method for {@link Book#copiesNumber}
     * @return - field value {@link Book#copiesNumber}
     */
    public int getCopiesNumber() {
        return copiesNumber;
    }

    /**
     * Setter method for {@link Book#copiesNumber}
     * @param copiesNumber - value for field {@link Book#copiesNumber}
     */
    public void setCopiesNumber(int copiesNumber) {
        this.copiesNumber = copiesNumber;
    }

    /**
     * Getter method for {@link Book#releaseYear}
     * @return - field value {@link Book#releaseYear}
     */
    public Year getReleaseYear() {
        return releaseYear;
    }

    /**
     * Setter method for {@link Book#releaseYear}
     * @param releaseYear - value for field {@link Book#releaseYear}
     */
    public void setReleaseYear(Year releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Getter method for {@link Book#numberOfPages}
     * @return - field value {@link Book#numberOfPages}
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Setter method for {@link Book#numberOfPages}
     * @param numberOfPages - value for field {@link Book#numberOfPages}
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * Getter method for {@link Book#description}
     * @return - field value {@link Book#description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method for {@link Book#description}
     * @param description - value for field {@link Book#description}
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method for {@link Book#genre}
     * @return - field value {@link Book#genre}
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Setter method for {@link Book#genre}
     * @param genre - value for field {@link Book#genre}
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Getter method for {@link Book#author}
     * @return - field value {@link Book#author}
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Setter method for {@link Book#author}
     * @param author - value for field {@link Book#author}
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Getter method for {@link Book#publisher}
     * @return - field value {@link Book#publisher}
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * Setter method for {@link Book#publisher}
     * @param publisher - value for field {@link Book#publisher}
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * Getter method for {@link Book#image}
     * @return - field value {@link Book#image}
     */
    public Image getImage() {
        return image;
    }

    /**
     * Setter method for {@link Book#image}
     * @param image - value for field {@link Book#image}
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * {@code BookBuilder} class to build {@link Book}
     */
    public class BookBuilder {
        /**
         * Method defines {@link AbstractEntity#id} field
         * @param id - book id
         * @return - builder object {@link Book.BookBuilder}
         */
        public BookBuilder withId(long id){
            Book.this.setId(id);
            return this;
        }

        /**
         * Method defines {@link Book#title} field
         * @param name - book title name
         * @return - builder object {@link Book.BookBuilder}
         */
        public BookBuilder withTitle(String name){
            Book.this.setTitle(name);
            return this;
        }

        /**
         * Method defines {@link Book#copiesNumber} field
         * @param copiesNumber - book copies number
         * @return - builder object {@link Book.BookBuilder}
         */
        public BookBuilder withCopiesNumber(int copiesNumber){
            Book.this.setCopiesNumber(copiesNumber);
            return this;
        }

        /**
         * Method defines {@link Book#numberOfPages} field
         * @param numberOfPages - book number of pages
         * @return - builder object {@link Book.BookBuilder}
         */
        public BookBuilder withNumberOfPages(int numberOfPages){
            Book.this.setNumberOfPages(numberOfPages);
            return this;
        }

        /**
         * Method defines {@link Book#releaseYear} field
         * @param year - publish year
         * @return - builder object {@link Book.BookBuilder}
         */
        public BookBuilder withReleaseYear(Year year){
            Book.this.setReleaseYear(year);
            return this;
        }

        /**
         * Method defines {@link Book#genre} field
         * @param genre - book genre
         * @return - builder object {@link Book.BookBuilder}
         */
        public BookBuilder withGenre(Genre genre){
            Book.this.setGenre(genre);
            return this;
        }

        /**
         * Method defines {@link Book#description} field
         * @param description - book description
         * @return - builder object {@link Book.BookBuilder}
         */
        public BookBuilder withDescription(String description){
            Book.this.setDescription(description);
            return this;
        }

        /**
         * Method defines {@link Book#author} field
         * @param author - book author
         * @return - builder object {@link Book.BookBuilder}
         */
        public BookBuilder withAuthor(Author author){
            Book.this.setAuthor(author);
            return this;
        }

        /**
         * Method defines {@link Book#publisher} field
         * @param publisher - book publisher
         * @return - builder object {@link Book.BookBuilder}
         */
        public BookBuilder withPublisher(Publisher publisher){
            Book.this.setPublisher(publisher);
            return this;
        }

        /**
         * Method defines {@link Book#image} field
         * @param image - book cover image
         * @return - builder object {@link Book.BookBuilder}
         */
        public BookBuilder withImage(Image image){
            Book.this.setImage(image);
            return this;
        }

        /**
         * Method build book entity
         * @return - book object {@link Book}
         */
        public Book buildBook(){
            return Book.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (copiesNumber != book.copiesNumber) return false;
        if (numberOfPages != book.numberOfPages) return false;
        if (!title.equals(book.title)) return false;
        if (!releaseYear.equals(book.releaseYear)) return false;
        if (!description.equals(book.description)) return false;
        if (!genre.equals(book.genre)) return false;
        if (!author.equals(book.author)) return false;
        if (!publisher.equals(book.publisher)) return false;
        return image.equals(book.image);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + copiesNumber;
        result = 31 * result + releaseYear.hashCode();
        result = 31 * result + numberOfPages;
        result = 31 * result + description.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + image.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Book{" + "title='").append(title).append('\'')
                .append(", copiesNumber=").append(copiesNumber)
                .append(", releaseYear=").append(releaseYear)
                .append(", numberOfPages=").append(numberOfPages)
                .append(", description='").append(description).append('\'')
                .append(", genre=").append(genre)
                .append(", author=").append(author)
                .append(", publisher=").append(publisher)
                .append(", image=").append(image).append('}')
                .toString();
    }
}
