package by.vlad.library.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum OrderStatus {
    CREATED,
    RESERVED,
    ORDERED,
    REJECTED,
    RETURNED;

    private static final Logger logger = LogManager.getLogger();

    public static OrderStatus getStatus(String statusName){
        OrderStatus status;

        try{
            status = OrderStatus.valueOf(statusName.toUpperCase());
        }catch (NullPointerException | IllegalArgumentException e){
            logger.warn("OrderStatus " + statusName + " does not exist");
            status = CREATED;
        }

        return status;
    }
}
