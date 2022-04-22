package by.vlad.library.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    private static PasswordEncoder instance;

    private PasswordEncoder(){
    }

    public static PasswordEncoder getInstance(){
        if (instance == null){
            instance = new PasswordEncoder();
        }

        return instance;
    }

    public String getSHA1Hash(String password){
        StringBuilder stringHashCode;
        byte[] bytes;
        MessageDigest messageDigest;

        stringHashCode = new StringBuilder();

        try {

            messageDigest = MessageDigest.getInstance("sha-1");
            bytes = messageDigest.digest(password.getBytes());

            for (byte b: bytes) {
                stringHashCode.append(String.format("%02x", b));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return stringHashCode.toString();
    }
}
