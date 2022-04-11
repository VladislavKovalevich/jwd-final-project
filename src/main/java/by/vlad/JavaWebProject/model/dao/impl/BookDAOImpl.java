package by.vlad.JavaWebProject.model.dao.impl;

import by.vlad.JavaWebProject.entity.Book;
import by.vlad.JavaWebProject.entity.Genre;
import by.vlad.JavaWebProject.exception.DaoException;
import by.vlad.JavaWebProject.model.dao.BasicDAO;
import by.vlad.JavaWebProject.model.dao.BookDAO;
import by.vlad.JavaWebProject.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAOImpl extends BasicDAO<Book> implements BookDAO {
    private static final String SELECT_ALL_BOOKS =
            "SELECT books.id, books.name, copies_number, release_year, number_of_pages, price, genres.name " +
                    "FROM books JOIN genres ON genres.id = books.id";

    private static final String SELECT_BOOK_BY_ID =
            "SELECT books.id, books.name, copies_number, release_year, number_of_pages, price, genres.name " +
            "FROM books JOIN genres ON genres.id = books.id WHERE books.id = ?";

    private static BookDAOImpl instance;

    private BookDAOImpl(){
    }

    public static BookDAOImpl getInstance() {
        if (instance == null){
            instance = new BookDAOImpl();
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
                Book book = Book.getBuilder()
                        .withId(resultSet.getLong(1))
                        .withName(resultSet.getString(2))
                        .withCopiesNumber(resultSet.getInt(3))
                        .withReleaseYear(Year.of(resultSet.getInt(4)))
                        .withNumberOfPages(resultSet.getInt(5))
                        .withPrice(resultSet.getDouble(6))
                        .withGenre(resultSet.getString(7))
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
                Book book = Book.getBuilder()
                        .withId(resultSet.getLong(1))
                        .withName(resultSet.getString(2))
                        .withCopiesNumber(resultSet.getInt(3))
                        .withReleaseYear(Year.of(resultSet.getInt(4)))
                        .withNumberOfPages(resultSet.getInt(5))
                        .withPrice(resultSet.getDouble(6))
                        .withGenre(resultSet.getString(7))
                        .buildBook();

                bookOptional = Optional.of(book);
            }

        }catch (SQLException e){
            throw  new DaoException(e);
        }

        return bookOptional;
    }
}
