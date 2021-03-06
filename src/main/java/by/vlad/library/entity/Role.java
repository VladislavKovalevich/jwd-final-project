package by.vlad.library.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Role {

    GUEST,
    CLIENT,
    ADMIN;

    private static final Logger logger = LogManager.getLogger();


    public static Role getRole(String roleStr){
        Role role;

        try {
            role = valueOf(roleStr.toUpperCase());
        }catch (IllegalArgumentException | NullPointerException e){
            logger.warn("Role" + roleStr + " does not exist");
            role = GUEST;
        }
        return role;
    }
}