package by.vlad.library.util;

import org.apache.commons.codec.digest.DigestUtils;

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

    public String encode(String password){
        return DigestUtils.md5Hex(password + SALT);
    }
}