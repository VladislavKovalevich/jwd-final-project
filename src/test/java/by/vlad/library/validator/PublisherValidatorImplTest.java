package by.vlad.library.validator;

import by.vlad.library.validator.impl.PublisherValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PublisherValidatorImplTest {
    PublisherValidator validator = PublisherValidatorImpl.getInstance();

    @DataProvider(name = "publisherNameDataProvider")
    public Object[][] initPublisherNameData(){
        return new Object[][]{
                {"", false},
                {"Белый Ветер", true},
                {"Бриз", true},
                {"!Белый", false},
                {" Белый Ветер ", false}
        };
    }

    @Test(dataProvider = "publisherNameDataProvider")
    public void testPublisherName(String data, boolean expected){
        boolean actual = validator.validatePublisherName(data);
        Assert.assertEquals(actual, expected);
    }
}
