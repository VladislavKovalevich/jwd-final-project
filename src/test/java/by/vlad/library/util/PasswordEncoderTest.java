package by.vlad.library.util;

import org.testng.Assert;

public class PasswordEncoderTest {
    PasswordEncoder passwordEncoder = PasswordEncoder.getInstance();

    public void passwordEncoderTest(String password, String expected){
        String actual = passwordEncoder.encode(password);
        Assert.assertEquals(actual, expected);
    }
}
