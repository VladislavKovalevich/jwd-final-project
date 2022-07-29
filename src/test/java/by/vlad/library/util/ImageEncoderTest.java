package by.vlad.library.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ImageEncoderTest {
    private ImageEncoder imageEncoder = ImageEncoder.getInstance();

    @DataProvider(name = "imageEncoderProvider")
    public Object[][] initImageEncoderData(){
        return new Object[][]{
                {new byte[]{43,25,21,20,21}, "data:image/jpeg;base64,KxkVFBU="},
                {new byte[]{55,25,49,20,21}, "data:image/jpeg;base64,NxkxFBU="},
                {new byte[]{55,25,49,20,21}, "data:image/jpeg;base64,NxkxFBU="},
                {new byte[]{55,25,49,20,21,21}, "data:image/jpeg;base64,NxkxFBUV"},
                {new byte[]{55,25,49,20,21,49,55}, "data:image/jpeg;base64,NxkxFBUxNw=="},
        };
    }

    @Test(dataProvider = "imageEncoderProvider")
    public void testImageEncoder(byte[] imageContent, String expected){
        String actual = imageEncoder.encodeImage(imageContent);
        Assert.assertEquals(actual, expected);
    }
}
