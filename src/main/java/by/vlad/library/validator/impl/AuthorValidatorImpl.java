package by.vlad.library.validator.impl;

import by.vlad.library.validator.AuthorValidator;

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

/**
 * {@code AuthorValidatorImpl} class implements functional of {@link AuthorValidator}
 */
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

    @Override
    public boolean validateAuthorParams(Map<String, String> authorMap) {
        boolean isValid = true;
        String name = authorMap.get(AUTHOR_NAME_FORM);
        String surname = authorMap.get(AUTHOR_SURNAME_FORM);

        if (!validateName(name)){
            authorMap.put(WRONG_AUTHOR_NAME_FORM, AuthorValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        if (!validateName(surname)){
            authorMap.put(WRONG_AUTHOR_SURNAME_FORM, AuthorValidator.WRONG_FORMAT_MARKER);
            isValid = false;
        }

        return isValid;
    }
}