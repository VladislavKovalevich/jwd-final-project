package by.vlad.library.model.service.impl;

import by.vlad.library.model.dao.UserDao;
import by.vlad.library.model.dao.impl.UserDaoImpl;
import by.vlad.library.entity.User;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.service.UserService;
import by.vlad.library.util.PasswordEncoder;
import by.vlad.library.validator.UserValidator;
import by.vlad.library.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static UserServiceImpl instance;

    private UserServiceImpl(){}

    public static UserServiceImpl getInstance() {
        if (instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> authenticate(Map<String, String> userData) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();

        String login = userData.get(EMAIL_FORM);
        String password = userData.get(PASSWORD_FORM);

        UserValidator validator = UserValidatorImpl.getInstance();

        if (!validator.validateEmail(login) || !validator.validatePassword(password)){
            userData.put(WRONG_EMAIL_OR_PASS, UserValidator.WRONG_FORMAT_MARKER);
            return optionalUser;
        }

        UserDao userDao = UserDaoImpl.getInstance();
        password = PasswordEncoder.getInstance().encode(password);

        try {
            optionalUser = userDao.authenticate(login, password);
            if (optionalUser.isEmpty()){
                userData.put(NOT_FOUND_USER, UserValidator.WRONG_FORMAT_MARKER);
            }
        } catch (DaoException e) {
            logger.error("Method authenticate from user service was failed" + e);
            throw new ServiceException("Method authenticate from user service was failed", e);
        }

        return optionalUser;
    }

    @Override
    public boolean createNewAccount(Map<String, String> userData) throws ServiceException {
        boolean isCreated = false;

        UserValidator userValidator = UserValidatorImpl.getInstance();

        if (!userValidator.validateCreatedAccountData(userData)){
            return isCreated;
        }

        String name = userData.get(NAME_FORM);
        String surname = userData.get(SURNAME_FORM);
        String email = userData.get(EMAIL_FORM);
        String serialNumber = userData.get(SERIAL_NUMBER_FORM);
        String login = userData.get(LOGIN_FORM);
        String phoneNumber = userData.get(PHONE_NUMBER_FORM);
        String password = userData.get(PASSWORD_FORM);

        UserDao userDao = UserDaoImpl.getInstance();

        try {
            if (userDao.isEmailExists(email)){
                userData.put(WRONG_EMAIL_EXISTS_FORM, UserValidator.WRONG_FORMAT_MARKER);
                return isCreated;
            }

            String encryptedPass = PasswordEncoder.getInstance().encode(password);

            User newUser = User.getBuilder()
                    .withName(name)
                    .withSurname(surname)
                    .withEmail(email)
                    .withLogin(login)
                    .withMobilePhone(phoneNumber)
                    .withPassportSerialNumber(serialNumber)
                    .withPassword(encryptedPass)
                    .buildUser();

            isCreated = userDao.createNewAccount(newUser);
        } catch (DaoException e) {
            logger.error("Method createNewAccount from user service was failed" + e);
            throw new ServiceException("Method createNewAccount from user service was failed", e);
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

        String updateName = userData.get(NAME_FORM);
        String updateSurname = userData.get(SURNAME_FORM);
        String updateEmail = userData.get(EMAIL_FORM);
        String updateSerialNumber = userData.get(SERIAL_NUMBER_FORM);
        String updateLogin = userData.get(LOGIN_FORM);
        String updatePhoneNumber = userData.get(PHONE_NUMBER_FORM);

        UserDao userDao = UserDaoImpl.getInstance();

        try {

            if (!updateEmail.equals(userData.get(USER_EMAIL))) {
                if (userDao.isEmailExists(updateEmail)) {
                    userData.put(WRONG_EMAIL_EXISTS_FORM, UserValidator.WRONG_FORMAT_MARKER);
                    return isUpdated;
                }
            }

            User user = User.getBuilder()
                    .withName(updateName)
                    .withSurname(updateSurname)
                    .withEmail(updateEmail)
                    .withPassportSerialNumber(updateSerialNumber)
                    .withMobilePhone(updatePhoneNumber)
                    .withLogin(updateLogin)
                    .buildUser();

            isUpdated = userDao.updateUserAccountData(user, userData.get(USER_EMAIL));

        } catch (DaoException e) {
            logger.error("Method updatePersonalData from user service was failed" + e);
            throw new ServiceException("Method updatePersonalData from user service was failed", e);
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

        String oldPassword = passwordData.get(PASSWORD_FORM);
        String email = passwordData.get(USER_EMAIL);
        String newPassword = passwordData.get(NEW_PASSWORD_FORM);

        UserDao userDao = UserDaoImpl.getInstance();

        String encodedOldPass = PasswordEncoder.getInstance().encode(oldPassword);
        String encodedNewPass = PasswordEncoder.getInstance().encode(newPassword);

        try {
            isChanged = userDao.changeAccountPassword(email, encodedOldPass, encodedNewPass);
            if (!isChanged){
                passwordData.put(WRONG_PASSWORD_VALUE, UserValidator.WRONG_FORMAT_MARKER);
            }
        } catch (DaoException e) {
            logger.error("Method changePassword from user service was failed" + e);
            throw new ServiceException("Method changePassword from user service was failed", e);
        }
        return isChanged;
    }

    @Override
    public Optional<User> findUserById(long id) throws ServiceException {
        Optional<User> optionalUser;

        UserDao userDao = UserDaoImpl.getInstance();

        try {
            optionalUser = userDao.findUserById(id);
        } catch (DaoException e) {
            logger.error("Method findUserById from user service was failed" + e);
            throw new ServiceException("Method findUserById from user service was failed", e);
        }

        return optionalUser;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> users;
        UserDao userDao = UserDaoImpl.getInstance();

        try {
            users = userDao.findAllUsers();
        } catch (DaoException e) {
            logger.error("Method getAllUsers from user service was failed" + e);
            throw new ServiceException("Method getAllUsers from user service was failed", e);
        }

        return users;
    }

    @Override
    public boolean changeAccountStatus(String email, boolean status) throws ServiceException {
        boolean isUpdated;
        UserDao userDao = UserDaoImpl.getInstance();

        try {
            isUpdated = userDao.changeAccountStatus(email, status);
        } catch (DaoException e) {
            logger.error("Method changeAccountStatus from user service was failed" + e);
            throw new ServiceException("Method changeAccountStatus from user service was failed", e);
        }

        return isUpdated;
    }
}