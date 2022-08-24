package by.vlad.library.model.service;

import by.vlad.library.entity.User;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code UserService} represent a functional of business logic to work with {@link User} class
 */
public interface UserService {
    /**
     * {@code USER_EXISTS_MARKER} constant represent string marker to mark, that user with some params is already exists
     */
    String USER_MAP_MARKER = "user marker";

    /**
     * Authenticate user by email and password
     * @param userData map with user data
     * @return object user boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<User> authenticate(Map<String, String> userData) throws ServiceException;

    /**
     * Create new account
     * @param userData map with new user data
     * @return true if user was created, false - if wasn't
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean createNewAccount(Map<String, String> userData) throws ServiceException;

    /**
     * Update user data
     * @param userData map with user data
     * @return true if data was updated, false - if wasn't
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean updatePersonalData(Map<String, String> userData) throws ServiceException;

    /**
     * Change user password
     * @param password map with password data, include user email, old password, new password and repeated new password
     * @return true if password was changed, false - if wasn't
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean changePassword(Map<String, String> password) throws ServiceException;

    /**
     * Find user by identifier
     * @param id user identifier
     * @return object user boxed in {@link Optional} class or empty Optional object
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    Optional<User> findUserById(long id) throws ServiceException;

    /**
     * Get all users
     * @return user list or empty list
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    List<User> getAllUsers() throws ServiceException;

    /**
     * Change account status
     * @param email user email
     * @param status new user status (true - block, false - unblock)
     * @return true if the operation completed successfully, false otherwise
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean changeAccountStatus(String email, boolean status) throws ServiceException;

    /**
     * Send secret code on user email
     * @param userFormData user map data
     * @return true if code was sent, false - if wasn't
     */
    boolean sendPasswordCode(Map<String, String> userFormData);

    /**
     * Verify sent code and code from user
     * @param userFormData user map data
     * @return true if codes was equals, false - if wasn't
     */
    boolean verifyPasswordCode(Map<String, String> userFormData);

    /**
     * Update account password
     * @param userData user map data
     * @return true if password was updated, false - if wasn't
     * @throws ServiceException if dao method throw {@link by.vlad.library.exception.DaoException}
     */
    boolean changePasswordByCode(Map<String, String> userData) throws ServiceException;
}