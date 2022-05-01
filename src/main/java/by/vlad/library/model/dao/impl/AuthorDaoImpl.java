package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Author;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.AuthorDao;
import by.vlad.library.model.dao.BasicDao;
import by.vlad.library.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private static final String SELECT_ALL_AUTHORS =
            "SELECT * FROM authors";

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
        List<Author> authors = new ArrayList<>();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AUTHORS)){

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    Author author = new Author();

                    author.setId(resultSet.getLong(1));
                    author.setName(resultSet.getString(2));
                    author.setSurname(resultSet.getString(3));

                    authors.add(author);
                }
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        return authors;
    }

    @Override
    public boolean isAuthorExists(String name, String surname) {
        return false;
    }
}
