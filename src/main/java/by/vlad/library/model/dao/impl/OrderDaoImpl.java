package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Order;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.OrderDao;
import by.vlad.library.model.dao.mapper.Mapper;
import by.vlad.library.model.dao.mapper.impl.OrderMapper;
import by.vlad.library.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.vlad.library.model.dao.ColumnName.*;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String FIND_ORDERS_BY_USER_ID = "SELECT order_id, order_create_date, order_reserved_date, order_ordered_date, " +
            "order_rejected_date, order_returned_date, order_status_name, order_type_name, user_id, user_login, " +
            "user_email, user_name, user_surname, user_passport_serial_number, user_mobile_phone, user_is_banned " +
            "FROM orders " +
            "JOIN order_status ON orders_status_id = order_status_id " +
            "JOIN order_types ON orders_types_id = order_type_id " +
            "JOIN users ON users_id = user_id " +
            "WHERE users_id = ? ";

    private static final String INSERT_ORDER = "" +
            "INSERT INTO orders(`users_id`, `order_create_date`, `orders_types_id`) VALUES(?, ?, ?)";

    private static final String ADD_BOOK_TO_ORDER = "INSERT INTO books_orders (`orders_id`, `books_id`) VALUES (?, ?)";

    private static final String DELETE_BOOK_FROM_ORDER = "DELETE FROM books_orders WHERE orders_id = ? AND books_id = ?";

    private static final String DELETE_ORDER = "DELETE FROM orders WHERE order_id = ?";

    private static final String FIND_ALL_ORDERS = "SELECT order_id, order_create_date, order_reserved_date, order_ordered_date, " +
            "order_rejected_date, order_returned_date, order_status_name, order_type_name, user_id, user_login, " +
            "user_email, user_name, user_surname, user_passport_serial_number, user_mobile_phone, user_is_banned " +
            "FROM orders " +
            "JOIN order_status ON orders_status_id = order_status_id " +
            "JOIN order_types ON orders_types_id = order_type_id " +
            "JOIN users ON users_id = user_id ";

    private static final String FIND_ORDER_BY_ID = "SELECT order_id, order_create_date, order_reserved_date, order_ordered_date, " +
            "order_rejected_date, order_returned_date, order_status_name, order_type_name, user_id, user_login, " +
            "user_email, user_name, user_surname, user_passport_serial_number, user_mobile_phone, user_is_banned " +
            "FROM orders " +
            "JOIN order_status ON orders_status_id = order_status_id " +
            "JOIN order_types ON orders_types_id = order_type_id " +
            "JOIN users ON users_id = user_id " +
            "WHERE order_id = ?";

    private static final String CHANGE_ORDER_STATUS =
            "UPDATE orders " +
                    "SET orders_status_id = ?, ";

    private static final String WHERE_ORDER_ID =  "WHERE order_id = ?;  ";

    private static final String REMOVE_COPIES = "" +
            "UPDATE books " +
            "SET book_copies_number = book_copies_number";

    private static final String INC = " + 1";
    private static final String DEC = " - 1";

    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_IN_START = " IN(";
    private static final String SQL_IN_END = ") ";
    private static final String SQL_AND = " AND ";

    private static OrderDaoImpl instance;

    public static OrderDaoImpl getInstance() {
        if (instance == null){
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    private OrderDaoImpl(){}

    @Override
    public boolean insert(Order order) throws DaoException {
        logger.error("Unavailable operation to entity Order");
        throw new UnsupportedOperationException("Unavailable operation to entity Order");
    }

    @Override
    public boolean delete(Order order) throws DaoException {
        logger.error("Unavailable operation to entity Order");
        throw new UnsupportedOperationException("Unavailable operation to entity Order");
    }

    @Override
    public Order update(Order order) throws DaoException {
        logger.error("Unavailable operation to entity Order");
        throw new UnsupportedOperationException("Unavailable operation to entity Order");
    }

    @Override
    public List<Order> findAll() throws DaoException {
        logger.error("Unavailable operation to entity Order");
        throw new UnsupportedOperationException("Unavailable operation to entity Order");
    }

    @Override
    public List<Order> findOrdersByUserId(long userId) throws DaoException {
        List<Order> orders;
        Mapper<Order> orderMapper = OrderMapper.getInstance();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ORDERS_BY_USER_ID)) {

            statement.setLong(1, userId);

            try(ResultSet resultSet = statement.executeQuery()){
                orders = orderMapper.map(resultSet);
            }

        }catch (SQLException e){
            logger.error("SQL request findOrdersByUserId for table library.orders was failed" + e);
            throw new DaoException("SQL request findOrdersByUserId for table library.orders was failed", e);
        }

        return orders;
    }

    @Override
    public List<Order> findOrdersByStatus(String orderStatusesStr) throws DaoException {
        List<Order> orders;
        Mapper<Order> orderMapper = OrderMapper.getInstance();

        StringBuilder sqlFindOrdersByStatus = new StringBuilder(FIND_ALL_ORDERS);

        sqlFindOrdersByStatus.append(SQL_WHERE)
                .append(ORDER_STATUS_NAME_COL)
                .append(SQL_IN_START)
                .append(orderStatusesStr)
                .append(SQL_IN_END);

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlFindOrdersByStatus.toString())){

            try(ResultSet resultSet = statement.executeQuery()){
                orders = orderMapper.map(resultSet);
            }

        }catch (SQLException e){
            logger.error("SQL request findOrdersByStatus for table library.orders was failed" + e);
            throw new DaoException("SQL request findOrdersByStatus for table library.orders was failed", e);
        }

        return orders;
    }

    @Override
    public Optional<Order> findOrderById(long orderId) throws DaoException {
        Optional<Order> optionalOrder;
        Mapper<Order> orderMapper = OrderMapper.getInstance();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ORDER_BY_ID)){

            statement.setLong(1, orderId);

            try(ResultSet resultSet = statement.executeQuery()){
                List<Order> temp = orderMapper.map(resultSet);
                optionalOrder = Optional.of(temp.get(0));
            }

        }catch (SQLException e){
            logger.error("SQL request findOrderById for table library.orders was failed" + e);
            throw new DaoException("SQL request findOrderById for table library.orders was failed", e);
        }

        return optionalOrder;
    }

    @Override
    public Optional<Long> insertOrder(long userId, LocalDate createdDate, long orderTypeId) throws DaoException {
        Optional<Long> insertedIdOrder = Optional.empty();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, userId);
            statement.setDate(2, Date.valueOf(createdDate));
            statement.setLong(3, orderTypeId);

            statement.executeUpdate();

            try(ResultSet resultSet = statement.getGeneratedKeys()){
                if (resultSet.next()){
                    insertedIdOrder = Optional.of(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            logger.error("SQL request insertOrder for table library.orders was failed" + e);
            throw new DaoException("SQL request insertOrder for table library.orders was failed", e);
        }

        return insertedIdOrder;
    }

    @Override
    public boolean insertBookToOrder(long orderId, long bookId) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(ADD_BOOK_TO_ORDER)){

            statement.setLong(1, orderId);
            statement.setLong(2, bookId);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            logger.error("SQL request insertBookToOrder for table library.orders was failed" + e);
            throw new DaoException("SQL request insertBookToOrder for table library.orders was failed", e);
        }

        return rows == 1;
    }

    @Override
    public boolean deleteBookFromOrder(long orderId, long bookId) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_FROM_ORDER)){

            statement.setLong(1, orderId);
            statement.setLong(2, bookId);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            logger.error("SQL request deleteBookFromOrder for table library.orders was failed" + e);
            throw new DaoException("SQL request deleteBookFromOrder for table library.orders was failed", e);
        }

        return rows == 1;
    }

    @Override
    public boolean deleteOrder(long orderId) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_ORDER)){

            statement.setLong(1, orderId);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            logger.error("SQL request deleteOrder for table library.orders was failed" + e);
            throw new DaoException("SQL request deleteOrder for table library.orders was failed", e);
        }

        return rows == 1;
    }

    @Override
    public boolean updateOrderStatus(long orderId, LocalDate date, long statusId) throws DaoException {
        int rows;

        String changeOrderStatusSql = buildChangeOrderStatusString(statusId);

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(changeOrderStatusSql)){

            statement.setLong(1, statusId);
            statement.setDate(2, Date.valueOf(date));
            statement.setLong(3, orderId);

            rows = statement.executeUpdate();

        }catch (SQLException e){
            logger.error("SQL request updateOrderStatus for table library.orders was failed" + e);
            throw new DaoException("SQL request updateOrderStatus for table library.orders was failed", e);
        }

        return rows == 1;
    }

    @Override
    public boolean updateStatusAndCopiesNumber(long orderId, StringBuilder booksIdStrBuilder, int booksCount, LocalDate date, long statusId) throws DaoException {
        int rows = 0;

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            String changeOrderStatusSql = buildChangeOrderStatusString(statusId);

            statement = connection.prepareStatement(changeOrderStatusSql);

            statement.setLong(1, statusId);
            statement.setDate(2, Date.valueOf(date));
            statement.setLong(3, orderId);

            statement.executeUpdate();

            StringBuilder sqlBuilder = new StringBuilder(REMOVE_COPIES);

            if (statusId == 5){
                sqlBuilder.append(INC);
            }else{
                sqlBuilder.append(DEC);
            }

            sqlBuilder.append(SQL_WHERE).append(BOOK_ID_COL)
                    .append(SQL_IN_START).append(booksIdStrBuilder)
                    .append(SQL_IN_END);

            statement = connection.prepareStatement(sqlBuilder.toString());

            rows = statement.executeUpdate();

            if (rows == booksCount){
                connection.commit();
                return true;
            }else{
                connection.rollback();
                return false;
            }

        } catch (SQLException e) {
            logger.error("SQL request insertImage for table library.orders was failed" + e);
            try{
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("Rollback for table library.orders was failed" + ex);
                throw new DaoException("Rollback for table library.orders was failed", ex);
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

        return rows == booksCount;
    }

    private String buildChangeOrderStatusString(long statusId){
        StringBuilder sqlChangeOrderStatus = new StringBuilder(CHANGE_ORDER_STATUS);

        switch ((int) statusId) {
            case 2 -> sqlChangeOrderStatus.append(ORDER_RESERVED_DATE_COL);
            case 3 -> sqlChangeOrderStatus.append(ORDER_ORDERED_DATE_COL);
            case 4 -> sqlChangeOrderStatus.append(ORDER_REJECTED_DATE_COL);
            case 5 -> sqlChangeOrderStatus.append(ORDER_RETURNED_DATE_COL);
        }

        sqlChangeOrderStatus.append("= ? ").append(WHERE_ORDER_ID);

        return sqlChangeOrderStatus.toString();
    }
}
