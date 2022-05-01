package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Author;
import by.vlad.library.entity.Book;
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

public class BookDaoImpl implements BookDao {
    private static final String SELECT_ALL_BOOKS =
            "SELECT books.id, title, authors.id, authors.name, authors.surname, book_publishers.id, " +
                    "book_publishers.name, genres.name " +
            "FROM library.books " +
            "JOIN library.authors ON books.authors_id = authors.id " +
            "JOIN library.book_publishers ON books.book_publishers_id = book_publishers.id " +
            "JOIN library.genres ON books.genres_id = genres.id";

    private static final String SELECT_BOOK_BY_ID =
            "SELECT books.id, books.title, copies_number, publish_year, number_of_pages, description, authors.id, " +
                    "authors.name, authors.surname, genres.name, book_publishers.id, book_publishers.name " +
            "FROM library.books " +
            "JOIN library.authors ON books.authors_id = authors.id " +
            "JOIN library.book_publishers ON books.book_publishers_id = book_publishers.id " +
            "JOIN library.genres ON books.genres_id = genres.id " +
            "WHERE books.id = ?";

    private static final String SELECT_NUMBER_OF_BOOKS =
            "SELECT COUNT(*) as books_count FROM books";

    private static final String SELECT_PREVIOUS_BOOKS =
            "SELECT * FROM (SELECT books.id as books_id, title, authors.id as authors_id, authors.name as author_name, " +
                    "authors.surname, book_publishers.id as publisher_id, " +
                    "book_publishers.name as publisher_name, genres.name as genre_name " +
                    "FROM library.books " +
                    "LEFT JOIN library.authors ON books.authors_id = authors.id " +
                    "LEFT JOIN library.book_publishers ON books.book_publishers_id = book_publishers.id " +
                    "LEFT JOIN library.genres ON books.genres_id = genres.id " +
                    "WHERE books.id<? ORDER BY books.id DESC LIMIT ?) as reverse_table " +
                    "ORDER BY books_id";

    private static final String SELECT_NEXT_BOOKS =
            "SELECT books.id, title, authors.id, authors.name, authors.surname, book_publishers.id, " +
                    "book_publishers.name, genres.name " +
                    "FROM library.books " +
                    "JOIN library.authors ON books.authors_id = authors.id " +
                    "JOIN library.book_publishers ON books.book_publishers_id = book_publishers.id " +
                    "JOIN library.genres ON books.genres_id = genres.id " +
                    "WHERE books.id>? ORDER BY books.id LIMIT ?";

    private static final long DEFAULT_ITEM_PER_PAGE = 4;

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
                        resultSet.getLong(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                );

                Publisher publisher = new Publisher(
                        resultSet.getLong(11),
                        resultSet.getString(12)
                );

                Book book = Book.getBuilder()
                        .withId(resultSet.getLong(1))
                        .withName(resultSet.getString(2))
                        .withCopiesNumber(resultSet.getInt(3))
                        .withReleaseYear(Year.of(resultSet.getInt(4)))
                        .withNumberOfPages(resultSet.getInt(5))
                        .withDescription(resultSet.getString(6))
                        .withGenre(resultSet.getString(10))
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
                booksNumber = resultSet.getInt(1);
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        result = (int) (booksNumber / DEFAULT_ITEM_PER_PAGE);

        if (booksNumber % DEFAULT_ITEM_PER_PAGE != 0){
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

            preparedStatement.setLong(1, lastId);
            preparedStatement.setLong(2, DEFAULT_ITEM_PER_PAGE);

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

            preparedStatement.setLong(1, firstId);
            preparedStatement.setLong(2, DEFAULT_ITEM_PER_PAGE);

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
        return false;
    }
}
