package by.vlad.library.validator;

import by.vlad.library.validator.impl.UserValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorImplTest {
    private final UserValidator validator = UserValidatorImpl.getInstance();

    @DataProvider(name = "nameDataProvider")
    public Object[][] initNameData(){
        return new Object[][]{
                {"", false},
                {"1234", false},
                {"11111111111111", false},
                {"Иван23", false},
                {"<Иван>", false},
                {"Александр", true}
        };
    }

    @Test(dataProvider = "nameDataProvider")
    public void testValidateName(String name, boolean expected){
        boolean actual = validator.validateName(name);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "loginDataProvider")
    public Object[][] initLoginData(){
        return new Object[][]{
                {"", false},
                {"1234", false},
                {"11111111111111", false},
                {"Ivan23", true},
                {"<Ivan>", false},
                {"Vlad_oleg_19", true}
        };
    }

    @Test(dataProvider = "loginDataProvider")
    public void testValidateLogin(String name, boolean expected){
        boolean actual = validator.validateLogin(name);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "emailDataProvider")
    public Object[][] initEmailData(){
        return new Object[][]{
                {"ivan.ivanov@gmail.com", true},
                {"ivanov@gmail.com", true},
                {"11111111111111", false},
                {"", false},
                {"ivanivanov.@gmail.com", false},
                {"ivanivanov@gmail.com.com", false}
        };
    }

    @Test(dataProvider = "emailDataProvider")
    public void testValidateEmail(String name, boolean expected){
        boolean actual = validator.validateEmail(name);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "serialPassportNumberProvider")
    public Object[][] initSerialPassportNumberData(){
        return new Object[][]{
                {"MC2334532", true},
                {"OI2918756", true},
                {"11111111111111", false},
                {"", false},
                {"mc2345421", false},
                {"ЛВ2345621", false}
        };
    }

    @Test(dataProvider = "serialPassportNumberProvider")
    public void testValidateSerialPassportNumber(String name, boolean expected){
        boolean actual = validator.validatePassportSerialNumber(name);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "mobilePhoneDataProvider")
    public Object[][] initMobilePhoneData(){
        return new Object[][]{
                {"", false},
                {"wqeasd", false},
                {"1234567", false},
                {"+375291442345", true},
                {"+375698764565", false},
                {"+375643456", false}
        };
    }

    @Test(dataProvider = "mobilePhoneDataProvider")
    public void testMobilePhoneNumber(String name, boolean expected){
        boolean actual = validator.validatePhoneNumber(name);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "passwordDataProvider")
    public Object[][] initPasswordData(){
        return new Object[][]{
                {"qwerty1234", true},
                {"12345678", true},
                {"123", false},
                {"<///>", false},
                {"11111111111111111111111111111111111111111111111111111111", false}
        };
    }

    @Test(dataProvider = "passwordDataProvider")
    public void testPassword(String name, boolean expected){
        boolean actual = validator.validatePassword(name);
        Assert.assertEquals(actual, expected);
    }
}
