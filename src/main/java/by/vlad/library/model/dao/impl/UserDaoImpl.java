package by.vlad.library.model.dao.impl;

import by.vlad.library.model.dao.UserDao;
import by.vlad.library.entity.User;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.mapper.Mapper;
import by.vlad.library.model.dao.mapper.impl.UserMapper;
import by.vlad.library.model.pool.ConnectionPool;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT * FROM users, roles " +
            "WHERE roles_id = role_id AND user_email = ? AND user_password = ? AND user_is_banned = 0";

    private static final String SELECT_USER_COUNT_BY_EMAIL =
            "SELECT COUNT(*) as count_col FROM users " +
            "WHERE user_email = ?";

    private static final String INSERT_NEW_USER =
            "INSERT INTO users(`user_name`, `user_login`, `user_surname`, `user_email`, " +
                    "`user_password`, `user_passport_serial_number`, `user_mobile_phone`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_DATA =
            "UPDATE users " +
            "SET user_name = ?," +
            "    user_surname = ?, " +
            "    user_email = ?, " +
            "    user_login = ?, " +
            "    user_passport_serial_number = ?, " +
            "    user_mobile_phone = ? " +
            "WHERE user_email = ?;  ";

    private static final String UPDATE_USER_PASSWORD_DATA =
            "UPDATE users " +
            "SET user_password = ? " +
            "WHERE user_email = ? AND user_password = ?";

    private static final String GET_USER_BY_ID =
            "SELECT * " +
            "FROM users WHERE user_id = ?";

    private static final String FIND_ALL_USERS =
            "SELECT * FROM users, roles WHERE roles_id = 1 AND roles_id = role_id;";

    private static final String UPDATE_USER_STATUS =
            "UPDATE users" +
                    " SET user_is_banned = ? " +
                    " WHERE user_email = ? ";

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
        Mapper<User> userMapper = UserMapper.getInstance();

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                List<User> users = userMapper.map(resultSet);
                if (!users.isEmpty()) {
                    optionalUser = Optional.of(users.get(0));
                }
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
            statement.setString(7,
                    userData.getMobilePhone().length() == 0 ? null
                    : userData.getMobilePhone());

            rows = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return rows == 1;
    }

    @Override
    public boolean isEmailExists(String email) throws DaoException {
        boolean isExists = false;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_COUNT_BY_EMAIL)){

            preparedStatement.setString(1, email);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    isExists = count == 0;
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
    public boolean changeAccountStatus(String userEmail, boolean status) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_STATUS)){

            statement.setBoolean(1, status);
            statement.setString(2, userEmail);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return rows == 1;
    }

    @Override
    public Optional<User> findUserById(long id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        Mapper<User> userMapper = UserMapper.getInstance();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)){

            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()){
                List<User> users = userMapper.map(resultSet);
                if (!users.isEmpty()) {
                    optionalUser = Optional.of(users.get(0));
                }
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return optionalUser;
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users;
        Mapper<User> userMapper = UserMapper.getInstance();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement()){

            try(ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS)) {
                users = userMapper.map(resultSet);
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return users;
    }
}
