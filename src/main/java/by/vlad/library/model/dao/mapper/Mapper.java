package by.vlad.library.model.dao.mapper;

import by.vlad.library.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Mapper<T extends AbstractEntity> {
    List<T> map(ResultSet resultSet) throws SQLException;
}