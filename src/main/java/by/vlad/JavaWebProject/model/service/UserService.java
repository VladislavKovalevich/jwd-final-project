package by.vlad.JavaWebProject.model.service;

import by.vlad.JavaWebProject.entity.User;
import by.vlad.JavaWebProject.exception.ServiceException;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> authenticate(String login, String password) throws ServiceException;

    boolean createNewAccount(Map<String, String> userData) throws ServiceException;

    boolean updatePersonalData(Map<String, String> userData) throws ServiceException;

    boolean changePassword(Map<String, String> password) throws ServiceException;

    boolean replenishBalance(Map<String, String> balanceData) throws ServiceException;
}