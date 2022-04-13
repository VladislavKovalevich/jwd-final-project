package by.vlad.JavaWebProject.validator;

import java.util.Map;

public interface UserValidator {
    boolean validatePassword(String password);

    boolean validateEmail(String email);

    boolean validateName(String name);

    boolean validateAmount(String amount);

    boolean validateCreatedAccountData(Map<String, String> newUserData);

    boolean validateUpdatedAccountData(Map<String, String> updatedUserData);

    boolean validateNewPasswordData(Map<String, String> passwordData);
}