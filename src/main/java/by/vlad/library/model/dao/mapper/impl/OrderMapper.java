package by.vlad.library.model.dao.mapper.impl;

import by.vlad.library.entity.Order;
import by.vlad.library.entity.OrderStatus;
import by.vlad.library.entity.OrderType;
import by.vlad.library.entity.User;
import by.vlad.library.model.dao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.vlad.library.model.dao.ColumnName.*;

/**
 * {@code OrderMapper} class implements functional of {@link Mapper}
 */
public class OrderMapper implements Mapper<Order> {
    private static OrderMapper instance;

    public static OrderMapper getInstance(){
        if (instance == null){
            instance = new OrderMapper();
        }

        return instance;
    }

    private OrderMapper(){}

    @Override
    public List<Order> map(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();

        while (resultSet.next()){
            User tempUser = User.getBuilder()
                    .withId(resultSet.getLong(USER_ID_COL))
                    .withIsBanned(resultSet.getBoolean(USER_IS_BANNED_COL))
                    .withMobilePhone(resultSet.getString(USER_MOBILE_PHONE_COL))
                    .withPassportSerialNumber(resultSet.getString(USER_PASSPORT_SERIAL_NUMBER_COL))
                    .withSurname(resultSet.getString(USER_SURNAME_COL))
                    .withName(resultSet.getString(USER_NAME_COL))
                    .withLogin(resultSet.getString(USER_LOGIN_COL))
                    .withEmail(resultSet.getString(USER_EMAIL_COL))
                    .buildUser();

            Order.OrderBuilder orderBuilder = Order.getOrderBuilder()
                    .withId(resultSet.getLong(ORDER_ID_COL))
                    .withOrderType(OrderType.getType(resultSet.getString(ORDER_TYPE_NAME_COL)))
                    .withOrderStatus(OrderStatus.getStatus(resultSet.getString(ORDER_STATUS_NAME_COL)))
                    .withCreateDate(LocalDate.parse(resultSet.getString(ORDER_CREATE_DATE_COL)))
                    .withUser(tempUser);

            String returnedDate = resultSet.getString(ORDER_RETURNED_DATE_COL);
            String rejectedDate = resultSet.getString(ORDER_REJECTED_DATE_COL);
            String acceptedDate = resultSet.getString(ORDER_ACCEPTED_DATE_COL);
            String reservedDate = resultSet.getString(ORDER_RESERVED_DATE_COL);
            String estimatedReturnDate = resultSet.getString(ORDER_ESTIMATED_RETURN_DATE_COL);

            orderBuilder = reservedDate != null ? orderBuilder.withReservedDate(LocalDate.parse(reservedDate)) : orderBuilder;
            orderBuilder = rejectedDate != null ? orderBuilder.withRejectedDate(LocalDate.parse(rejectedDate)) : orderBuilder;
            orderBuilder = returnedDate != null ? orderBuilder.withReturnedDate(LocalDate.parse(returnedDate)) : orderBuilder;
            orderBuilder = acceptedDate != null ? orderBuilder.withOrderedDate(LocalDate.parse(acceptedDate)) : orderBuilder;
            orderBuilder = estimatedReturnDate != null ? orderBuilder.withEstimatedReturnDate(LocalDate.parse(estimatedReturnDate)) : orderBuilder;

            Order tempOrder = orderBuilder.buildOrder();

            orders.add(tempOrder);
        }

        return orders;
    }
}
