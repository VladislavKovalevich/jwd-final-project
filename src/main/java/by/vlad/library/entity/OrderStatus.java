package by.vlad.library.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * {@code OrderStatus} enum represent state of order
 */
public enum OrderStatus {
    CREATED,
    RESERVED,
    ACCEPTED,
    REJECTED,
    RETURNED,
    OVERDUE;

    private static final Logger logger = LogManager.getLogger();

    /**
     * Method to get order state by string name
     * @param statusName - order state name, type {@link String}
     * @return - order status, type {@link OrderStatus}
     */
    public static OrderStatus getStatus(String statusName){
        OrderStatus status;

        try{
            status = statusName != null ? OrderStatus.valueOf(statusName.toUpperCase()) : CREATED;
        }catch (IllegalArgumentException e){
            logger.warn("OrderStatus " + statusName + " does not exist");
            status = CREATED;
        }

        return status;
    }
}
