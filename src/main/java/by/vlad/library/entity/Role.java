package by.vlad.library.entity;

public enum Role {
    GUEST,
    CLIENT,
    ADMIN;

    public static Role getRole(String roleStr){
        Role role;
        try {
            role = valueOf(roleStr.toUpperCase());
        }catch (IllegalArgumentException | NullPointerException e){
            role = GUEST;
            //logger
        }
        return role;
    }
}