package by.vlad.library.validator;

import java.util.Map;

public interface UserValidator {
    boolean validatePassword(String password);

    boolean validateEmail(String email);

    boolean validateName(String name);

    boolean validatePassportSerialNumber(String amount);

    boolean validateLogin(String login);

    boolean validatePhoneNumber(String phoneNumber);

    boolean validateCreatedAccountData(Map<String, String> newUserData);

    boolean validateUpdatedAccountData(Map<String, String> updatedUserData);

    boolean validateNewPasswordData(Map<String, String> passwordData);
}