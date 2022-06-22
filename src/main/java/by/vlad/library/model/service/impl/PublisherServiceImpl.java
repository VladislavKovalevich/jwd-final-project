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
    public Optional<Publisher> addNewPublisher(Map<String, String> mapData) throws ServiceException {
        Optional<Publisher> optionalPublisher = Optional.empty();
        String newPublisherName = mapData.get(PUBLISHER_NAME_FORM);
        PublisherValidator publisherValidator = PublisherValidatorImpl.getInstance();

        if (!publisherValidator.validatePublisherName(newPublisherName)){
            mapData.put(WRONG_PUBLISHER_NAME_FORM, PublisherValidator.WRONG_FORMAT_MARKER);
            return optionalPublisher;
        }

        PublisherDao publisherDao = PublisherDaoImpl.getInstance();

        try {
            if (!publisherDao.isPublisherExists(newPublisherName)) {
                Publisher publisher = new Publisher();
                publisher.setName(newPublisherName);

                optionalPublisher = publisherDao.addPublisher(publisher);
            }else{
                mapData.put(WRONG_PUBLISHER_EXISTS_FORM, PublisherService.PUBLISHER_EXISTS_MARKER);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return optionalPublisher;
    }

    @Override
    public Optional<Publisher> updatePublisher(Map<String, String> mapData) throws ServiceException {
        PublisherValidator publisherValidator = PublisherValidatorImpl.getInstance();
        Optional<Publisher> optionalPublisher = Optional.empty();

        String publisherName = mapData.get(PUBLISHER_NAME_FORM);
        String[] oldPublisherParams = mapData.get(PUBLISHER_FORM).split(DELIMITER);
        long publisherId = Long.parseLong(oldPublisherParams[0]);

        if (!publisherValidator.validatePublisherName(publisherName)){
            mapData.put(WRONG_PUBLISHER_NAME_FORM, PublisherValidator.WRONG_FORMAT_MARKER);
            return optionalPublisher;
        }

        PublisherDao publisherDao = PublisherDaoImpl.getInstance();

        try {
            if(!publisherDao.isPublisherExists(publisherName)){
                Publisher publisher = new Publisher(publisherId, publisherName);
                optionalPublisher = publisherDao.updatePublisher(publisher);

            }else{
                mapData.put(WRONG_PUBLISHER_EXISTS_FORM, PublisherService.PUBLISHER_EXISTS_MARKER);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return optionalPublisher;
    }
}
