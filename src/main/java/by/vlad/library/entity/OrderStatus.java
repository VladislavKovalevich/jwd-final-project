package by.vlad.library.entity;

public enum OrderStatus {
    CREATED,
    RESERVED,
    ORDERED,
    REJECTED,
    RETURNED;

    public static OrderStatus getStatus(String statusName){
        OrderStatus status;

        try{
            status = OrderStatus.valueOf(statusName.toUpperCase());
        }catch (NullPointerException | IllegalArgumentException e){
            status = CREATED;
            //logger
        }

        return status;
    }
}
