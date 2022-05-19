package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.PublisherDao;
import by.vlad.library.model.dao.impl.PublisherDaoImpl;
import by.vlad.library.model.service.PublisherService;
import by.vlad.library.validator.PublisherValidator;
import by.vlad.library.validator.impl.PublisherValidatorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

public class PublisherServiceImpl implements PublisherService {
    private static final String DELIMITER = "\\|";
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
        String newPublisherName = mapData.get(PUBLISHER_NAME_FORM);
        PublisherValidator publisherValidator = PublisherValidatorImpl.getInstance();

        if (!publisherValidator.validatePublisherName(newPublisherName)){
            mapData.put(WRONG_PUBLISHER_NAME_FORM, PublisherValidator.WRONG_FORMAT_MARKER);
            return isCreated;
        }

        PublisherDao publisherDao = PublisherDaoImpl.getInstance();

        try {
            if (!publisherDao.isPublisherExists(newPublisherName)) {
                Publisher publisher = new Publisher();
                publisher.setName(newPublisherName);

                isCreated = publisherDao.insert(publisher);
            }else{
                mapData.put(WRONG_PUBLISHER_EXISTS_FORM, PublisherService.PUBLISHER_EXISTS_MARKER);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return isCreated;
    }

    @Override
    public List<Publisher> updatePublisher(Map<String, String> mapData) throws ServiceException {
        PublisherValidator publisherValidator = PublisherValidatorImpl.getInstance();
        List<Publisher> publishers = new ArrayList<>();

        String publisherName = mapData.get(PUBLISHER_NAME_FORM);
        String[] oldPublisherParams = mapData.get(PUBLISHER_FORM).split(DELIMITER);
        long publisherId = Long.parseLong(oldPublisherParams[0]);

        if (!publisherValidator.validatePublisherName(publisherName)){
            mapData.put(WRONG_PUBLISHER_NAME_FORM, PublisherValidator.WRONG_FORMAT_MARKER);
            return publishers;
        }

        PublisherDao publisherDao = PublisherDaoImpl.getInstance();

        try {
            if(!publisherDao.isPublisherExists(publisherName)){
                Publisher publisher = new Publisher(publisherId, publisherName);
                Optional<Publisher> optionalPublisher = publisherDao.updatePublisher(publisher);

                if (optionalPublisher.isPresent()){
                    publishers = publisherDao.findAll();
                }
            }else{
                mapData.put(WRONG_PUBLISHER_EXISTS_FORM, PublisherService.PUBLISHER_EXISTS_MARKER);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return publishers;
    }
}
