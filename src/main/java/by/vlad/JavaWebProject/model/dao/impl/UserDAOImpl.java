package by.vlad.JavaWebProject.model.dao.impl;

import by.vlad.JavaWebProject.model.dao.BasicDAO;
import by.vlad.JavaWebProject.model.dao.UserDAO;
import by.vlad.JavaWebProject.entity.User;
import by.vlad.JavaWebProject.exception.DaoException;
import by.vlad.JavaWebProject.model.pool.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends BasicDAO<User> implements UserDAO {
    public static final String SELECT_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT users.id, users.name, surname, email, password, roles.name FROM users, roles " +
            "WHERE roles_id = roles.id AND email = ? AND password = ?";

    private static UserDAOImpl instance;

    private UserDAOImpl(){
    }

    public static UserDAOImpl getInstance() {
        if (instance == null){
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws DaoException {
        Optional<User> user = Optional.empty();

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            String passFromDB;

            if (resultSet.next()){
                User curr = User.getBuilder()
                        .withId(resultSet.getLong(1))
                        .withName(resultSet.getString(2))
                        .withSurname(resultSet.getString(3))
                        .withEmail(resultSet.getString(4))
                        .withPassword(resultSet.getString(5))
                        .withRole(resultSet.getString(6))
                        .buildUser();

                user = Optional.of(curr);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return user;
    }
}
