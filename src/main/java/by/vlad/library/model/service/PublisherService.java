package by.vlad.library.model.service;

import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface PublisherService {
    List<Publisher> findAllPublishers() throws ServiceException;

    boolean addNewPublisher(Map<String, String> mapData) throws ServiceException;
}
