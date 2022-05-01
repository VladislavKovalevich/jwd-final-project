package by.vlad.library.model.dao.impl;

import by.vlad.library.model.dao.UserDao;
import by.vlad.library.entity.User;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.pool.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT users.id, users.name, surname, email, password, login, passport_serial_number, mobile_phone, roles.name FROM users, roles " +
            "WHERE roles_id = roles.id AND email = ? AND password = ?";

    private static final String SELECT_USER_COUNT_BY_EMAIL =
            "SELECT COUNT(*) as users_count FROM users " +
            "WHERE email = ?";

    private static final String INSERT_NEW_USER =
            "INSERT INTO users(`name`, `login`, `surname`, `email`, `password`, `passport_serial_number`, `mobile_phone`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_DATA =
            "UPDATE users " +
            "SET name = ?," +
            "    surname = ?, " +
            "    email = ?, " +
            "    login = ?, " +
            "    passport_serial_number = ?, " +
            "    mobile_phone = ? " +
            "WHERE email = ?;  ";

    private static final String UPDATE_USER_PASSWORD_DATA =
            "UPDATE users " +
            "SET password = ? " +
            "WHERE email = ? AND password = ?";

    private static final String GET_USER_BY_ID =
            "SELECT name, surname, email, login, passport_serial_number, mobile_phone " +
            "FROM users WHERE id = ?";

    private static UserDaoImpl instance;

    private UserDaoImpl(){
    }

    public static UserDaoImpl getInstance() {
        if (instance == null){
            instance = new UserDaoImpl();
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
        Optional<User> optionalUser = Optional.empty();

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                User temp = User.getBuilder()
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

                optionalUser = Optional.of(temp);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return optionalUser;
    }

    @Override
    public boolean createNewAccount(User userData) throws DaoException {
        int rows;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER)){

            statement.setString(1, userData.getName());
            statement.setString(2, userData.getLogin());
            statement.setString(3, userData.getSurname());
            statement.setString(4, userData.getEmail());
            statement.setString(5, userData.getPassword());
            statement.setString(6, userData.getPassportSerialNumber());
            statement.setString(7, userData.getMobilePhone());

            rows = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return rows == 1;
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
        int rows;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_DATA)){

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassportSerialNumber());
            statement.setString(6, user.getMobilePhone());
            statement.setString(7, oldEmail);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return rows == 1;
    }

    @Override
    public boolean changeAccountPassword(String email, String password, String newPassword) throws DaoException {
        int rows;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD_DATA)){

            statement.setString(1, newPassword);
            statement.setString(2, email);
            statement.setString(3, password);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return rows == 1;
    }

    @Override
    public boolean changeAccountStatus(long userId, int status) throws DaoException {
        return false;
    }

    @Override
    public Optional<User> findUserById(long id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)){

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                User temp = User.getBuilder()
                        .withId(id)
                        .withName(resultSet.getString(1))
                        .withSurname(resultSet.getString(2))
                        .withEmail(resultSet.getString(3))
                        .withLogin(resultSet.getString(4))
                        .withPassportSerialNumber(resultSet.getString(5))
                        .withMobilePhone(resultSet.getString(6))
                        .buildUser();

                optionalUser = Optional.of(temp);
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return optionalUser;
    }
}
