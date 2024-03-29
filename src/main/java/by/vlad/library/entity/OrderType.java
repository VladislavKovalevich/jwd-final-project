package by.vlad.library.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

/**
 * {@code OrderStatus} enum represent type of order
 */
public enum OrderType {
    READING_ROOM( 1),
    SUBSCRIPTION(20);

    private static final Logger logger = LogManager.getLogger();

    private final int days;

    OrderType(int days) {
        this.days = days;
    }

    /**
     * Method to get order type by string name
     * @param type - order name, type {@link String}
     * @return order type, type {@link OrderType}
     */
    public static OrderType getType(String type){
        OrderType orderType;

        try {
            orderType = OrderType.valueOf(type.toUpperCase().replace(' ', '_'));
        }catch (IllegalArgumentException | NullPointerException e){
            logger.warn("OrderType " + type + " does not exist");
            orderType = READING_ROOM;
        }

        return orderType;
    }

    public static String getTypeName(OrderType type){
        String name = type.toString();
        String result = name.substring(1)
                .toLowerCase(Locale.ROOT)
                .replace('_', ' ');

        Character ch = Character.toUpperCase(name.charAt(0));

        return ch + result;
    }

    public int getDays() {
        return days;
    }
}
