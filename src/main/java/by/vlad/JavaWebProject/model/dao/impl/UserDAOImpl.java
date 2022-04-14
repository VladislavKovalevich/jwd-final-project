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
            "SELECT users.id, users.name, surname, email, password, login, passport_serial_number, mobile_phone, roles.name FROM users, roles " +
            "WHERE roles_id = roles.id AND email = ? AND password = ?";

    public static final String SELECT_USER_COUNT_BY_EMAIL =
            "SELECT COUNT(*) as users_count FROM users " +
            "WHERE email = ?";

    public static final String INSERT_NEW_USER =
            "INSERT INTO users(`name`, `login`, `surname`, `email`, `password`, `passport_serial_number`, `mobile_phone`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_USER_DATA =
            "UPDATE users " +
            "SET name = ?," +
            "    surname = ?, " +
            "    email = ?, " +
            "    login = ?, " +
            "    passport_serial_number = ?, " +
            "    mobile_phone = ? " +
            "WHERE email = ?;  ";

    public static final String UPDATE_USER_PASSWORD_DATA =
            "UPDATE users " +
            "SET password = ? " +
            "WHERE email = ? AND password = ?";

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

            if (resultSet.next()){
                User curr = User.getBuilder()
                        .withId(resultSet.getLong(1))
                        .withName(resultSet.getString(2))
                        .withSurname(resultSet.getString(3))
                        .withEmail(resultSet.getString(4))
                        .withPassword(resultSet.getString(5))
                        .withLogin(resultSet.getString(6))
                        .withPassportSerialNumber(resultSet.getString(7))
                        .withMobilePhone(resultSet.getString(8))
                        .withRole(resultSet.getString(9))
                        .buildUser();

                user = Optional.of(curr);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return user;
    }

    @Override
    public boolean createNewAccount(User userData) throws DaoException {
        int row_count;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER)){

            statement.setString(1, userData.getName());
            statement.setString(2, userData.getLogin());
            statement.setString(3, userData.getSurname());
            statement.setString(4, userData.getEmail());
            statement.setString(5, userData.getPassword());
            statement.setString(6, userData.getPassportSerialNumber());
            statement.setString(7, userData.getMobilePhone());

            row_count = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return row_count == 1;
    }

    @Override
    public boolean isEmailExists(String email) throws DaoException {
        boolean isExists = true;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_COUNT_BY_EMAIL)){

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int count = resultSet.getInt(1);

                if (count == 0){
                    isExists = false;
                }
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }

        return isExists;
    }

    @Override
    public boolean updateUserAccountData(User user, String oldEmail) throws DaoException {
        int row_count = 0;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_DATA)){

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassportSerialNumber());
            statement.setString(6, user.getMobilePhone());
            statement.setString(7, oldEmail);

            row_count = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return row_count == 1;
    }

    @Override
    public boolean changeAccountPassword(String email, String password, String newPassword) throws DaoException {
        int row_count;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD_DATA)){

            statement.setString(1, newPassword);
            statement.setString(2, email);
            statement.setString(3, password);

            row_count = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return row_count == 1;
    }
}
