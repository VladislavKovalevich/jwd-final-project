package by.vlad.library.validator;

import java.util.Map;

/**
 * {@code UserValidator} interface represent functional to validate
 * input data of {@link by.vlad.library.entity.User} class
 */
public interface UserValidator {
    /**
     * {@code WRONG_FORMAT_MARKER} constant represent string to mark wrong format data
     */
    String WRONG_FORMAT_MARKER = "wrong format";

    /**
     * method to validate user password
     * @param password user password
     * @return true if data is valid, false - if not
     */
    boolean validatePassword(String password);

    /**
     * method to validate user email
     * @param email user email
     * @return true if data is valid, false - if not
     */
    boolean validateEmail(String email);

    /**
     * method to validate user name
     * @param name user password
     * @return true if data is valid, false - if not
     */
    boolean validateName(String name);

    /**
     * method to validate user passport serial number
     * @param passportSerialNumber user passport serial number
     * @return true if data is valid, false - if not
     */
    boolean validatePassportSerialNumber(String passportSerialNumber);

    /**
     * method to validate user login
     * @param login user login
     * @return true if data is valid, false - if not
     */
    boolean validateLogin(String login);

    /**
     * method to validate user mobile phone number
     * @param phoneNumber user mobile phone number
     * @return true if data is valid, false - if not
     */
    boolean validatePhoneNumber(String phoneNumber);

    /**
     * method to validate user data to create new account
     * @param newUserData map with user data
     * @return true if data is valid, false - if not
     */
    boolean validateCreatedAccountData(Map<String, String> newUserData);

    /**
     * method to validate user data to update account info
     * @param updatedUserData map with user data
     * @return true if data is valid, false - if not
     */
    boolean validateUpdatedAccountData(Map<String, String> updatedUserData);

    /**
     * method to validate user data to update account password
     * @param passwordData map with password data
     * @return true if data is valid, false - if not
     */
    boolean validateNewPasswordData(Map<String, String> passwordData);
}