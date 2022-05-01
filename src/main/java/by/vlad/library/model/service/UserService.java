package by.vlad.library.model.service;

import by.vlad.library.entity.User;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> authenticate(Map<String, String> userData) throws ServiceException;

    boolean createNewAccount(Map<String, String> userData) throws ServiceException;

    boolean updatePersonalData(Map<String, String> userData) throws ServiceException;

    boolean changePassword(Map<String, String> password) throws ServiceException;

    Optional<User> findUserById(long id) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;
}