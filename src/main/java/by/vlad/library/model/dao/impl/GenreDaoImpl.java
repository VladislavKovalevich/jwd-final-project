package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Genre;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.GenreDao;
import by.vlad.library.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.vlad.library.model.dao.ColumnName.*;

public class GenreDaoImpl implements GenreDao {
    private static final String SELECT_ALL_GENRES = "SELECT genres.id, genres.name FROM genres";

    private static final String IS_GENRE_EXISTS =
            "SELECT COUNT(*) as count_col FROM genres " +
                    "WHERE name = ?";

    private static final String INSERT_GENRE =
            "INSERT INTO genres (`name`) VALUES (?)";

    private static final String UPDATE_GENRE =
            "UPDATE genres " +
                    "SET name = ? " +
                    "WHERE id = ?";

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
            PreparedStatement statement = connection.prepareStatement(INSERT_GENRE)){

            statement.setString(1, genre.getName());

            rows = statement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException(e);
        }
        return rows == 1;
    }

    @Override
    public boolean delete(Genre genre) throws DaoException {
        return false;
    }

    @Override
    public Genre update(Genre genre) throws DaoException {
        return null;
    }

    @Override
    public List<Genre> findAll() throws DaoException {
        List<Genre> genres = new ArrayList<>();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GENRES)) {

            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Genre genre = new Genre();
                    genre.setId(resultSet.getLong(GENRES_ID_COL));
                    genre.setName(resultSet.getString(GENRES_NAME_COL));

                    genres.add(genre);
                }
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return genres;
    }

    @Override
    public boolean isGenreExists(String name) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(IS_GENRE_EXISTS)) {

            statement.setString(1, name);

            try(ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    rows = resultSet.getInt(COUNT_COL);
                }else{
                    rows = 1;
                }
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }

        return rows == 1;
    }

    @Override
    public Optional<Genre> updateGenre(Genre genre) throws DaoException {
        Optional<Genre> optionalGenre;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_GENRE)){

            statement.setString(1, genre.getName());
            statement.setLong(2, genre.getId());

            int rows = statement.executeUpdate();

            if (rows == 1){
                optionalGenre = Optional.of(genre);
            }else{
                optionalGenre = Optional.empty();
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return optionalGenre;
    }
}