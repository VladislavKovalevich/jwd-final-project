package by.vlad.JavaWebProject.entity;

public class User extends AbstractEntity{
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;

    public User(long id) {
        super(id);
    }

    public User() {
    }

    public static UserBuilder getBuilder(){
        return new User().new UserBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public class UserBuilder{
        public UserBuilder withId(long id){
            User.this.setId(id);
            return this;
        }

        public UserBuilder withName(String name){
            User.this.setName(name);
            return this;
        }

        public UserBuilder withSurname(String surname){
            User.this.setSurname(surname);
            return this;
        }

        public UserBuilder withEmail(String email){
            User.this.setEmail(email);
            return this;
        }

        public UserBuilder withPassword(String password){
            User.this.setPassword(password);
            return this;
        }

        public UserBuilder withRole(String role){
            User.this.setRole(Role.valueOf(role.toUpperCase()));
            return this;
        }

        public User buildUser(){
            return User.this;
        }
    }
}
