package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Image;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.ImageDao;
import by.vlad.library.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * {@code ImageDaoImpl} class implements functional of {@link ImageDao}
 * @see Image
 * @see ImageDao
 * @see by.vlad.library.model.dao.BasicDao
 */
public class ImageDaoImpl implements ImageDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String INSERT_IMAGE = "INSERT INTO images(`image_content`) VALUES (?)";

    private static final String ADD_IMAGE_TO_BOOK = "UPDATE books SET " +
            "images_id = ? " +
            "WHERE book_id = ?";

    private static final String UPDATE_IMAGE = "UPDATE images SET image_content = ? WHERE image_id = ?";

    private static ImageDaoImpl instance;

    public static ImageDaoImpl getInstance() {
        if (instance == null){
            instance = new ImageDaoImpl();
        }
        return instance;
    }

    private ImageDaoImpl(){}

    @Override
    public boolean insert(Image image) throws DaoException {
        logger.error("Unavailable operation to entity Image");
        throw new UnsupportedOperationException("Unavailable operation to entity Image");
    }

    @Override
    public boolean delete(Image image) throws DaoException {
        logger.error("Unavailable operation to entity Image");
        throw new UnsupportedOperationException("Unavailable operation to entity Image");
    }

    @Override
    public Image update(Image image) throws DaoException {
        logger.error("Unavailable operation to entity Image");
        throw new UnsupportedOperationException("Unavailable operation to entity Image");
    }

    @Override
    public List<Image> findAll() throws DaoException {
        logger.error("Unavailable operation to entity Image");
        throw new UnsupportedOperationException("Unavailable operation to entity Image");
    }

    @Override
    public boolean insertImage(Image image, long bookId) throws DaoException {
        int rows = 0;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(INSERT_IMAGE, Statement.RETURN_GENERATED_KEYS);
            statement.setBytes(1, image.getContent());

            statement.executeUpdate();
            long imageId = 0;

            try(ResultSet resultSet = statement.getGeneratedKeys()){
                if (resultSet.next()){
                    imageId = resultSet.getLong(1);
                }
            }

            statement = connection.prepareStatement(ADD_IMAGE_TO_BOOK);

            statement.setLong(1, imageId);
            statement.setLong(2, bookId);

            rows = statement.executeUpdate();


            if (rows == 1){
                connection.commit();
            }

        } catch (SQLException e) {
            logger.error("SQL request insertImage for table library.images was failed" + e);
            try{
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("Rollback for table library.images was failed" + ex);
                throw new DaoException("Rollback for table library.images was failed", ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                statement.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("Connection or statement close was failed" + e);
                throw new DaoException("Connection or statement close was failed", e);
            }
        }

        return rows == 1;
    }

    @Override
    public boolean updateImage(Image image) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_IMAGE)){

            statement.setBytes(1,image.getContent());
            statement.setLong(2, image.getId());

            rows = statement.executeUpdate();

        }catch (SQLException e){
            logger.error("SQL request updateImage for table library.genres was failed" + e);
            throw new DaoException("SQL request updateImage for table library.images was failed", e);
        }

        return rows == 1;
    }
}
