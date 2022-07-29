package by.vlad.library.util;

import by.vlad.library.entity.AbstractEntity;
import by.vlad.library.entity.Author;
import by.vlad.library.entity.Genre;
import by.vlad.library.entity.Publisher;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ConverterToCSVStringTest {
    private ConverterToCSVString converter = ConverterToCSVString.getInstance();

    @DataProvider(name = "initConverterDataProvider")
    public Object[][] initConverterData(){
        return new Object[][]{
                {List.of(new Author(1), new Author(3), new Author(9)), "1,3,9"},
                {List.of(new Genre(11), new Genre(23), new Genre(19)), "11,23,19"},
                {List.of(new Publisher(1), new Publisher(44), new Publisher(9)), "1,44,9"}
        };
    }

    @Test(dataProvider = "initConverterDataProvider")
    public void testConverter(List<? extends AbstractEntity> list, String expected){
        String actual = converter.convertEntityList(list);
        Assert.assertEquals(actual, expected);
    }
}
