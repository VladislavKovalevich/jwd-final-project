package by.vlad.JavaWebProject.validator.impl;

import by.vlad.JavaWebProject.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {
    private static final String EMAIL_EXPRESSION = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String PASSWORD_EXPRESSION = "^([a-z0-9A-Z]){9,16}$";

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
}
