package by.vlad.library.validator;

import by.vlad.library.validator.impl.GenreValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GenreValidatorImplTest {
    GenreValidator validator = GenreValidatorImpl.getInstance();

    @DataProvider(name = "genreNameDataProvider")
    public Object[][] initGenreNameData(){
        return new Object[][]{
                {"", false},
                {"Проза", true},
                {"проза", false},
                {"!Научно-фантастическая", false},
                {"Научно-фантастическая литература", true}
        };
    }

    @Test(dataProvider = "genreNameDataProvider")
    public void testNameAndSurname(String data, boolean expected){
        boolean actual = validator.validateGenreName(data);
        Assert.assertEquals(actual, expected);
    }
}
