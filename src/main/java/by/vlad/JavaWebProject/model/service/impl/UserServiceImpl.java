package by.vlad.JavaWebProject.model.service.impl;

import by.vlad.JavaWebProject.model.dao.UserDAO;
import by.vlad.JavaWebProject.model.dao.impl.UserDAOImpl;
import by.vlad.JavaWebProject.entity.User;
import by.vlad.JavaWebProject.exception.DaoException;
import by.vlad.JavaWebProject.exception.ServiceException;
import by.vlad.JavaWebProject.model.service.UserService;
import by.vlad.JavaWebProject.util.PasswordEncoder;
import by.vlad.JavaWebProject.validator.UserValidator;
import by.vlad.JavaWebProject.validator.impl.UserValidatorImpl;

import java.util.Map;
import java.util.Optional;

import static by.vlad.JavaWebProject.controller.command.AttributeAndParamsNames.*;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;

    private UserServiceImpl(){}

    public static UserServiceImpl getInstance() {
        if (instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws ServiceException {
        Optional<User> user = Optional.empty();
        UserValidator validator = UserValidatorImpl.getInstance();

        if (!validator.validateEmail(login) || !validator.validatePassword(password)){
            //log
            return user;
        }

        UserDAO userDAO = UserDAOImpl.getInstance();
        password = PasswordEncoder.getInstance().getSHA1Hash(password);

        try {
            user = userDAO.authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public boolean createNewAccount(Map<String, String> userData) throws ServiceException {
        boolean isCreated = false;

        UserValidator userValidator = UserValidatorImpl.getInstance();

        if (!userValidator.validateCreatedAccountData(userData)){
            return isCreated;
        }

        String name = userData.get(NEW_NAME);
        String surname = userData.get(NEW_SURNAME);
        String email = userData.get(NEW_EMAIL);
        String password = userData.get(NEW_PASSWORD);

        UserDAO userDAO = UserDAOImpl.getInstance();

        try {
            if (userDAO.isEmailExists(email)){
                userData.put("error_msg", "this email is already exists");
                return isCreated;
            }

            String encryptedPass = PasswordEncoder.getInstance().getSHA1Hash(password);

            User newUser = User.getBuilder()
                    .withName(name)
                    .withSurname(surname)
                    .withEmail(email)
                    .withPassword(encryptedPass)
                    .buildUser();

            isCreated = userDAO.createNewAccount(newUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return isCreated;
    }

    @Override
    public Optional<User> findUserId(String userId) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public boolean updatePersonalData(Map<String, String> userData) throws ServiceException {
        return false;
    }

    @Override
    public boolean changePassword(Map<String, String> password) throws ServiceException {
        return false;
    }

    @Override
    public boolean replenishBalance(Map<String, String> balanceData) throws ServiceException {
        return false;
    }
}