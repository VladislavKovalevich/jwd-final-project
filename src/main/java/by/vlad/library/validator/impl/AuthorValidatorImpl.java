package by.vlad.library.validator.impl;

import by.vlad.library.validator.AuthorValidator;

public class AuthorValidatorImpl implements AuthorValidator {
    private static AuthorValidatorImpl instance;

    public static AuthorValidatorImpl getInstance(){
        if (instance == null){
            instance = new AuthorValidatorImpl();
        }

        return instance;
    }

    private AuthorValidatorImpl(){
    }

    @Override
    public boolean validateName(String name) {
        return false;
    }
}