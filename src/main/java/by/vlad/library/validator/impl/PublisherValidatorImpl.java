package by.vlad.library.validator.impl;

import by.vlad.library.validator.PublisherValidator;

public class PublisherValidatorImpl implements PublisherValidator {
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
        return false;
    }
}
