package by.vlad.library.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PasswordEncoderTest {
    PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();

    @DataProvider(name = "passwordEncoderProvider")
    public Object[][] initPasswordEncoderData(){
        return new Object[][]{
                {"ASQWdsad!x", "ASQWdsad!x", true},
                {"abcdefghx", "ABCDEFGHX", false},
                {"12345qwerty", "2345qwerty1", false},
        };
    }

    @Test(dataProvider = "passwordEncoderProvider")
    public void passwordEncoderTest(String actualPassword, String expectedPassword, boolean expected){
        String actualHash = passwordEncoder.encode(actualPassword);
        String expectedHash = passwordEncoder.encode(expectedPassword);
        boolean actual = actualHash.equals(expectedHash);
        Assert.assertEquals(actual, expected);
    }
}
