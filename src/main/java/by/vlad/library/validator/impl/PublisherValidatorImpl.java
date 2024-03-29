package by.vlad.library.validator.impl;

import by.vlad.library.validator.PublisherValidator;

/**
 * {@code PublisherValidatorImpl} class implements functional of {@link PublisherValidator}
 */
public class PublisherValidatorImpl implements PublisherValidator {
    private static final String NAME_REGEX = "^[А-ЯЁ]([а-яё]+(\\s[А-ЯЁ][а-яё]+)?){2,30}$";

    private static PublisherValidatorImpl instance;

    public static PublisherValidatorImpl getInstance(){
        if (instance == null){
            instance = new PublisherValidatorImpl();
        }

        return instance;
    }

    private PublisherValidatorImpl(){
    }

    @Override
    public boolean validatePublisherName(String publisherName) {
        return publisherName.matches(NAME_REGEX);
    }
}
