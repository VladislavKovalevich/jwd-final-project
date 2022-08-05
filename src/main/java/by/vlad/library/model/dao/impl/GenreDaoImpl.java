package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Genre;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.GenreDao;
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
 * {@code GenreDaoImpl} class implements functional of {@link GenreDao}
 * @see Genre
 * @see GenreDao
 * @see by.vlad.library.model.dao.BasicDao
 */
public class GenreDaoImpl implements GenreDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_SELECT_ALL_GENRES =
            "SELECT genre_id, genre_name " +
                    "FROM genres";

    private static final String SQL_SELECT_GENRE_COUNT_BY_NAME =
            "SELECT COUNT(*) as count_col " +
                    "FROM genres " +
                    "WHERE genre_name = ?";

    private static final String SQL_INSERT_GENRE =
            "INSERT INTO genres (`genre_name`) " +
                    "VALUES (?)";

    private static final String SQL_UPDATE_GENRE =
            "UPDATE genres " +
                    "SET genre_name = ? " +
                    "WHERE genre_id = ?";

    private static GenreDaoImpl instance;

    private GenreDaoImpl(){
    }

    public static GenreDaoImpl getInstance() {
        if (instance == null){
            instance = new GenreDaoImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(Genre genre) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_GENRE)){

            statement.setString(1, genre.getName());

            rows = statement.executeUpdate();
        }catch (SQLException e){
            logger.error("SQL request insert for table library.genres was failed" + e);
            throw new DaoException("SQL request insert for table library.genres was failed", e);
        }
        return rows == 1;
    }

    @Override
    public boolean delete(Genre genre) throws DaoException {
        logger.error("Unavailable operation to entity Genre");
        throw new UnsupportedOperationException("Unavailable operation to entity Genre");
    }

    @Override
    public Genre update(Genre genre) throws DaoException {
        logger.error("Unavailable operation to entity Genre");
        throw new UnsupportedOperationException("Unavailable operation to entity Genre");
    }

    @Override
    public List<Genre> findAll() throws DaoException {
        List<Genre> genres = new ArrayList<>();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_GENRES)) {

            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Genre genre = new Genre();
                    genre.setId(resultSet.getLong(GENRE_ID_COL));
                    genre.setName(resultSet.getString(GENRE_NAME_COL));

                    genres.add(genre);
                }
            }

        }catch (SQLException e){
            logger.error("SQL request findAll for table library.genres was failed" + e);
            throw new DaoException("SQL request findAll for table library.genres was failed", e);
        }

        return genres;
    }

    @Override
    public boolean isGenreExists(Genre genre) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_GENRE_COUNT_BY_NAME)) {

            statement.setString(1, genre.getName());

            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    rows = resultSet.getInt(COUNT_COL);
                }else{
                    rows = 1;
                }
            }
        }catch (SQLException e){
            logger.error("SQL request isGenreExists for table library.genres was failed" + e);
            throw new DaoException("SQL request isGenreExists for table library.genres was failed", e);
        }

        return rows == 1;
    }

    @Override
    public Optional<Genre> updateGenre(Genre genre) throws DaoException {
        Optional<Genre> optionalGenre;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_GENRE)){

            statement.setString(1, genre.getName());
            statement.setLong(2, genre.getId());

            int rows = statement.executeUpdate();

            if (rows == 1){
                optionalGenre = Optional.of(genre);
            }else{
                optionalGenre = Optional.empty();
            }

        }catch (SQLException e){
            logger.error("SQL request updateGenre for table library.genres was failed" + e);
            throw new DaoException("SQL request updateGenre for table library.genres was failed", e);
        }

        return optionalGenre;
    }

    @Override
    public Optional<Genre> addGenre(Genre genre) throws DaoException {
        Optional<Genre> optionalGenre;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_GENRE)){

            statement.setString(1, genre.getName());

            int rows = statement.executeUpdate();

            optionalGenre = rows == 1 ? Optional.of(genre) : Optional.empty();

        }catch (SQLException e){
            logger.error("SQL request addGenre for table library.genres was failed" + e);
            throw new DaoException("SQL request addGenre for table library.genres was failed", e);
        }
        return optionalGenre;
    }
}
