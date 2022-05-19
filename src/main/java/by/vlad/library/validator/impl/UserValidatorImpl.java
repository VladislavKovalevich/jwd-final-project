package by.vlad.library.validator.impl;

import by.vlad.library.validator.UserValidator;

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class UserValidatorImpl implements UserValidator {
    private static final String EMAIL_REGEX = "^(\\w+\\.)*\\w+@\\w+(\\.\\w+)*\\.[\\p{Lower}]{2,6}$";
    private static final String PASSWORD_REGEX = "^([\\w]){9,16}$";
    private static final String NAME_AND_SURNAME_REGEX = "^([а-яА-ЯЁё]){3,30}$";
    private static final String LOGIN_REGEX = "^[\\w-]{3,25}$";
    private static final String SERIAL_NUMBER_REGEX = "^[\\p{Upper}]{2}[\\d]{7}$";
    private static final String MOBILE_PHONE_REGEX = "^[+]?[\\d]{7,15}$";

    private static UserValidatorImpl instance;

    public static UserValidatorImpl getInstance(){
        if (instance == null){
            instance = new UserValidatorImpl();
        }

        return instance;
    }

    private UserValidatorImpl(){
    }

    @Override
    public boolean validatePassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    @Override
    public boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    @Override
    public boolean validateName(String name) {
        return name.matches(NAME_AND_SURNAME_REGEX);
    }

    @Override
    public boolean validatePassportSerialNumber(String serialNumber) {
        return serialNumber.matches(SERIAL_NUMBER_REGEX);
    }

    @Override
    public boolean validateLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches(MOBILE_PHONE_REGEX);
    }

    @Override
    public boolean validateCreatedAccountData(Map<String, String> newUserData) {
        boolean isValid = true;

        String login = newUserData.get(LOGIN_FORM);
        String name = newUserData.get(NAME_FORM);
        String surname = newUserData.get(SURNAME_FORM);
        String email = newUserData.get(EMAIL_FORM);
        String serialNumber = newUserData.get(SERIAL_NUMBER_FORM);
        String phoneNumber = newUserData.get(PHONE_NUMBER_FORM);
        String password = newUserData.get(PASSWORD_FORM);
        String repeated_pass = newUserData.get(REPEAT_PASSWORD_FORM);

        if (!validateLogin(login)){
            newUserData.put(WRONG_LOGIN_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validatePhoneNumber(phoneNumber) && phoneNumber.length() > 0){
            newUserData.put(WRONG_PHONE_NUMBER_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validatePassportSerialNumber(serialNumber)){
            newUserData.put(WRONG_SERIAL_NUMBER_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validateEmail(email)){
            newUserData.put(WRONG_EMAIL_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validatePassword(password)){
            newUserData.put(WRONG_PASSWORD_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validateName(name)){
            newUserData.put(WRONG_NAME_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validateName(surname)){
            newUserData.put(WRONG_SURNAME_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!password.equals(repeated_pass)){
            newUserData.put(WRONG_REPEAT_PASSWORD_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean validateUpdatedAccountData(Map<String, String> updatedUserData) {
        boolean isValid = true;

        String updateLogin = updatedUserData.get(LOGIN_FORM);
        String updateName = updatedUserData.get(NAME_FORM);
        String updateSurname = updatedUserData.get(SURNAME_FORM);
        String updateEmail = updatedUserData.get(EMAIL_FORM);
        String updateSerialNumber = updatedUserData.get(SERIAL_NUMBER_FORM);
        String phoneNumber = updatedUserData.get(PHONE_NUMBER_FORM);

        if (!validateLogin(updateLogin)){
            updatedUserData.put(WRONG_LOGIN_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validatePhoneNumber(phoneNumber) && phoneNumber.length() > 0){
            updatedUserData.put(WRONG_PHONE_NUMBER_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validatePassportSerialNumber(updateSerialNumber)){
            updatedUserData.put(WRONG_SERIAL_NUMBER_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validateName(updateName)){
            updatedUserData.put(WRONG_NAME_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validateName(updateSurname)){
            updatedUserData.put(WRONG_SURNAME_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validateEmail(updateEmail)){
            updatedUserData.put(WRONG_EMAIL_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean validateNewPasswordData(Map<String, String> passwordData) {
        boolean isValid = true;

        String oldPassword = passwordData.get(PASSWORD_FORM);
        String newPassword = passwordData.get(NEW_PASSWORD_FORM);
        String repeatNewPass = passwordData.get(NEW_REPEAT_PASSWORD_FORM);

        if (!validatePassword(oldPassword)){
            passwordData.put(WRONG_PASSWORD_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validatePassword(newPassword)){
            passwordData.put(WRONG_NEW_PASSWORD_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!newPassword.equals(repeatNewPass)){
            passwordData.put(WRONG_NEW_REPEAT_PASSWORD_FORM, UserValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        return isValid;
    }
}
