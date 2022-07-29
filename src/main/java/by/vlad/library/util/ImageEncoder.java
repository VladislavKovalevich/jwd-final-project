package by.vlad.library.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

/**
 * {@code ImageEncoder} util class to encode cover images
 */
public class ImageEncoder {
    private static final String CODED_TYPE = "data:image/jpeg;base64,";
    private static ImageEncoder instance;

    public static ImageEncoder getInstance(){
        if (instance == null){
            instance = new ImageEncoder();
        }

        return instance;
    }

    private ImageEncoder(){
    }

    /**
     * method to encode image as byte array to string
     * @param image image as byte array
     * @return encoded image
     */
    public String encodeImage(byte[] image){
        byte[] imageBase64 = Base64.encodeBase64(image, false);
        String imageAsString = StringUtils.newStringUtf8(imageBase64);
        StringBuilder encodeImage = new StringBuilder(CODED_TYPE);
        encodeImage.append(imageAsString);
        return encodeImage.toString();
    }
}
