package by.vlad.library.model.dao.impl;

import by.vlad.library.model.dao.UserDao;
import by.vlad.library.entity.User;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.mapper.Mapper;
import by.vlad.library.model.dao.mapper.impl.UserMapper;
import by.vlad.library.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * {@code UserDaoImpl} class implements functional of {@link UserDao}
 * @see User
 * @see UserDao
 * @see by.vlad.library.model.dao.BasicDao
 */
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_SELECT_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT * FROM users, roles " +
            "WHERE roles_id = role_id AND user_email = ? AND user_password = ?";

    private static final String SQL_SELECT_USER_COUNT_BY_EMAIL =
            "SELECT COUNT(*) as count_col FROM users " +
            "WHERE user_email = ?";

    private static final String SQL_INSERT_USER =
            "INSERT INTO users(`user_name`, `user_login`, `user_surname`, `user_email`, " +
                    "`user_password`, `user_passport_serial_number`, `user_mobile_phone`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_USER =
            "UPDATE users " +
            "SET user_name = ?," +
            "    user_surname = ?, " +
            "    user_email = ?, " +
            "    user_login = ?, " +
            "    user_passport_serial_number = ?, " +
            "    user_mobile_phone = ? " +
            "WHERE user_email = ?;  ";

    private static final String SQL_UPDATE_USER_PASSWORD_BY_EMAIL_AND_PASSWORD =
            "UPDATE users " +
            "SET user_password = ? " +
            "WHERE user_email = ? AND user_password = ?";

    private static final String SQL_UPDATE_USER_PASSWORD_BY_EMAIL =
            "UPDATE users " +
            "SET user_password = ? " +
            "WHERE user_email = ? ";

    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT * " +
            "FROM users, roles WHERE user_id = ? AND roles_id = role_id";

    private static final String SQL_SELECT_ALL_USERS =
            "SELECT * FROM users, roles WHERE roles_id = 1 AND roles_id = role_id;";

    private static final String SQL_UPDATE_USER_STATUS =
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
        logger.error("Unavailable operation to entity User");
        throw new UnsupportedOperationException("Unavailable operation to entity User");
    }

    @Override
    public boolean delete(User user) {
        logger.error("Unavailable operation to entity User");
        throw new UnsupportedOperationException("Unavailable operation to entity User");
    }

    @Override
    public User update(User user) {
        logger.error("Unavailable operation to entity User");
        throw new UnsupportedOperationException("Unavailable operation to entity User");
    }

    @Override
    public List<User> findAll() {
        logger.error("Unavailable operation to entity User");
        throw new UnsupportedOperationException("Unavailable operation to entity User");
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        Mapper<User> userMapper = UserMapper.getInstance();

        try(Connection connection  = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL_AND_PASSWORD)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                List<User> users = userMapper.map(resultSet);
                if (!users.isEmpty()) {
                    optionalUser = Optional.of(users.get(0));
                }
            }
        } catch (SQLException e) {
            logger.error("SQL request authenticate for table library.users was failed" + e);
            throw new DaoException("SQL request authenticate for table library.users was failed", e);
        }

        return optionalUser;
    }

    @Override
    public boolean createNewAccount(User userData) throws DaoException {
        int rows;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER)){

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
            logger.error("SQL request createNewAccount for table library.users was failed" + e);
            throw new DaoException("SQL request createNewAccount for table library.users was failed", e);
        }

        return rows == 1;
    }

    @Override
    public boolean isEmailExists(String email) throws DaoException {
        boolean isExists = false;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_COUNT_BY_EMAIL)){

            preparedStatement.setString(1, email);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    isExists = count >= 1;
                }
            }
        }catch (SQLException e) {
            logger.error("SQL request isEmailExists for table library.users was failed" + e);
            throw new DaoException("SQL request isEmailExists for table library.users was failed", e);
        }

        return isExists;
    }

    @Override
    public boolean updateUserAccountData(User user, String oldEmail) throws DaoException {
        int rows;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)){

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getPassportSerialNumber());
            statement.setString(6, user.getMobilePhone());
            statement.setString(7, oldEmail);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            logger.error("SQL request updateUserAccountData for table library.users was failed" + e);
            throw new DaoException("SQL request updateUserAccountData for table library.users was failed", e);
        }

        return rows == 1;
    }

    @Override
    public boolean changeAccountPassword(String email, String password, String newPassword) throws DaoException {
        int rows;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD_BY_EMAIL_AND_PASSWORD)){

            statement.setString(1, newPassword);
            statement.setString(2, email);
            statement.setString(3, password);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            logger.error("SQL request changeAccountPassword for table library.users was failed" + e);
            throw new DaoException("SQL request changeAccountPassword for table library.users was failed", e);
        }

        return rows == 1;
    }

    @Override
    public boolean changeAccountStatus(String userEmail, boolean status) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_STATUS)){

            statement.setBoolean(1, status);
            statement.setString(2, userEmail);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            logger.error("SQL request changeAccountStatus for table library.users was failed" + e);
            throw new DaoException("SQL request changeAccountStatus for table library.users was failed", e);
        }

        return rows == 1;
    }

    @Override
    public Optional<User> findUserById(long id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        Mapper<User> userMapper = UserMapper.getInstance();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)){

            statement.setLong(1, id);

            try(ResultSet resultSet = statement.executeQuery()){
                List<User> users = userMapper.map(resultSet);
                if (!users.isEmpty()) {
                    optionalUser = Optional.of(users.get(0));
                }
            }

        }catch (SQLException e){
            logger.error("SQL request findUserById for table library.users was failed" + e);
            throw new DaoException("SQL request findUserById for table library.users was failed", e);
        }

        return optionalUser;
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users;
        Mapper<User> userMapper = UserMapper.getInstance();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement()){

            try(ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS)) {
                users = userMapper.map(resultSet);
            }

        }catch (SQLException e){
            logger.error("SQL request findAllUsers for table library.users was failed" + e);
            throw new DaoException("SQL request findAllUsers for table library.users was failed", e);
        }

        return users;
    }

    @Override
    public boolean changeAccountPasswordByCode(String email, String encodedNewPass) throws DaoException {
        int rows = 0;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD_BY_EMAIL)){

            statement.setString(1, encodedNewPass);
            statement.setString(2, email);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            logger.error("SQL request changeAccountPassword for table library.users was failed" + e);
            throw new DaoException("SQL request changeAccountPassword for table library.users was failed", e);
        }

        return rows == 1;
    }
}
