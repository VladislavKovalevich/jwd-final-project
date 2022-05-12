package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.impl.PublisherDaoImpl;
import by.vlad.library.model.service.PublisherService;
import by.vlad.library.validator.PublisherValidator;
import by.vlad.library.validator.impl.PublisherValidatorImpl;

import java.util.List;
import java.util.Map;

public class PublisherServiceImpl implements PublisherService {
    private static PublisherServiceImpl instance;

    public static PublisherServiceImpl getInstance(){
        if (instance == null){
            instance = new PublisherServiceImpl();
        }
        return instance;
    }

    private PublisherServiceImpl(){}

    @Override
    public List<Publisher> findAllPublishers() throws ServiceException{
        List<Publisher> publishers;
        PublisherDaoImpl publisherDaoImpl = PublisherDaoImpl.getInstance();

        try {
            publishers = publisherDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return publishers;
    }

    @Override
    public boolean addNewPublisher(Map<String, String> mapData) throws ServiceException {
        boolean isCreated = false;
        String newPublisherName = mapData.get("NEW_PUBLISHER_NAME_FORM");
        PublisherValidator publisherValidator = PublisherValidatorImpl.getInstance();

        if (!publisherValidator.validatePublisherName(newPublisherName)){
            return isCreated;
        }

        Publisher publisher = new Publisher();
        publisher.setName(newPublisherName);

        PublisherDaoImpl publisherDaoImpl = PublisherDaoImpl.getInstance();

        try {
            isCreated = publisherDaoImpl.insert(publisher);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return isCreated;
    }
}
