package by.vlad.library.model.service;

import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface PublisherService {
    String PUBLISHER_EXISTS_MARKER = "publisher with this params is already exists";

    List<Publisher> findAllPublishers() throws ServiceException;

    boolean addNewPublisher(Map<String, String> mapData) throws ServiceException;

    List<Publisher> updatePublisher(Map<String, String> mapData) throws ServiceException;
}
