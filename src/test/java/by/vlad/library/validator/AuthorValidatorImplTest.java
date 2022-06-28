package by.vlad.library.validator;

import by.vlad.library.validator.impl.AuthorValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AuthorValidatorImplTest {
    AuthorValidator validator = AuthorValidatorImpl.getInstance();

    @DataProvider(name = "authorNameAndSurnameDataProvider")
    public Object[][] initNameAndSurnameData(){
        return new Object[][]{
                {"", false},
                {"Александр", true},
                {"александр", false},
                {"Alex", false},
                {"Пушкин", true}
        };
    }

    @Test(dataProvider = "authorNameAndSurnameDataProvider")
    public void testNameAndSurname(String data, boolean expected){
        boolean actual = validator.validateName(data);
        Assert.assertEquals(actual, expected);
    }
}
