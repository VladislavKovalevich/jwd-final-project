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
import java.util.Optional;

import static by.vlad.library.model.dao.ColumnName.*;

public class PublisherDaoImpl implements PublisherDao {
    private static final String SELECT_ALL_PUBLISHER = "SELECT publisher_id, publisher_name FROM publishers";

    private static final String INSERT_AUTHOR =
            "INSERT INTO publishers (`publisher_name`) VALUES (?)";

    private static final String IS_PUBLISHER_EXISTS =
            "SELECT COUNT(*) as count_col FROM publishers " +
                    "WHERE publisher_name = ?";

    private static final String UPDATE_PUBLISHER =
            "UPDATE publishers " +
                    "SET publisher_name = ? " +
                    "WHERE publisher_id = ?";

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

                    p.setId(resultSet.getLong(PUBLISHER_ID_COL));
                    p.setName(resultSet.getString(PUBLISHER_NAME_COL));

                    publishers.add(p);
                }
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return publishers;
    }

    @Override
    public boolean isPublisherExists(String publisherName) throws DaoException{
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(IS_PUBLISHER_EXISTS)){

            statement.setString(1, publisherName);

            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    rows = resultSet.getInt(COUNT_COL);
                }else {
                    rows = 1;
                }
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return rows == 1;
    }

    @Override
    public Optional<Publisher> updatePublisher(Publisher publisher) throws DaoException {
        Optional<Publisher> optionalPublisher;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PUBLISHER)){

            statement.setString(1, publisher.getName());
            statement.setLong(2, publisher.getId());

            int rows = statement.executeUpdate();

            if (rows == 1){
                optionalPublisher = Optional.of(publisher);
            }else{
                optionalPublisher = Optional.empty();
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }

        return optionalPublisher;
    }
}