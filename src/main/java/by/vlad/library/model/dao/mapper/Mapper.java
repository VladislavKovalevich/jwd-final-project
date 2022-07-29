package by.vlad.library.model.dao.mapper;

import by.vlad.library.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * {@code Mapper} interface represent functional retrieving object extends {@link AbstractEntity} from {@link ResultSet}
 */
public interface Mapper<T extends AbstractEntity> {
    /**
     * Retrieve object extends {@link AbstractEntity}
     * @param resultSet execution result sql instruction, type {@link ResultSet}
     * @return object extends {@link AbstractEntity}
     * @throws SQLException when result of sql request is wrong
     */
    List<T> map(ResultSet resultSet) throws SQLException;
}