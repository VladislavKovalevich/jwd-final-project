package by.vlad.library.model.service;

import by.vlad.library.entity.User;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    String USER_EXISTS_MARKER = "user with this params is already exists";

    Optional<User> authenticate(Map<String, String> userData) throws ServiceException;

    boolean createNewAccount(Map<String, String> userData) throws ServiceException;

    boolean updatePersonalData(Map<String, String> userData) throws ServiceException;

    boolean changePassword(Map<String, String> password) throws ServiceException;

    Optional<User> findUserById(long id) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    boolean changeAccountStatus(String email, boolean status) throws ServiceException;
}