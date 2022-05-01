package by.vlad.library.validator.impl;

import by.vlad.library.validator.BookValidator;

import java.util.Map;

public class BookValidatorImpl implements BookValidator {
    private static BookValidatorImpl instance;

    public static BookValidatorImpl getInstance(){
        if (instance == null){
            instance = new BookValidatorImpl();
        }

        return instance;
    }

    private BookValidatorImpl(){
    }

    @Override
    public boolean validateTitle(String title) {
        return false;
    }

    @Override
    public boolean validateCopiesNumber(String copiesNumber) {
        return false;
    }

    @Override
    public boolean validatePagesCount(String pagesCount) {
        return false;
    }

    @Override
    public boolean validateReleaseYear(String year) {
        return false;
    }

    @Override
    public boolean validateDescription(String description) {
        return false;
    }

    @Override
    public boolean validateCreatedBook(Map<String, String> bookData) {

        return false;
    }
}
