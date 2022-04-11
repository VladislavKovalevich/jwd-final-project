package by.vlad.JavaWebProject.validator;

public interface UserValidator {
    boolean validatePassword(String password);
    boolean validateEmail(String email);
}