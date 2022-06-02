package by.vlad.library.model.dao.impl;

import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.entity.OrderType;
import by.vlad.library.exception.DaoException;
import by.vlad.library.model.dao.OrderDao;
import by.vlad.library.model.pool.ConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final String FIND_ORDERS_BY_USER_ID = "" +
            "SELECT orders.id, create_date, ordered_date, rejected_date, returned_date, order_status.status, order_types.type " +
            "FROM library.orders " +
            "JOIN library.order_status ON orders.order_status_id = order_status.id " +
            "JOIN library.order_types ON orders.order_types_id = order_types.id " +
            "WHERE users_id = ? ";

    private static final String INSERT_ORDER = "" +
            "INSERT INTO orders(`users_id`, `create_date`) VALUES(?, ?)";

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
    public Order findOrderById(long orderId) throws DaoException {
        return null;
    }

    @Override
    public boolean insertOrder(long userId, LocalDate createdDate) throws DaoException {
        int rows;

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_ORDER)) {

            statement.setLong(1, userId);
            statement.setDate(2, Date.valueOf(createdDate));//?

            rows = statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return rows == 1;
    }
}
