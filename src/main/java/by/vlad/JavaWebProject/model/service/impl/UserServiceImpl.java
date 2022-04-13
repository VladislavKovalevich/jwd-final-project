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
    public boolean updatePersonalData(Map<String, String> userData) throws ServiceException {
        boolean isUpdated = false;

        UserValidator userValidator = UserValidatorImpl.getInstance();

        if (!userValidator.validateUpdatedAccountData(userData)){
            return isUpdated;
        }

        String update_name = userData.get(UPDATE_NAME);
        String update_surname = userData.get(UPDATE_SURNAME);
        String update_email = userData.get(UPDATE_EMAIL);

        UserDAO userDAO = UserDAOImpl.getInstance();

        try {

            if (!update_email.equals(userData.get("old_email"))) {
                if (userDAO.isEmailExists(update_email)) {
                    return isUpdated;
                }
            }

            User user = User.getBuilder()
                    .withName(update_name)
                    .withSurname(update_surname)
                    .withEmail(update_email)
                    .buildUser();

            isUpdated = userDAO.updateUserAccountData(user, userData.get("old_email"));

        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return isUpdated;
    }

    @Override
    public boolean changePassword(Map<String, String> passwordData) throws ServiceException {
        boolean isChanged = false;

        UserValidator userValidator = UserValidatorImpl.getInstance();

        if (!userValidator.validateNewPasswordData(passwordData)){
            return isChanged;
        }

        String oldPassword = passwordData.get(PASSWORD);
        String email = passwordData.get(USER_EMAIL);
        String newPassword = passwordData.get(NEW_PASS);

        UserDAO userDAO = UserDAOImpl.getInstance();

        String encodedOldPass = PasswordEncoder.getInstance().getSHA1Hash(oldPassword);
        String encodedNewPass = PasswordEncoder.getInstance().getSHA1Hash(newPassword);

        try {
            isChanged = userDAO.changeAccountPassword(email, encodedOldPass, encodedNewPass);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isChanged;
    }

    @Override
    public boolean replenishBalance(Map<String, String> balanceData) throws ServiceException {
        return false;
    }
}