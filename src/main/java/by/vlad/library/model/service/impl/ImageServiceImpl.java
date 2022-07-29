package by.vlad.library.model.service.impl;

import by.vlad.library.entity.Book;
import by.vlad.library.entity.Image;
import by.vlad.library.exception.DaoException;
import by.vlad.library.exception.ServiceException;
import by.vlad.library.model.dao.ImageDao;
import by.vlad.library.model.dao.impl.ImageDaoImpl;
import by.vlad.library.model.service.ImageService;
import by.vlad.library.util.ImageEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code ImageServiceImpl} class implements functional of {@link ImageService}
 * @see Image
 * @see ImageService
 */
public class ImageServiceImpl implements ImageService {
    private static final Logger logger = LogManager.getLogger();
    private static ImageServiceImpl instance;

    public static ImageServiceImpl getInstance() {
        if (instance == null){
            instance = new ImageServiceImpl();
        }
        return instance;
    }

    private ImageServiceImpl(){}

    @Override
    public void createNewImage(byte[] imageContent, Book book) throws ServiceException {
        ImageDao imageDao = ImageDaoImpl.getInstance();
        Image image = new Image(imageContent);

        try {
            if (imageDao.insertImage(image, book.getId())){
                image.setEncodeImage(ImageEncoder.getInstance().encodeImage(imageContent));
                book.setImage(image);
            }
        } catch (DaoException e) {
            logger.error("Method createNewImage from image service was failed" + e);
            throw new ServiceException("Method createNewImage from image service was failed", e);
        }

    }

    @Override
    public void updateImage(long imageId, byte[] imageContent, Book book) throws ServiceException {
        ImageDao imageDao = ImageDaoImpl.getInstance();
        Image image = new Image(imageId, imageContent);

        try {
            if (imageDao.updateImage(image)){
                image.setEncodeImage(ImageEncoder.getInstance().encodeImage(imageContent));
                book.setImage(image);
            }
        }catch (DaoException e){
            logger.error("Method updateImage from image service was failed" + e);
            throw new ServiceException("Method updateImage from image service was failed", e);
        }
    }
}
