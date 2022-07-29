package by.vlad.library.model.dao.mapper.impl;

import by.vlad.library.entity.User;
import by.vlad.library.model.dao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.vlad.library.model.dao.ColumnName.*;

/**
 * {@code UserMapper} class implements functional of {@link Mapper}
 */
public class UserMapper implements Mapper<User> {
    private static UserMapper instance;

    public static UserMapper getInstance(){
        if (instance == null){
            instance = new UserMapper();
        }

        return instance;
    }

    private UserMapper(){}

    @Override
    public List<User> map(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();

        while (resultSet.next()){
            User temp = User.getBuilder()
                    .withId(resultSet.getLong(USER_ID_COL))
                    .withIsBanned(resultSet.getBoolean(USER_IS_BANNED_COL))
                    .withRole(resultSet.getString(ROLE_NAME_COL))
                    .withMobilePhone(resultSet.getString(USER_MOBILE_PHONE_COL))
                    .withPassportSerialNumber(resultSet.getString(USER_PASSPORT_SERIAL_NUMBER_COL))
                    .withSurname(resultSet.getString(USER_SURNAME_COL))
                    .withName(resultSet.getString(USER_NAME_COL))
                    .withLogin(resultSet.getString(USER_LOGIN_COL))
                    .withEmail(resultSet.getString(USER_EMAIL_COL))
                    .buildUser();

            users.add(temp);
        }

        return users;
    }
}
