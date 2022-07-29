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

    /**
     * method-getter for {@link User#name} field
     * @return {@link User#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Method-setter for {@link User#name} filed
     * @param name - user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * method-getter for {@link User#surname} field
     * @return {@link User#surname}
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Method-setter for {@link User#surname} filed
     * @param surname - user surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * method-getter for {@link User#email} field
     * @return {@link User#email}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method-setter for {@link User#email} filed
     * @param email - user email, type String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * method-getter for {@link User#password} field
     * @return {@link User#password}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method-setter for {@link User#password} filed
     * @param password - password, type String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * method-getter for {@link User#role} field
     * @return {@link User#role}
     * @see Role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Method-setter for {@link User#role} filed
     * @param role - user role, type Role
     * @see Role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * method-getter for {@link User#login} field
     * @return {@link User#login}
     */
    public String getLogin() {
        return login;
    }

    /**
     * Method-setter for {@link User#login} filed
     * @param login - user login, type String
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * method-getter for {@link User#passportSerialNumber} field
     * @return {@link User#passportSerialNumber}
     */
    public String getPassportSerialNumber() {
        return passportSerialNumber;
    }

    /**
     * Method-setter for {@link User#passportSerialNumber} filed
     * @param passportSerialNumber - passport serial number, type String
     */
    public void setPassportSerialNumber(String passportSerialNumber) {
        this.passportSerialNumber = passportSerialNumber;
    }

    /**
     * method-getter for {@link User#mobilePhone} field
     * @return {@link User#mobilePhone}
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Method-setter for {@link User#password} filed
     * @param mobilePhone - user mobile phone, type String
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * method-getter for {@link User#isBanned} field
     * @return {@link User#isBanned}
     */
    public boolean isBanned() {
        return isBanned;
    }

    /**
     * Method-setter for {@link User#password} filed
     * @param banned - ban flag, type boolean
     */
    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    /**
     * {@code UserBuilder} class to build {@link User}
     */
    public class UserBuilder{
        /**
         * Method defines {@link AbstractEntity#id} field
         * @param id - user id, type long
         * @return - builder object {@link User.UserBuilder}
         */
        public UserBuilder withId(long id){
            User.this.setId(id);
            return this;
        }

        /**
         * Method defines {@link User#login} field
         * @param login - user login, type String
         * @return - builder object {@link User.UserBuilder}
         */
        public UserBuilder withLogin(String login){
            User.this.setLogin(login);
            return this;
        }

        /**
         * Method defines {@link User#name} field
         * @param name - user name, type String
         * @return - builder object {@link User.UserBuilder}
         */
        public UserBuilder withName(String name){
            User.this.setName(name);
            return this;
        }

        /**
         * Method defines {@link User#surname} field
         * @param surname - user surname, type String
         * @return - builder object {@link User.UserBuilder}
         */
        public UserBuilder withSurname(String surname){
            User.this.setSurname(surname);
            return this;
        }

        /**
         * Method defines {@link User#email} field
         * @param email - user email, type String
         * @return - builder object {@link User.UserBuilder}
         */
        public UserBuilder withEmail(String email){
            User.this.setEmail(email);
            return this;
        }

        /**
         * Method defines {@link User#password} field
         * @param password - user password, type String
         * @return - builder object {@link User.UserBuilder}
         */
        public UserBuilder withPassword(String password){
            User.this.setPassword(password);
            return this;
        }

        /**
         * Method defines {@link User#password} field
         * @param serialNumber - user passport serial number, type String
         * @return - builder object {@link User.UserBuilder}
         */
        public UserBuilder withPassportSerialNumber(String serialNumber){
            User.this.setPassportSerialNumber(serialNumber);
            return this;
        }

        /**
         * Method defines {@link User#mobilePhone} field
         * @param mobilePhone - user mobile phone, type String
         * @return - builder object {@link User.UserBuilder}
         */
        public UserBuilder withMobilePhone(String mobilePhone){
            User.this.setMobilePhone(mobilePhone);
            return this;
        }

        /**
         * Method defines {@link User#isBanned} field
         * @param isBanned - user status flag, type boolean
         * @return - builder object {@link User.UserBuilder}
         */
        public UserBuilder withIsBanned(boolean isBanned){
            User.this.setBanned(isBanned);
            return this;
        }

        /**
         * Method defines {@link User#role} field
         * @param role - user status flag, type {@link Role}
         * @return - builder object {@link User.UserBuilder}
         * @see Role
         */
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