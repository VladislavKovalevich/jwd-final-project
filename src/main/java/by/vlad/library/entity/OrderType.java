package by.vlad.library.entity;

public enum OrderType {
    READING_ROOM,
    SUBSCRIPTION;

    public static OrderType getType(String type){
        OrderType orderType;

        try {
            orderType = OrderType.valueOf(type.toUpperCase().replace(' ', '_'));
        }catch (IllegalArgumentException | NullPointerException e){
            orderType = READING_ROOM;
            //logger
        }

        return orderType;
    }
}
