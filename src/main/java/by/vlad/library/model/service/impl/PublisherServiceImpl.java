package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Publisher;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.PublisherDao;
import by.vlad.library.model.dao.impl.PublisherDaoImpl;
import by.vlad.library.model.service.PublisherService;
import by.vlad.library.validator.PublisherValidator;
import by.vlad.library.validator.impl.PublisherValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;

/**
 * {@code PublisherServiceImpl} class implements functional of {@link PublisherService}
 * @see Publisher
 * @see PublisherService
 */
public class PublisherServiceImpl implements PublisherService {
    private static final Logger logger = LogManager.getLogger();
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
            logger.error("Method findAllPublishers from publisher service was failed" + e);
            throw new ServiceException("Method findAllPublishers from publisher service was failed", e);
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

        Publisher publisher = new Publisher();
        publisher.setName(newPublisherName);
        PublisherDao publisherDao = PublisherDaoImpl.getInstance();

        try {
            if (!publisherDao.isPublisherExists(publisher)) {

                optionalPublisher = publisherDao.addPublisher(publisher);
            }else{
                mapData.put(WRONG_PUBLISHER_EXISTS_FORM, PublisherService.PUBLISHER_EXISTS_MARKER);
            }
        } catch (DaoException e) {
            logger.error("Method addNewPublisher from publisher service was failed" + e);
            throw new ServiceException("Method addNewPublisher from publisher service was failed", e);
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

        Publisher publisher = new Publisher(publisherId, publisherName);
        PublisherDao publisherDao = PublisherDaoImpl.getInstance();

        try {
            if(!publisherDao.isPublisherExists(publisher)){
                optionalPublisher = publisherDao.updatePublisher(publisher);

            }else{
                mapData.put(WRONG_PUBLISHER_EXISTS_FORM, PublisherService.PUBLISHER_EXISTS_MARKER);
            }
        } catch (DaoException e) {
            logger.error("Method updatePublisher from publisher service was failed" + e);
            throw new ServiceException("Method updatePublisher from publisher service was failed", e);
        }

        return optionalPublisher;
    }
}
