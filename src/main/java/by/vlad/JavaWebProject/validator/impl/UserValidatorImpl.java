package by.vlad.JavaWebProject.validator.impl;

import by.vlad.JavaWebProject.validator.UserValidator;

import java.util.Map;

import static by.vlad.JavaWebProject.controller.command.AttributeAndParamsNames.*;

public class UserValidatorImpl implements UserValidator {
    private static final String EMAIL_EXPRESSION = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String PASSWORD_EXPRESSION = "^([a-z0-9A-Z]){9,16}$";
    private static final String NAME_AND_SURNAME_EXPRESSION = "^([а-яa-zA-ZА-Я]){3,30}$";
    private static final String AMOUNT_REGEX = "^\\d{1,4}(\\.\\d\\d)??$";

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
    public boolean validateAmount(String amount) {
        return amount.matches(AMOUNT_REGEX);
    }

    @Override
    public boolean validateCreatedAccountData(Map<String, String> newUserData) {
        boolean isValid = true;

        String name = newUserData.get(NEW_NAME);
        String surname = newUserData.get(NEW_SURNAME);
        String email = newUserData.get(NEW_EMAIL);
        String password = newUserData.get(NEW_PASSWORD);
        String repeated_pass = newUserData.get(REPEATED_PASSWORD);

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

        String update_name = updatedUserData.get(UPDATE_NAME);
        String update_surname = updatedUserData.get(UPDATE_SURNAME);
        String update_email = updatedUserData.get(UPDATE_EMAIL);

        if (!validateName(update_name)){
            updatedUserData.put("error_msg", "invalid name format");
            isValid = false;
        }

        if (!validateName(update_surname)){
            updatedUserData.put("error_msg", "invalid surname format");
            isValid = false;
        }

        if (!validateEmail(update_email)){
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
