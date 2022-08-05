package by.vlad.library.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code Role} enum represent user role
 */
public enum Role {

    GUEST,
    CLIENT,
    ADMIN;

    private static final Logger logger = LogManager.getLogger();

    /**
     * Method get user role by string name
     * @param roleStr - role name as String
     * @return - user role {@link Role}
     */
    public static Role getRole(String roleStr){
        Role role;

        try {
            role = roleStr != null ? valueOf(roleStr.toUpperCase()) : GUEST;
        }catch (IllegalArgumentException e){
            logger.warn("Role" + roleStr + " does not exist");
            role = GUEST;
        }
        return role;
    }
}