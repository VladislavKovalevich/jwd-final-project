package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.PublisherDao;
import by.vlad.library.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDaoImpl implements PublisherDao {
    private static final String SELECT_ALL_PUBLISHER = "SELECT * FROM book_publishers";

    private static final String INSERT_AUTHOR =
            "INSERT INTO book_publishers (`name`) VALUES (?)";

    private static final String UPDATE_AUTHOR =
            "UPDATE book_publishers " +
                    "SET name = ? " +
                    "WHERE id = ?";

    private static PublisherDaoImpl instance;

    public static PublisherDaoImpl getInstance(){
        if (instance == null){
            instance = new PublisherDaoImpl();
        }
        return instance;
    }

    private PublisherDaoImpl(){}

    @Override
    public boolean insert(Publisher publisher) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_AUTHOR)){

            statement.setString(1, publisher.getName());
            rows = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return rows == 1;
    }

    @Override
    public boolean delete(Publisher publisher) throws DaoException {
        return false;
    }

    @Override
    public Publisher update(Publisher publisher) throws DaoException {
        return null;
    }

    @Override
    public List<Publisher> findAll() throws DaoException {
        List<Publisher> publishers = new ArrayList<>();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PUBLISHER)){

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    Publisher p = new Publisher();

                    p.setId(resultSet.getLong(1));
                    p.setName(resultSet.getString(2));

                    publishers.add(p);
                }
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return publishers;
    }

    @Override
    public boolean isPublisherExists(String publisherName) {
        return false;
    }

    @Override
    public boolean updatePublisher(String publisherName) throws DaoException {
        return false;
    }
}
