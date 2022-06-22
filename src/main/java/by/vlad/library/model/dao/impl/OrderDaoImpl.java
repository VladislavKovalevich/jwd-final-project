package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.entity.OrderType;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.OrderDao;
import by.vlad.library.model.dao.mapper.Mapper;
import by.vlad.library.model.dao.mapper.impl.OrderMapper;
import by.vlad.library.model.pool.ConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static final String FIND_ORDERS_BY_USER_ID = "" +
            "SELECT order_id, order_create_date, order_ordered_date, order_rejected_date, order_returned_date, " +
            "order_status_name, order_type_name " +
            "FROM library.orders " +
            "JOIN order_status ON orders_status_id = order_status_id " +
            "JOIN order_types ON orders_types_id = order_type_id " +
            "WHERE users_id = ? ";

    private static final String INSERT_ORDER = "" +
            "INSERT INTO orders(`users_id`, `order_create_date`, `orders_types_id`) VALUES(?, ?, ?)";

    private static final String ADD_BOOK_TO_ORDER = "INSERT INTO books_orders (`orders_id`, `books_id`) VALUES (?, ?)";

    private static final String DELETE_BOOK_FROM_ORDER = "DELETE FROM books_orders WHERE orders_id = ? AND books_id = ?";

    private static final String DELETE_ORDER = "DELETE FROM orders WHERE order_id = ?";

    private static final String FIND_ALL_ORDERS = "SELECT order_id, order_create_date, order_ordered_date, " +
            "order_rejected_date, order_returned_date, order_status_name, order_type_name, user_id, user_login, " +
            "user_email, user_name, user_surname, user_passport_serial_number, user_mobile_phone, user_is_banned " +
            "FROM orders " +
            "JOIN order_status ON orders_status_id = order_status_id " +
            "JOIN order_types ON orders_types_id = order_type_id " +
            "JOIN users ON users_id = user_id ";

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
        return false;
    }

    @Override
    public boolean delete(Order order) throws DaoException {
        return false;
    }

    @Override
    public Order update(Order order) throws DaoException {
        return null;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return null;
    }

    @Override
    public List<Order> findOrdersByUserId(long userId) throws DaoException {
        List<Order> orders = new ArrayList<>();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ORDERS_BY_USER_ID)) {

            statement.setLong(1, userId);

            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Order temp = Order.getOrderBuilder()
                            .withId(resultSet.getLong(1))
                            .withCreateDate(LocalDate.parse(resultSet.getString(2)))
                            //.withOrderedDate(LocalDate.parse(resultSet.getString(3)))
                            //.withRejectedDate(LocalDate.parse(resultSet.getString(4)))
                            //.withReturnedDate(LocalDate.parse(resultSet.getString(5)))
                            .withOrderStatus(OrderStatus.getStatus(resultSet.getString(6)))
                            .withOrderType(OrderType.getType(resultSet.getString(7)))
                            .buildOrder();

                    orders.add(temp);
                }
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return orders;
    }

    @Override
    public List<Order> findAllOrders() throws DaoException {
        List<Order> orders;
        Mapper<Order> orderMapper = OrderMapper.getInstance();

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_ORDERS)){

            try(ResultSet resultSet = statement.executeQuery()){
                orders = orderMapper.map(resultSet);
            }

        }catch (SQLException e){
            throw new DaoException(e);
        }

        return orders;
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
            throw new DaoException(e);
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
            throw new DaoException(e);
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
            throw new DaoException(e);
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
            throw new DaoException(e);
        }

        return rows == 1;
    }
}
