package by.vlad.JavaWebProject.model.dao.impl;

import by.vlad.JavaWebProject.entity.Author;
import by.vlad.JavaWebProject.exception.DaoException;
import by.vlad.JavaWebProject.model.dao.AuthorDAO;
import by.vlad.JavaWebProject.model.dao.BasicDAO;
import by.vlad.JavaWebProject.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl extends BasicDAO<Author> implements AuthorDAO {
    private static final String SELECT_AUTHORS_BY_BOOK_ID =
            "SELECT authors.id, authors.name, authors.surname FROM library.authors " +
            "JOIN library.authors_books ON authors_id = authors.id " +
            "JOIN library.books ON authors_books.books_id = books.id " +
            "WHERE books.id = ?";

    private static AuthorDAOImpl instance;

    private AuthorDAOImpl(){
    }

    public static AuthorDAOImpl getInstance() {
        if (instance == null){
            instance = new AuthorDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(Author author) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Author author) throws DaoException {
        return false;
    }

    @Override
    public Author update(Author author) throws DaoException {
        return null;
    }

    @Override
    public List<Author> findAll() throws DaoException {
        return null;
    }

    @Override
    public List<Author> getAuthorsByBookId(long bookId) throws DaoException {
        List<Author> authors = new ArrayList<>();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AUTHORS_BY_BOOK_ID)){

            preparedStatement.setLong(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Author author = Author.getAuthorBuilder()
                        .withId(resultSet.getLong(1))
                        .withName(resultSet.getString(2))
                        .withSurname(resultSet.getString(3))
                        .buildAuthor();

                authors.add(author);
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return authors;
    }
}
