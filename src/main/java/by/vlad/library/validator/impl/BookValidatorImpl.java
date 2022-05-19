package by.vlad.library.validator.impl;

import by.vlad.library.validator.BookValidator;

import java.util.Map;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class BookValidatorImpl implements BookValidator {
    private static final String TITLE_REGEX = "^[А-ЯЁ][а-яё\\d\\s]+[А-ЯЁа-яё\\d\\s]*(:\\s[А-ЯЁа-яё\\d\\s]+)*$";
    private static final String COPIES_NUMBER_REGEX = "^[1-9][\\d]*$";
    private static final String PAGES_COUNT_REGEX = "^[1-9][\\d]{1,4}$";
    private static final String YEAR_REGEX = "^([12][\\d]{3})|([1-9]{1,3})$";
    private static final String DESCRIPTION_REGEX = "^[а-яА-ЯЁё\\w\\d\\p{Punct}\\s]+$";

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
        return title.matches(TITLE_REGEX);
    }

    @Override
    public boolean validateCopiesNumber(String copiesNumber) {
        return copiesNumber.matches(COPIES_NUMBER_REGEX);
    }

    @Override
    public boolean validatePagesCount(String pagesCount) {
        return pagesCount.matches(PAGES_COUNT_REGEX);
    }

    @Override
    public boolean validateReleaseYear(String year) {
        return year.matches(YEAR_REGEX);
    }

    @Override
    public boolean validateDescription(String description) {
        return description.matches(DESCRIPTION_REGEX);
    }

    @Override
    public boolean validateBookData(Map<String, String> bookData) {
        boolean isValid = true;

        String title = bookData.get(TITLE_FORM);
        String description = bookData.get(DESCRIPTION_FORM);
        String copiesNumber = bookData.get(COPIES_NUMBER_FORM);
        String pagesCount = bookData.get(PAGES_COUNT_FORM);
        String releaseYear = bookData.get(RELEASE_YEAR_FORM);

        if (!validateTitle(title)){
            isValid = false;
            bookData.put(WRONG_TITLE_FORM, BookValidator.WRONG_FORMAT_MARKER);
        }

        if (!validateDescription(description)){
            isValid = false;
            bookData.put(WRONG_DESCRIPTION_FORM, BookValidator.WRONG_FORMAT_MARKER);
        }

        if (!validateCopiesNumber(copiesNumber)){
            isValid = false;
            bookData.put(WRONG_COPIES_NUMBER_FORM, BookValidator.WRONG_FORMAT_MARKER);
        }

        if (!validatePagesCount(pagesCount)){
            isValid = false;
            bookData.put(WRONG_PAGES_COUNT_FORM, BookValidator.WRONG_FORMAT_MARKER);
        }

        if (!validateReleaseYear(releaseYear)){
            isValid = false;
            bookData.put(WRONG_RELEASE_YEAR_FORM, BookValidator.WRONG_FORMAT_MARKER);
        }

        return isValid;
    }
}
