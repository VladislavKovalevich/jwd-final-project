package by.vlad.library.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum OrderType {
    READING_ROOM,
    SUBSCRIPTION;

    private static final Logger logger = LogManager.getLogger();

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
}
