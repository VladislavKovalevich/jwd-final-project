package by.vlad.JavaWebProject.validator.impl;

import by.vlad.JavaWebProject.validator.UserValidator;

import java.util.Map;

import static by.vlad.JavaWebProject.controller.command.AttributeAndParamsNames.*;

public class UserValidatorImpl implements UserValidator {
    private static final String EMAIL_EXPRESSION = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String PASSWORD_EXPRESSION = "^([a-z0-9A-Z]){9,16}$";
    private static final String NAME_AND_SURNAME_EXPRESSION = "^([а-яa-zA-ZА-Я]){3,30}$";
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
        return password.matches(PASSWORD_EXPRESSION);
    }

    @Override
    public boolean validateEmail(String email) {
        return email.matches(EMAIL_EXPRESSION);
    }

    @Override
    public boolean validateName(String name) {
        return name.matches(NAME_AND_SURNAME_EXPRESSION);
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

        String login = newUserData.get(NEW_LOGIN);
        String name = newUserData.get(NEW_NAME);
        String surname = newUserData.get(NEW_SURNAME);
        String email = newUserData.get(NEW_EMAIL);
        String serialNumber = newUserData.get(NEW_SERIAL_NUMBER);
        String phoneNumber = newUserData.get(NEW_PHONE_NUMBER);
        String password = newUserData.get(NEW_PASSWORD);
        String repeated_pass = newUserData.get(REPEATED_PASSWORD);

        if (!validateLogin(login)){
            newUserData.put("error_msg", "invalid login format");
            isValid = false;
        }

        if (!validatePhoneNumber(phoneNumber) && phoneNumber.length() > 0){
            newUserData.put("error_msg", "invalid phone number format");
            isValid = false;
        }

        if (!validatePassportSerialNumber(serialNumber)){
            newUserData.put("error_msg", "invalid passport serial number format");
            isValid = false;
        }

        if (!validateEmail(email)){
            newUserData.put("error_msg", "invalid email format");
            isValid = false;
        }

        if (!validatePassword(password)){
            newUserData.put("error_msg", "invalid password format");
            isValid = false;
        }

        if (!validateName(name)){
            newUserData.put("error_msg", "invalid name format");
            isValid = false;
        }

        if (!validateName(surname)){
            newUserData.put("error_msg", "invalid surname format");
            isValid = false;
        }

        if (!password.equals(repeated_pass)){
            newUserData.put("error_msg", "password and repeated password isn't equals");
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean validateUpdatedAccountData(Map<String, String> updatedUserData) {
        boolean isValid = true;

        String updateLogin = updatedUserData.get(UPDATE_LOGIN);
        String updateName = updatedUserData.get(UPDATE_NAME);
        String updateSurname = updatedUserData.get(UPDATE_SURNAME);
        String updateEmail = updatedUserData.get(UPDATE_EMAIL);
        String updateSerialNumber = updatedUserData.get(UPDATE_SERIAL_NUMBER);
        String phoneNumber = updatedUserData.get(UPDATE_PHONE_NUMBER);

        if (!validateLogin(updateLogin)){
            updatedUserData.put("error_msg", "invalid login format");
            isValid = false;
        }

        if (!validatePhoneNumber(phoneNumber) && phoneNumber.length() > 0){
            updatedUserData.put("error_msg", "invalid phone number format");
            isValid = false;
        }

        if (!validatePassportSerialNumber(updateSerialNumber)){
            updatedUserData.put("error_msg", "invalid passport serial number format");
            isValid = false;
        }

        if (!validateName(updateName)){
            updatedUserData.put("error_msg", "invalid name format");
            isValid = false;
        }

        if (!validateName(updateSurname)){
            updatedUserData.put("error_msg", "invalid surname format");
            isValid = false;
        }

        if (!validateEmail(updateEmail)){
            updatedUserData.put("error_msg", "invalid email format");
            isValid = false;
        }

        return isValid;
    }

    @Override
    public boolean validateNewPasswordData(Map<String, String> passwordData) {
        boolean isValid = true;

        String oldPassword = passwordData.get(PASSWORD);
        String newPassword = passwordData.get(NEW_PASS);
        String repeatNewPass = passwordData.get(REPEAT_NEW_PASS);

        if (!validatePassword(oldPassword)){
            passwordData.put("error_msg", "invalid old password format");
            isValid = false;
        }

        if (!validatePassword(newPassword)){
            passwordData.put("error_msg", "invalid new password format");
            isValid = false;
        }

        if (!newPassword.equals(repeatNewPass)){
            passwordData.put("error_msg", "new password and repeated isn't equals");
            isValid = false;
        }

        return isValid;
    }
}
