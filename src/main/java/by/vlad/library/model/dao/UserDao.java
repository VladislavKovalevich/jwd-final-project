package by.vlad.library.model.dao;

import by.vlad.library.entity.User;
import by.vlad.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * {@code UserDao} extends {@link UserDao} interface and
 *  represent dao functional of the {@link User} class
 * @see BasicDao
 * @see User
 */
public interface UserDao extends BasicDao<User> {
    /**
     * Find user by login and password
     * @param login user login
     * @param password user password
     * @return object {@link User} boxed in {@link Optional} class or if user not found - empty Optional object
     * @throws DaoException if request from database was failed
     */
    Optional<User> authenticate(String login, String password) throws DaoException;

    /**
     * Insert user info in database
     * @param userData user entity
     * @return true if user was inserted, false - if wasn't
     * @throws DaoException if request from database was failed
     */
    boolean createNewAccount(User userData) throws DaoException;

    /**
     * Find user by email address
     * @param email user email
     * @return true if user with current email exists, false - if doesn't
     * @throws DaoException if request from database was failed
     */
    boolean isEmailExists(String email) throws DaoException;

    /**
     * Update user info in database
     * @param user user entity
     * @param oldEmail old user email
     * @return true if user info was updated, false - if wasn't
     * @throws DaoException if request from database was failed
     */
    boolean updateUserAccountData(User user, String oldEmail) throws DaoException;

    /**
     * Update user password in database
     * @param email user email
     * @param password current user password
     * @param newPassword new user password
     * @return true if password was changed, false - if wasn't
     * @throws DaoException if request from database was failed
     */
    boolean changeAccountPassword(String email, String password, String newPassword) throws DaoException;

    /**
     * Update user account status
     * @param userEmail user email
     * @param status user status (true - block, false - unblock)
     * @return true if status was changed, false - if wasn't
     * @throws DaoException if request from database was failed
     */
    boolean changeAccountStatus(String userEmail, boolean status) throws DaoException;

    /**
     * Find user by id from database
     * @param id user identifier
     * @return order object boxed in {@link Optional} class
     * @throws DaoException if request from database was failed
     */
    Optional<User> findUserById(long id) throws DaoException;

    /**
     * Find all users from database
     * @return user list or empty list
     * @throws DaoException if request from database was failed
     */
    List<User> findAllUsers() throws DaoException;

    /**
     * Update account password
     * @param email user email
     * @param encodedNewPass new password
     * @return boolean when password was updated, false - if wasn't
     * @throws DaoException if request from database was failed
     */
    boolean changeAccountPasswordByCode(String email, String encodedNewPass) throws DaoException;
}