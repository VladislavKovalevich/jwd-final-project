package by.vlad.library.model.dao;

import by.vlad.library.entity.Publisher;

public interface PublisherDao extends BasicDao<Publisher> {
    boolean isPublisherExists(String publisherName);
}
