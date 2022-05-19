package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Author;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Genre;
import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.BookDao;
import by.vlad.library.model.dao.mapper.Mapper;
import by.vlad.library.model.dao.mapper.impl.BookMapper;
import by.vlad.library.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import static by.vlad.library.model.dao.ColumnName.*;

public class BookDaoImpl implements BookDao {
    private static final String SELECT_ALL_BOOKS =
            "SELECT books.id, books.title, authors.id, authors.name, authors.surname, publishers.id, " +
                    "publishers.name, genres.name " +
            "FROM library.books " +
            "JOIN library.authors ON books.authors_id = authors.id " +
            "JOIN library.publishers ON books.publishers_id = publishers.id " +
            "JOIN library.genres ON books.genres_id = genres.id";

    private static final String SELECT_BOOK_BY_ID =
            "SELECT books.id, books.title, books.copies_number, books.publish_year, books.number_of_pages, books.description, authors.id, " +
                    "authors.name, authors.surname, genres.id, genres.name, publishers.id, publishers.name " +
            "FROM library.books " +
            "JOIN library.authors ON books.authors_id = authors.id " +
            "JOIN library.publishers ON books.publishers_id = publishers.id " +
            "JOIN library.genres ON books.genres_id = genres.id " +
            "WHERE books.id = ?";

    private static final String SELECT_NUMBER_OF_BOOKS =
            "SELECT COUNT(*) as count_col FROM books";

    private static final String SELECT_PREVIOUS_BOOKS =
            "SELECT * FROM (SELECT books.id as books_id, title, authors.id as authors_id, authors.name as author_name, " +
                    "authors.surname, publishers.id as publisher_id, " +
                    "publishers.name as publisher_name, genres.name as genre_name " +
                    "FROM library.books " +
                    "LEFT JOIN library.authors ON books.authors_id = authors.id " +
                    "LEFT JOIN library.publishers ON books.publishers_id = publishers.id " +
                    "LEFT JOIN library.genres ON books.genres_id = genres.id " +
                    "WHERE books.id<? ORDER BY books.id DESC LIMIT ?) as reverse_table " +
                    "ORDER BY books_id";

    private static final String SELECT_NEXT_BOOKS =
            "SELECT books.id, books.title, authors.id, authors.name, authors.surname, publishers.id, " +
                    "publishers.name, genres.name " +
                    "FROM library.books " +
                    "JOIN library.authors ON books.authors_id = authors.id " +
                    "JOIN library.publishers ON books.publishers_id = publishers.id " +
                    "JOIN library.genres ON books.genres_id = genres.id " +
                    "WHERE books.id>? ORDER BY books.id LIMIT ?";

    private static final String INSERT_NEW_BOOK =
            "INSERT INTO books (`title`, `copies_number`, `publish_year`, `number_of_pages`, `description`, `authors_id`, `publishers_id`, `genres_id`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_BOOK =
            "UPDATE books " +
                    "SET title = ?," +
                    "    copies_number = ?, " +
                    "    publish_year = ?, " +
                    "    number_of_pages = ?, " +
                    "    description = ?, " +
                    "    authors_id = ? ," +
                    "    publishers_id = ? ," +
                    "    genres_id = ? " +
                    "WHERE id = ?;  ";

    private static final long DEFAULT_ITEM_COUNT_PER_PAGE = 4;

    private static BookDaoImpl instance;

    private BookDaoImpl(){
    }

    public static BookDaoImpl getInstance() {
        if (instance == null){
            instance = new BookDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(Book book) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Book book) throws DaoException {
        return false;
    }

    @Override
    public Book update(Book book) throws DaoException {
        return null;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        return null;
    }

    @Override
    public List<Book> getBooks() throws DaoException {
        List<Book> books;
        Mapper<Book> mapper = BookMapper.getInstance();

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)) {

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                books = mapper.map(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return books;
    }

    @Override
    public Optional<Book> getBookById(long id) throws DaoException {
        Optional<Book> bookOptional = Optional.empty();

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID)){

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Author author = new Author(
                        resultSet.getLong(AUTHORS_ID_COL),
                        resultSet.getString(AUTHORS_NAME_COL),
                        resultSet.getString(AUTHORS_SURNAME_COL)
                );

                Publisher publisher = new Publisher(
                        resultSet.getLong(PUBLISHERS_ID_COL),
                        resultSet.getString(PUBLISHERS_NAME_COL)
                );

                Genre genre = new Genre(
                        resultSet.getLong(GENRES_ID_COL),
                        resultSet.getString(GENRES_NAME_COL)
                );

                Book book = Book.getBuilder()
                        .withId(resultSet.getLong(BOOKS_ID_COL))
                        .withTitle(resultSet.getString(BOOKS_TITLE_COL))
                        .withCopiesNumber(resultSet.getInt(BOOKS_COPIES_NUMBER_COL))
                        .withReleaseYear(Year.of(resultSet.getInt(BOOKS_PUBLISH_YEAR_COL)))
                        .withNumberOfPages(resultSet.getInt(BOOKS_NUMBER_OF_PAGES_COL))
                        .withDescription(resultSet.getString(BOOKS_DESCRIPTION_COL))
                        .withGenre(genre)
                        .withPublisher(publisher)
                        .withAuthor(author)
                        .buildBook();

                bookOptional = Optional.of(book);
            }

        }catch (SQLException e){
            throw  new DaoException(e);
        }

        return bookOptional;
    }

    @Override
    public int findNumberOfBooks() throws DaoException {
        int result;
        int booksNumber = 0;

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NUMBER_OF_BOOKS)){

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                booksNumber = resultSet.getInt(COUNT_COL);
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        result = (int) (booksNumber / DEFAULT_ITEM_COUNT_PER_PAGE);

        if (booksNumber % DEFAULT_ITEM_COUNT_PER_PAGE != 0){
            result++;
        }

        return result;
    }

    @Override
    public List<Book> findNextBooks(long lastId) throws DaoException {
        List<Book> books;
        Mapper<Book> mapper = BookMapper.getInstance();

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEXT_BOOKS)){

            preparePaginationDbRequest(preparedStatement, lastId);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                books = mapper.map(resultSet);
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return books;
    }

    @Override
    public List<Book> findPrevBooks(long firstId) throws DaoException {
        List<Book> books;
        Mapper<Book> mapper = BookMapper.getInstance();

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PREVIOUS_BOOKS)){

            preparePaginationDbRequest(preparedStatement, firstId);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                books = mapper.map(resultSet);
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return books;
    }

    @Override
    public boolean addNewBook(Book book) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_NEW_BOOK)){

            prepareInsertUpdateDbRequest(statement, book, false);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return rows == 1;
    }

    @Override
    public Optional<Book> updateBook(Book book) throws DaoException {
        Optional<Book> bookOptional;
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK)) {

            prepareInsertUpdateDbRequest(statement, book, true);

            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        if (rows == 1){
            bookOptional = Optional.ofNullable(book);
        }else{
            bookOptional = Optional.empty();
        }

        return bookOptional;
    }

    private void prepareInsertUpdateDbRequest(PreparedStatement statement, Book book, boolean isUpdate){
        try {
            if (isUpdate){
                statement.setLong(9, book.getId());
            }

            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getCopiesNumber());
            statement.setInt(3, book.getReleaseYear().getValue());
            statement.setInt(4, book.getNumberOfPages());
            statement.setString(5, book.getDescription());
            statement.setLong(6, book.getAuthor().getId());
            statement.setLong(7, book.getPublisher().getId());
            statement.setLong(8, book.getGenre().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void preparePaginationDbRequest(PreparedStatement statement, long id){
        try {
            statement.setLong(1, id);
            statement.setLong(2, BookDaoImpl.DEFAULT_ITEM_COUNT_PER_PAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
