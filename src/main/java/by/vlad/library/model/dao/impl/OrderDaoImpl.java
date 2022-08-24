package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.OrderDao;
import by.vlad.library.model.dao.mapper.Mapper;
import by.vlad.library.model.dao.mapper.impl.OrderMapper;
import by.vlad.library.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.vlad.library.controller.command.AttributeAndParamsNames.*;
import static by.vlad.library.model.dao.ColumnName.*;

/**
 * {@code OrderDaoImpl} class implements functional of {@link OrderDao}
 *
 * @see Order
 * @see OrderDao
 * @see by.vlad.library.model.dao.BasicDao
 */
public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String SQL_SELECT_ORDERS_BY_USER_ID =
            "SELECT order_id, order_create_date, order_reserved_date, order_accepted_date, " +
                    "order_rejected_date, order_returned_date, order_estimated_return_date, " +
                    "order_status_name, order_type_name, user_id, user_login, user_email, user_name, " +
                    "user_surname, user_passport_serial_number, user_mobile_phone, user_is_banned " +
                    "FROM orders " +
                    "JOIN order_status ON orders_status_id = order_status_id " +
                    "JOIN order_types ON orders_types_id = order_type_id " +
                    "JOIN users ON users_id = user_id " +
                    "WHERE users_id = ? ";

    private static final String SQL_INSERT_ORDER = "" +
            "INSERT INTO orders(`users_id`, `order_create_date`, `orders_types_id`) " +
            "VALUES(?, ?, ?)";

    private static final String SQL_INSERT_BOOK_TO_ORDER =
            "INSERT INTO books_orders (`orders_id`, `books_id`) " +
                    "VALUES (?, ?)";

    private static final String SQL_DELETE_BOOK_FROM_ORDER =
            "DELETE FROM books_orders " +
                    "WHERE orders_id = ? AND books_id = ?";

    private static final String SQL_DELETE_ORDER =
            "DELETE FROM orders " +
                    "WHERE order_id = ?";

    private static final String SQL_SELECT_ALL_ORDERS =
            "SELECT order_id, order_create_date, order_reserved_date, order_accepted_date, " +
                    "order_rejected_date, order_returned_date, order_estimated_return_date, " +
                    "order_status_name, order_type_name, user_id, user_login, user_email, user_name, " +
                    "user_surname, user_passport_serial_number, user_mobile_phone, user_is_banned " +
                    "FROM orders " +
                    "JOIN order_status ON orders_status_id = order_status_id " +
                    "JOIN order_types ON orders_types_id = order_type_id " +
                    "JOIN users ON users_id = user_id ";

    private static final String SQL_SELECT_ORDER_BY_ID =
            "SELECT order_id, order_create_date, order_reserved_date, order_accepted_date, " +
                    "order_rejected_date, order_returned_date, order_estimated_return_date, order_status_name, " +
                    "order_type_name, user_id, user_login, user_email, user_name, user_surname, " +
                    "user_passport_serial_number, user_mobile_phone, user_is_banned " +
                    "FROM orders " +
                    "JOIN order_status ON orders_status_id = order_status_id " +
                    "JOIN order_types ON orders_types_id = order_type_id " +
                    "JOIN users ON users_id = user_id " +
                    "WHERE order_id = ?";

    private static final String SQL_SELECT_BOOK_COUNT_IN_ORDER_BY_ID = "" +
            "SELECT COUNT(books_id) as `count_col` FROM library.books_orders " +
            "WHERE orders_id = ? AND books_id = ?";


    private static final String SQL_SELECT_ORDER_COUNT_BY_USER_ID = "" +
            "SELECT COUNT(order_id) as `count_col` FROM library.orders " +
            "WHERE users_id = ? AND orders_status_id NOT IN (4,5,6);";

    private static final String SQL_SELECT_BOOKS_COUNT_BY_ORDER_ID = "" +
            "SELECT COUNT(books_id) as `count_col` FROM library.books_orders " +
            "WHERE orders_id = ?";

    private static final String SQL_SELECT_COUNT_OF_OVERDUE_ORDERS_BY_DATE_AND_USER_ID = "" +
            "SELECT COUNT(order_id) as `count_col` FROM orders " +
            "WHERE users_id = ? AND orders_status_id = ? AND order_estimated_return_date between ? AND ?;";

    private static final String SQL_SELECT_NUMBER_OF_USER_ORDERS =
            "SELECT COUNT(order_id) as count_col FROM orders WHERE users_id = ?";

    private static final String SQL_UPDATE_ORDER_STATUS =
            "UPDATE orders " +
                    "SET orders_status_id = ?, ";

    private static final String SQL_WHERE_ORDER_ID = "WHERE order_id = ?;  ";

    private static final String SQL_REMOVE_COPIES = "" +
            "UPDATE books " +
            "SET book_copies_number = book_copies_number";

    private static final String INC = " + 1";
    private static final String DEC = " - 1";

    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_IN_START = " IN(";
    private static final String SQL_IN_END = ") ";
    private static final String SQL_AND = " AND ";
    private static final String SQL_LIMIT = "LIMIT ?, ?";
    private static final String SQL_ORDER_BY = "ORDER BY ";
    private static final String SQL_ASC = " ASC ";
    private static final String SQL_DESC = " DESC ";

    private static final int ORDER_LIMIT = 5;
    private static final int BOOK_IN_ORDER_LIMIT = 5;
    private static final long DEFAULT_USER_ORDERS_COUNT_PER_PAGE = 4;

    private static OrderDaoImpl instance;

    public static OrderDaoImpl getInstance() {
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    private OrderDaoImpl() {
    }

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

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ORDERS_BY_USER_ID)) {

            statement.setLong(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                orders = orderMapper.map(resultSet);
            }

        } catch (SQLException e) {
            logger.error("SQL request findOrdersByUserId for table library.orders was failed" + e);
            throw new DaoException("SQL request findOrdersByUserId for table library.orders was failed", e);
        }

        return orders;
    }

    @Override
    public List<Order> findOrdersByStatus(String orderStatusesStr) throws DaoException {
        List<Order> orders;
        Mapper<Order> orderMapper = OrderMapper.getInstance();

        StringBuilder sqlFindOrdersByStatus = new StringBuilder(SQL_SELECT_ALL_ORDERS);

        sqlFindOrdersByStatus.append(SQL_WHERE)
                .append(ORDER_STATUS_NAME_COL)
                .append(SQL_IN_START)
                .append(orderStatusesStr)
                .append(SQL_IN_END);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlFindOrdersByStatus.toString())) {

            try (ResultSet resultSet = statement.executeQuery()) {
                orders = orderMapper.map(resultSet);
            }

        } catch (SQLException e) {
            logger.error("SQL request findOrdersByStatus for table library.orders was failed" + e);
            throw new DaoException("SQL request findOrdersByStatus for table library.orders was failed", e);
        }

        return orders;
    }

    @Override
    public Optional<Order> findOrderById(long orderId) throws DaoException {
        Optional<Order> optionalOrder;
        Mapper<Order> orderMapper = OrderMapper.getInstance();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID)) {

            statement.setLong(1, orderId);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Order> temp = orderMapper.map(resultSet);
                optionalOrder = Optional.of(temp.get(0));
            }

        } catch (SQLException e) {
            logger.error("SQL request findOrderById for table library.orders was failed" + e);
            throw new DaoException("SQL request findOrderById for table library.orders was failed", e);
        }

        return optionalOrder;
    }

    @Override
    public Optional<Long> insertOrder(long userId, LocalDate createdDate, long orderTypeId) throws DaoException {
        Optional<Long> insertedIdOrder = Optional.empty();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, userId);
            statement.setDate(2, Date.valueOf(createdDate));
            statement.setLong(3, orderTypeId);

            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
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

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_BOOK_TO_ORDER)) {

            statement.setLong(1, orderId);
            statement.setLong(2, bookId);

            rows = statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQL request insertBookToOrder for table library.orders was failed" + e);
            throw new DaoException("SQL request insertBookToOrder for table library.orders was failed", e);
        }

        return rows == 1;
    }

    @Override
    public boolean deleteBookFromOrder(long orderId, long bookId) throws DaoException {
        int rows;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BOOK_FROM_ORDER)) {

            statement.setLong(1, orderId);
            statement.setLong(2, bookId);

            rows = statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQL request deleteBookFromOrder for table library.orders was failed" + e);
            throw new DaoException("SQL request deleteBookFromOrder for table library.orders was failed", e);
        }

        return rows == 1;
    }

    @Override
    public boolean deleteOrder(long orderId) throws DaoException {
        int rows;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER)) {

            statement.setLong(1, orderId);

            rows = statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQL request deleteOrder for table library.orders was failed" + e);
            throw new DaoException("SQL request deleteOrder for table library.orders was failed", e);
        }

        return rows == 1;
    }

    @Override
    public Optional<Order> updateOrderStatus(Order order) throws DaoException {
        int rows;

        String changeOrderStatusSql = buildChangeOrderStatusString(order.getStatus());

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(changeOrderStatusSql)) {

            statement.setLong(1, order.getStatus().ordinal() + 1);
            if (order.getStatus() == OrderStatus.RESERVED) {
                statement.setDate(2, Date.valueOf(order.getReservedDate()));
            } else {
                statement.setDate(2, Date.valueOf(order.getRejectedDate()));
            }
            statement.setLong(3, order.getId());

            rows = statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQL request updateOrderStatus for table library.orders was failed" + e);
            throw new DaoException("SQL request updateOrderStatus for table library.orders was failed", e);
        }

        return rows == 1 ? Optional.of(order) : Optional.empty();
    }

    @Override
    public Optional<Order> updateStatusAndCopiesNumber(Order order, String booksIdString, int booksCount) throws DaoException {
        Optional<Order> optionalOrder = Optional.empty();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            String changeOrderStatusSql = buildChangeOrderStatusString(order.getStatus());

            statement = connection.prepareStatement(changeOrderStatusSql);

            statement.setLong(1, order.getStatus().ordinal() + 1);

            if (order.getStatus() == OrderStatus.ACCEPTED) {
                statement.setDate(2, Date.valueOf(order.getAcceptedDate()));
                statement.setDate(3, Date.valueOf(order.getEstimatedReturnDate()));
                statement.setLong(4, order.getId());
            } else {
                statement.setDate(2, Date.valueOf(order.getReturnedDate()));
                statement.setLong(3, order.getId());
            }

            statement.executeUpdate();

            StringBuilder sqlBuilder = new StringBuilder(SQL_REMOVE_COPIES);

            if (order.getStatus() == OrderStatus.ACCEPTED) {
                sqlBuilder.append(DEC);
            } else {
                sqlBuilder.append(INC);
            }

            sqlBuilder.append(SQL_WHERE).append(BOOK_ID_COL)
                    .append(SQL_IN_START).append(booksIdString)
                    .append(SQL_IN_END);

            statement = connection.prepareStatement(sqlBuilder.toString());

            int rows = statement.executeUpdate();

            if (rows == booksCount) {
                connection.commit();
                optionalOrder = Optional.of(order);
            } else {
                connection.rollback();
            }

        } catch (SQLException e) {
            logger.error("SQL request insertImage for table library.orders was failed" + e);
            try {
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

        return optionalOrder;
    }

    @Override
    public boolean bookIsAlreadyInOrder(long orderId, long bookId) throws DaoException {
        int rows = 0;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BOOK_COUNT_IN_ORDER_BY_ID)) {

            statement.setLong(1, orderId);
            statement.setLong(2, bookId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rows = resultSet.getInt(COUNT_COL);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL request bookIsAlreadyInOrder for table library.orders was failed" + e);
            throw new DaoException("SQL request bookIsAlreadyInOrder for table library.orders was failed", e);
        }

        return rows > 0;
    }

    @Override
    public boolean isLimitOfBooks(long orderId) throws DaoException {
        int rows = 0;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BOOKS_COUNT_BY_ORDER_ID)) {

            statement.setLong(1, orderId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rows = resultSet.getInt(COUNT_COL);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL request isLimitOfBooks for table library.orders was failed" + e);
            throw new DaoException("SQL request isLimitOfBooks for table library.orders was failed", e);
        }

        return rows >= BOOK_IN_ORDER_LIMIT;
    }

    @Override
    public boolean isOrderCountLimit(long userId) throws DaoException {
        int rows = 0;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ORDER_COUNT_BY_USER_ID)) {

            statement.setLong(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rows = resultSet.getInt(COUNT_COL);
                }
            }

        } catch (SQLException e) {
            logger.error("SQL request isOrderCountLimit for table library.orders was failed" + e);
            throw new DaoException("SQL request isOrderCountLimit for table library.orders was failed", e);
        }

        return rows >= ORDER_LIMIT;
    }

    @Override
    public int findOverdueOrders(long userId, LocalDate startDate, LocalDate endDate) throws DaoException {
        int rows = 0;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COUNT_OF_OVERDUE_ORDERS_BY_DATE_AND_USER_ID)) {

            long statusId = OrderStatus.OVERDUE.ordinal() + 1;

            statement.setLong(1, userId);
            statement.setLong(2, statusId);

            statement.setDate(3, Date.valueOf(startDate));
            statement.setDate(4, Date.valueOf(endDate));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rows = resultSet.getInt(COUNT_COL);
                }
            }

        } catch (SQLException e) {
            logger.error("SQL request findOverdueOrders for table library.orders was failed" + e);
            throw new DaoException("SQL request findOverdueOrders for table library.orders was failed", e);
        }

        return rows;
    }

    @Override
    public List<Order> getUserOrders(long userId, long tempPage, Map<String, Long[]> filterData) throws DaoException {
        List<Order> orders;
        Mapper<Order> mapper = OrderMapper.getInstance();

        StringBuilder resultSQL = new StringBuilder(SQL_SELECT_ORDERS_BY_USER_ID);

        if (!filterData.isEmpty()) {
            resultSQL.append(buildSQLFilterString(filterData));
        }

        resultSQL.append(SQL_LIMIT);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(resultSQL.toString())) {

            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, (tempPage - 1) * DEFAULT_USER_ORDERS_COUNT_PER_PAGE);
            preparedStatement.setLong(3, DEFAULT_USER_ORDERS_COUNT_PER_PAGE);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                orders = mapper.map(resultSet);
            }

        } catch (SQLException e) {
            logger.error("SQL request getUserOrders for table library.books was failed" + e);
            throw new DaoException("SQL request getUserOrders for table library.books was failed", e);
        }

        return orders;
    }

    @Override
    public long findNumberOfUserOrdersPage(long userId, Map<String, Long[]> filterMap) throws DaoException {
        int rows = 0;
        int pagesNumber;

        StringBuilder resultSQL = new StringBuilder(SQL_SELECT_NUMBER_OF_USER_ORDERS);

        if (!filterMap.isEmpty()) {
            resultSQL.append(buildSQLFilterString(filterMap));
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(resultSQL.toString())) {

            statement.setLong(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rows = resultSet.getInt(COUNT_COL);
                }
            }

        } catch (SQLException e) {
            logger.error("SQL request findNumberOfUserOrdersPage for table library.books was failed" + e);
            throw new DaoException("SQL request findNumberOfUserOrdersPage for table library.books was failed", e);
        }

        pagesNumber = (int) (rows / DEFAULT_USER_ORDERS_COUNT_PER_PAGE);

        if (rows % DEFAULT_USER_ORDERS_COUNT_PER_PAGE != 0) {
            pagesNumber++;
        }

        return pagesNumber;
    }

    private String buildChangeOrderStatusString(OrderStatus status) {
        StringBuilder sqlChangeOrderStatus = new StringBuilder(SQL_UPDATE_ORDER_STATUS);

        switch (status) {
            case RESERVED -> sqlChangeOrderStatus.append(ORDER_RESERVED_DATE_COL);
            case ACCEPTED -> sqlChangeOrderStatus.append(ORDER_ACCEPTED_DATE_COL);
            case REJECTED -> sqlChangeOrderStatus.append(ORDER_REJECTED_DATE_COL);
            case RETURNED, OVERDUE -> sqlChangeOrderStatus.append(ORDER_RETURNED_DATE_COL);
        }

        sqlChangeOrderStatus.append("= ? ");

        if (status == OrderStatus.ACCEPTED) {
            sqlChangeOrderStatus.append(", ")
                    .append(ORDER_ESTIMATED_RETURN_DATE_COL)
                    .append("= ? ");
        }

        sqlChangeOrderStatus.append(SQL_WHERE_ORDER_ID);

        return sqlChangeOrderStatus.toString();
    }

    private String buildSQLFilterString(Map<String, Long[]> filterMap) {
        StringBuilder sqlFilter = new StringBuilder(SQL_AND);

        for (Map.Entry<String, Long[]> e : filterMap.entrySet()) {
            String s = Arrays.toString(e.getValue());

            switch (e.getKey()) {
                case ORDER_STATUS -> {
                    sqlFilter.append(ORDER_STATUSES_ID_COL)
                            .append(SQL_IN_START)
                            .append(s, 1, s.length() - 1)
                            .append(SQL_IN_END)
                            .append(SQL_AND);
                }

                case ORDER_TYPE -> {
                    sqlFilter.append(ORDER_TYPES_ID_COL)
                            .append(SQL_IN_START)
                            .append(s, 1, s.length() - 1)
                            .append(SQL_IN_END)
                            .append(SQL_AND);
                }
            }
        }

        sqlFilter.delete(sqlFilter.length() - SQL_AND.length(), sqlFilter.length() - 1);

        int sortParam = Math.toIntExact(filterMap.get(ORDER_SORT_PARAM)[0]);
        int sortType = Math.toIntExact(filterMap.get(ORDER_SORT_TYPE)[0]);

        sqlFilter.append(SQL_ORDER_BY);

        switch (sortParam){
            case 1 -> {
                sqlFilter.append(ORDER_CREATE_DATE_COL);
            }
            case 2 -> {
                sqlFilter.append(ORDER_RESERVED_DATE_COL);
            }
            case 3 -> {
                sqlFilter.append(ORDER_ACCEPTED_DATE_COL);
            }
            case 4 -> {
                sqlFilter.append(ORDER_REJECTED_DATE_COL);
            }
            case 5 -> {
                sqlFilter.append(ORDER_RETURNED_DATE_COL);
            }
            case 6 -> {
                sqlFilter.append(ORDER_ESTIMATED_RETURN_DATE_COL);
            }
        }

        if (sortType == 1){
            sqlFilter.append(SQL_ASC);
        }else{
            sqlFilter.append(SQL_DESC);
        }

        return sqlFilter.toString();
    }
}
