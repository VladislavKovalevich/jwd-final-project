package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Author;
import by.vlad.library.entity.Book;
import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.BasicDao;
import by.vlad.library.model.dao.BookDao;
import by.vlad.library.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl extends BasicDao<Book> implements BookDao {
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
        List<Book> books = new ArrayList<>();

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Author author = new Author(
                        resultSet.getLong(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );

                Publisher publisher = new Publisher(
                        resultSet.getLong(6),
                        resultSet.getString(7)
                );

                Book book = Book.getBuilder()
                        .withId(resultSet.getLong(1))
                        .withName(resultSet.getString(2))
                        .withAuthor(author)
                        .withPublisher(publisher)
                        .withGenre(resultSet.getString(8))
                        .buildBook();

                books.add(book);
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
}
