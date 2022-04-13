package by.vlad.JavaWebProject.validator;

import java.util.Map;

public interface UserValidator {
    boolean validatePassword(String password);
    boolean validateEmail(String email);
    boolean validateCreatedAccountData(Map<String, String> newUserData);
    boolean validateName(String name);
}