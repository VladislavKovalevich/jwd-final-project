package by.vlad.library.validator.impl;

import by.vlad.library.validator.GenreValidator;

public class GenreValidatorImpl implements GenreValidator {
    private static final String GENRE_NAME_REGEX = "[А-ЯЁ][а-яё\\-]{2,35}";
    private static GenreValidatorImpl instance;

    public static GenreValidatorImpl getInstance(){
        if (instance == null){
            instance = new GenreValidatorImpl();
        }

        return instance;
    }

    private GenreValidatorImpl(){}

    @Override
    public boolean validateGenreName(String name) {
        return name.matches(GENRE_NAME_REGEX);
    }
}
