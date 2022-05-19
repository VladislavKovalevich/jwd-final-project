package by.vlad.library.model.dao.mapper.impl;

import by.vlad.library.entity.User;
import by.vlad.library.model.dao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        return null;
    }
}
