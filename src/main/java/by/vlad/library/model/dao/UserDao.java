package by.vlad.library.model.dao;

import by.vlad.library.entity.User;
import by.vlad.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BasicDao<User> {
    Optional<User> authenticate(String login, String password) throws DaoException;

    boolean createNewAccount(User userData) throws DaoException;

    boolean isEmailExists(String email) throws DaoException;

    boolean updateUserAccountData(User user, String oldEmail) throws DaoException;

    boolean changeAccountPassword(String email, String password, String newPassword) throws DaoException;

    boolean changeAccountStatus(long userId, int status) throws DaoException;

    Optional<User> findUserById(long id) throws DaoException;

    List<User> findAllUsers() throws DaoException;
}