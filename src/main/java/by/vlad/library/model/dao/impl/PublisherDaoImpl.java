package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.PublisherDao;
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

/**
 * {@code PublisherDaoImpl} class implements functional of {@link PublisherDao}
 * @see Publisher
 * @see PublisherDao
 * @see by.vlad.library.model.dao.BasicDao
 */
public class PublisherDaoImpl implements PublisherDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_SELECT_ALL_PUBLISHER =
            "SELECT publisher_id, publisher_name " +
                    "FROM publishers";

    private static final String SQL_INSERT_PUBLISHER =
            "INSERT INTO publishers (`publisher_name`) " +
                    "VALUES (?)";

    private static final String SQL_SELECT_COUNT_PUBLISHER_BY_NAME =
            "SELECT COUNT(*) as count_col " +
                    "FROM publishers " +
                    "WHERE publisher_name = ?";

    private static final String SQL_UPDATE_PUBLISHER =
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
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PUBLISHER)){

            statement.setString(1, publisher.getName());
            rows = statement.executeUpdate();

        }catch (SQLException e){
            logger.error("SQL request insert for table library.publishers was failed" + e);
            throw new DaoException("SQL request insert for table library.publishers was failed", e);
        }

        return rows == 1;
    }

    @Override
    public boolean delete(Publisher publisher) throws DaoException {
        logger.error("Unavailable operation to entity Publisher");
        throw new UnsupportedOperationException("Unavailable operation to entity Publisher");
    }

    @Override
    public Publisher update(Publisher publisher) throws DaoException {
        logger.error("Unavailable operation to entity Publisher");
        throw new UnsupportedOperationException("Unavailable operation to entity Publisher");
    }

    @Override
    public List<Publisher> findAll() throws DaoException {
        List<Publisher> publishers = new ArrayList<>();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_PUBLISHER)){

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    Publisher p = new Publisher();

                    p.setId(resultSet.getLong(PUBLISHER_ID_COL));
                    p.setName(resultSet.getString(PUBLISHER_NAME_COL));

                    publishers.add(p);
                }
            }

        }catch (SQLException e){
            logger.error("SQL request findAll for table library.publishers was failed" + e);
            throw new DaoException("SQL request findAll for table library.publishers was failed", e);
        }

        return publishers;
    }

    @Override
    public boolean isPublisherExists(Publisher publisher) throws DaoException{
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COUNT_PUBLISHER_BY_NAME)){

            statement.setString(1, publisher.getName());

            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    rows = resultSet.getInt(COUNT_COL);
                }else {
                    rows = 1;
                }
            }

        }catch (SQLException e){
            logger.error("SQL request isPublisherExists for table library.publishers was failed" + e);
            throw new DaoException("SQL request isPublisherExists for table library.publishers was failed", e);
        }

        return rows == 1;
    }

    @Override
    public Optional<Publisher> updatePublisher(Publisher publisher) throws DaoException {
        Optional<Publisher> optionalPublisher;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PUBLISHER)){

            statement.setString(1, publisher.getName());
            statement.setLong(2, publisher.getId());

            int rows = statement.executeUpdate();

            if (rows == 1){
                optionalPublisher = Optional.of(publisher);
            }else{
                optionalPublisher = Optional.empty();
            }
        }catch (SQLException e){
            logger.error("SQL request updatePublisher for table library.publishers was failed" + e);
            throw new DaoException("SQL request updatePublisher for table library.publishers was failed", e);
        }

        return optionalPublisher;
    }

    @Override
    public Optional<Publisher> addPublisher(Publisher publisher) throws DaoException {
        Optional<Publisher> optionalPublisher;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PUBLISHER)){

            statement.setString(1, publisher.getName());
            int rows = statement.executeUpdate();

            optionalPublisher = rows == 1 ? Optional.of(publisher) : Optional.empty();

        }catch (SQLException e){
            logger.error("SQL request addPublisher for table library.publishers was failed" + e);
            throw new DaoException("SQL request addPublisher for table library.publishers was failed", e);
        }

        return optionalPublisher;
    }
}