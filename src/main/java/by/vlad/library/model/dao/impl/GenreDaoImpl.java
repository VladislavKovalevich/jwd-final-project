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

public class GenreDaoImpl implements GenreDao {
    private static final String SELECT_ALL_GENRES = "SELECT * FROM genres";

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
        return false;
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
                    genre.setId(resultSet.getLong(1));
                    genre.setName(resultSet.getString(2));

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
        return false;
    }

    @Override
    public boolean updateGenre(String name) throws DaoException {
        return false;
    }
}
