package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Author;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.AuthorDao;
import by.vlad.library.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.vlad.library.model.dao.ColumnName.*;

public class AuthorDaoImpl implements AuthorDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String SELECT_ALL_AUTHORS =
            "SELECT * FROM authors";

    private static final String INSERT_AUTHOR =
            "INSERT INTO authors (`author_name`, `author_surname`) VALUES (?, ?)";

    private static final String UPDATE_AUTHOR =
            "UPDATE authors " +
                    "SET author_name = ?," +
                    "    author_surname = ? " +
                    "WHERE author_id = ?";

    private static final String IS_AUTHOR_EXISTS =
            "SELECT COUNT(*) as count_col FROM authors " +
                    "WHERE author_name = ? AND author_surname = ?";

    private static AuthorDaoImpl instance;

    private AuthorDaoImpl(){
    }

    public static AuthorDaoImpl getInstance() {
        if (instance == null){
            instance = new AuthorDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(Author author) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_AUTHOR)){

            statement.setString(1, author.getName());
            statement.setString(2, author.getSurname());

            rows = statement.executeUpdate();

        }catch (SQLException e){
            logger.error("SQL request insert for table library.author was failed" + e);
            throw new DaoException("SQL request insert for table library.author was failed", e);
        }

        return rows == 1;
    }

    @Override
    public boolean delete(Author author) throws DaoException {
        logger.error("Unavailable operation to entity Author");
        throw new UnsupportedOperationException("Unavailable operation to entity Author");
    }

    @Override
    public Author update(Author author) throws DaoException {
        logger.error("Unavailable operation to entity Author");
        throw new UnsupportedOperationException("Unavailable operation to entity Author");
    }

    @Override
    public List<Author> findAll() throws DaoException {
        List<Author> authors = new ArrayList<>();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AUTHORS)){

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    Author author = new Author();

                    author.setId(resultSet.getLong(AUTHOR_ID_COL));
                    author.setName(resultSet.getString(AUTHOR_NAME_COL));
                    author.setSurname(resultSet.getString(AUTHOR_SURNAME_COL));

                    authors.add(author);
                }
            }
        }catch (SQLException e){
            logger.error("SQL request findAll for table library.author was failed" + e);
            throw new DaoException("SQL request findAll for table library.author was failed", e);
        }

        return authors;
    }

    @Override
    public boolean isAuthorExists(String name, String surname) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(IS_AUTHOR_EXISTS)){

            statement.setString(1, name);
            statement.setString(2, surname);

            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    rows = resultSet.getInt(COUNT_COL);
                }else{
                    rows = 1;
                }
            }
        }catch (SQLException e){
            logger.error("SQL request isAuthorExists for table library.author was failed" + e);
            throw new DaoException("SQL request isAuthorExists for table library.author was failed", e);
        }

        return rows == 1;
    }

    @Override
    public Optional<Author> updateAuthor(Author author) throws DaoException {
        Optional<Author> optionalAuthor;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR)){

            statement.setString(1, author.getName());
            statement.setString(2, author.getSurname());
            statement.setLong(3, author.getId());

            int rows = statement.executeUpdate();

            if (rows == 1){
                optionalAuthor = Optional.of(author);
            }else{
                optionalAuthor = Optional.empty();
            }
        }catch (SQLException e){
            logger.error("SQL request updateAuthor for table library.author was failed" + e);
            throw new DaoException("SQL request updateAuthor for table library.author was failed", e);
        }

        return optionalAuthor;
    }

    @Override
    public Optional<Author> addAuthor(Author author) throws DaoException {
        Optional<Author> optionalAuthor;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_AUTHOR)){

            statement.setString(1, author.getName());
            statement.setString(2, author.getSurname());

            int rows = statement.executeUpdate();

            optionalAuthor = rows == 1 ? Optional.of(author) : Optional.empty();

        }catch (SQLException e){
            logger.error("SQL request addAuthor for table library.author was failed" + e);
            throw new DaoException("SQL request addAuthor for table library.author was failed", e);
        }

        return optionalAuthor;
    }
}
