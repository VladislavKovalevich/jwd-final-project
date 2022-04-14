package by.vlad.JavaWebProject.entity;

public enum Role {
    GUEST,
    CLIENT,
    ADMIN;

    public static Role getRole(String roleStr){
        return Role.valueOf(roleStr.toUpperCase());
    }
}