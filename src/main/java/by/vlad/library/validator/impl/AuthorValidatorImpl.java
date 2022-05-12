package by.vlad.library.validator.impl;

import by.vlad.library.validator.AuthorValidator;

public class AuthorValidatorImpl implements AuthorValidator {
    private static final String NAME_REGEX = "^[А-ЯЁ][а-яё]{2,30}$";

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
        return name.matches(NAME_REGEX);
    }
}