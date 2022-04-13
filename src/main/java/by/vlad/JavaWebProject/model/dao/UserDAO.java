package by.vlad.JavaWebProject.model.dao;

import by.vlad.JavaWebProject.entity.User;
import by.vlad.JavaWebProject.exception.DaoException;

import java.util.Optional;

public interface UserDAO {
    Optional<User> authenticate(String login, String password) throws DaoException;

    boolean createNewAccount(User userData) throws DaoException;

    boolean isEmailExists(String email) throws DaoException;
}