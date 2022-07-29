package by.vlad.library.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * {@code PasswordEncoder} util class to encode user password
 */
public class PasswordEncoder {
    private static PasswordEncoder instance;
    private final String SALT = "5e!6&T#";

    private PasswordEncoder(){
    }

    public static PasswordEncoder getInstance(){
        if (instance == null){
            instance = new PasswordEncoder();
        }

        return instance;
    }

    /**
     * Method encode user password with MD5Hex algorithm
     * @param password user password
     * @return encoded user password
     */
    public String encode(String password){
        return DigestUtils.md5Hex(password + SALT);
    }
}