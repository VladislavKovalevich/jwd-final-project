package by.vlad.library.model.dao;

import by.vlad.library.entity.Author;

public interface AuthorDao extends BasicDao<Author>{
    boolean isAuthorExists(String name, String surname);
}
