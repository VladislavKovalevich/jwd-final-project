package by.vlad.library.validator;

import by.vlad.library.validator.impl.BookValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookValidatorImplTest {
    private final BookValidator validator = BookValidatorImpl.getInstance();

    @DataProvider(name = "titleDataProvider")
    public Object[][] initTitleData(){
        return new Object[][]{
                {"", false},
                {"1234", false},
                {"11111111111111", false},
                {"Властелин колец 2: Две башни", true},
                {"Тихий Дон", true},
                {"Герой нашего времени", true}
        };
    }

    @Test(dataProvider = "titleDataProvider")
    public void testValidateTitle(String name, boolean expected){
        boolean actual = validator.validateTitle(name);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "copiesDataProvider")
    public Object[][] initCopiesData(){
        return new Object[][]{
                {"", false},
                {"1234", false},
                {"99", true},
                {"23qwe", false},
                {"5", true},
                {"-6", false}
        };
    }

    @Test(dataProvider = "copiesDataProvider")
    public void testValidateCopiesNumber(String copiesNumber, boolean expected){
        boolean actual = validator.validateCopiesNumber(copiesNumber);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "releaseYearDataProvider")
    public Object[][] initReleaseYearData(){
        return new Object[][]{
                {"19", false},
                {"", false},
                {"1998", true},
                {"982q", false},
                {"2019", true},
                {"3024", false}
        };
    }

    @Test(dataProvider = "releaseYearDataProvider")
    public void testValidateReleaseYear(String releaseYear, boolean expected){
        boolean actual = validator.validateReleaseYear(releaseYear);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "pagesCountDataProvider")
    public Object[][] initPagesCountData(){
        return new Object[][]{
                {"19", true},
                {"", false},
                {"199", true},
                {"982q", false},
                {"890", true}
        };
    }

    @Test(dataProvider = "pagesCountDataProvider")
    public void testValidatePagesCount(String pagesCount, boolean expected){
        boolean actual = validator.validatePagesCount(pagesCount);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "descriptionDataProvider")
    public Object[][] initDescriptionData(){
        return new Object[][]{
                {"<script>", false},
                {"", false},
                {"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
                 "Aenean commodo ligula eget dolor. Aenean massa.", true},
                {"«Приключе́ния капита́на Вру́нгеля» - юмористическая повесть советского писателя Андрея Некрасова. " +
                 "Книга впервые была представлена читателям в 1937 году в журнале «Пионер», где публиковалась в сокращении, " +
                 "полноценное книжное издание вышло в 1939 году.", true},
        };
    }

    @Test(dataProvider = "descriptionDataProvider")
    public void testValidateDescription(String description, boolean expected){
        boolean actual = validator.validateDescription(description);
        Assert.assertEquals(actual, expected);
    }
}
