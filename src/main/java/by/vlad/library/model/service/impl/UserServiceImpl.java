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

import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

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
    public Optional<User> authenticate(Map<String, String> userData) throws ServiceException {
        Optional<User> optionalUser = Optional.empty();

        String login = userData.get(EMAIL_FORM);
        String password = userData.get(PASSWORD_FORM);

        UserValidator validator = UserValidatorImpl.getInstance();

        if (!validator.validateEmail(login) || !validator.validatePassword(password)){
            userData.put(WRONG_EMAIL_OR_PASS, "invalid email or password");
            //log
            return optionalUser;
        }

        UserDao userDao = UserDaoImpl.getInstance();
        password = PasswordEncoder.getInstance().getSHA1Hash(password);

        try {
            optionalUser = userDao.authenticate(login, password);
            if (optionalUser.isEmpty()){
                userData.put(NOT_FOUND_USER, "user with these params was not found");
            }
        } catch (DaoException e) {
            //logger msg
            throw new ServiceException(e);
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
                userData.put(WRONG_EMAIL_FORM, "this email is already exists");
                return isCreated;
            }

            String encryptedPass = PasswordEncoder.getInstance().getSHA1Hash(password);

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
                    userData.put(WRONG_EMAIL_FORM, "this email is already exists");
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

        String oldPassword = passwordData.get(PASSWORD_FORM);
        String email = passwordData.get(EMAIL_FORM);
        String newPassword = passwordData.get(PASSWORD_FORM);

        UserDao userDao = UserDaoImpl.getInstance();

        String encodedOldPass = PasswordEncoder.getInstance().getSHA1Hash(oldPassword);
        String encodedNewPass = PasswordEncoder.getInstance().getSHA1Hash(newPassword);

        try {
            isChanged = userDao.changeAccountPassword(email, encodedOldPass, encodedNewPass);
        } catch (DaoException e) {
            throw new ServiceException(e);
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
            throw new ServiceException(e);
        }

        return optionalUser;
    }
}