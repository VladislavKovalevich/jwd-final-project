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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * {@code BookBuilder} class to build {@link Book}
     */
    public class BookBuilder {
        public BookBuilder withId(long id){
            Book.this.setId(id);
            return this;
        }

        public BookBuilder withTitle(String name){
            Book.this.setTitle(name);
            return this;
        }

        public BookBuilder withCopiesNumber(int copiesNumber){
            Book.this.setCopiesNumber(copiesNumber);
            return this;
        }

        public BookBuilder withNumberOfPages(int numberOfPages){
            Book.this.setNumberOfPages(numberOfPages);
            return this;
        }

        public BookBuilder withReleaseYear(Year year){
            Book.this.setReleaseYear(year);
            return this;
        }

        public BookBuilder withGenre(Genre genre){
            Book.this.setGenre(genre);
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
