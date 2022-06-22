package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Image;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.ImageDao;
import by.vlad.library.model.pool.ConnectionPool;

import java.sql.*;
import java.util.List;

public class ImageDaoImpl implements ImageDao {
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
        return false;
    }

    @Override
    public boolean delete(Image image) throws DaoException {
        return false;
    }

    @Override
    public Image update(Image image) throws DaoException {
        return null;
    }

    @Override
    public List<Image> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean insertImage(Image image, long bookId) throws DaoException {
        int rows = 0;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        //PreparedStatement statement = connection.prepareStatement(INSERT_IMAGE, Statement.RETURN_GENERATED_KEYS)){

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
            try{
                connection.rollback();
            } catch (SQLException ex) {
                throw new DaoException(ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        return rows == 1;
    }

    @Override
    public boolean updateImage(long imageId, byte[] imageContent) throws DaoException {
        int rows = 0;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_IMAGE)){

            statement.setBytes(1,imageContent);
            statement.setLong(2, imageId);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return rows == 1;
    }
}
