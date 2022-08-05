package by.vlad.library.entity;

/**
 * {@code User} class represent user entity
 * @see AbstractEntity
 */
public class User extends AbstractEntity{
    /** user login */
    private String login;

    /** user name */
    private String name;

    /** user surname */
    private String surname;

    /** user email */
    private String email;

    /** user password */
    private String password;

    /** user passport serial number */
    private String passportSerialNumber;

    /** user mobile phone */
    private String mobilePhone;

    /** user status flag, true - is banned, false is active*/
    private boolean isBanned;

    /** user role
     * @see Role
     */
    private Role role;

    public User(long id) {
        super(id);
    }

    public User() {
    }

    /**
     * {@code getBuilder} method to get {@link User.UserBuilder}
     * @return {@link User.UserBuilder}
     */
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassportSerialNumber() {
        return passportSerialNumber;
    }

    public void setPassportSerialNumber(String passportSerialNumber) {
        this.passportSerialNumber = passportSerialNumber;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    /**
     * {@code UserBuilder} class to build {@link User}
     */
    public class UserBuilder{

        public UserBuilder withId(long id){
            User.this.setId(id);
            return this;
        }

        public UserBuilder withLogin(String login){
            User.this.setLogin(login);
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

        public UserBuilder withPassportSerialNumber(String serialNumber){
            User.this.setPassportSerialNumber(serialNumber);
            return this;
        }

        public UserBuilder withMobilePhone(String mobilePhone){
            User.this.setMobilePhone(mobilePhone);
            return this;
        }

        public UserBuilder withIsBanned(boolean isBanned){
            User.this.setBanned(isBanned);
            return this;
        }

        public UserBuilder withRole(String role){
            User.this.setRole(Role.valueOf(role.toUpperCase()));
            return this;
        }

        /**
         * Method build user entity
         * @return - user object {@link User}
         */
        public User buildUser(){
            return User.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isBanned != user.isBanned) return false;
        if (!login.equals(user.login)) return false;
        if (!name.equals(user.name)) return false;
        if (!surname.equals(user.surname)) return false;
        if (!email.equals(user.email)) return false;
        if (!password.equals(user.password)) return false;
        if (!passportSerialNumber.equals(user.passportSerialNumber)) return false;
        if (mobilePhone != null ? !mobilePhone.equals(user.mobilePhone) : user.mobilePhone != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + passportSerialNumber.hashCode();
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (isBanned ? 1 : 0);
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("User{" + "login='").append(login).append('\'')
                .append(", name='").append(name).append('\'')
                .append(", surname='").append(surname).append('\'')
                .append(", email='").append(email).append('\'')
                .append(", password='").append(password).append('\'')
                .append(", passportSerialNumber='").append(passportSerialNumber).append('\'')
                .append(", mobilePhone='").append(mobilePhone).append('\'')
                .append(", isBanned=").append(isBanned)
                .append(", role=").append(role)
                .append('}')
                .toString();
    }
}