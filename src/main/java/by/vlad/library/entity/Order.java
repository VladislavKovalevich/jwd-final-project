package by.vlad.library.entity;

import java.time.LocalDate;

/**
 * {@code Order} class represent order
 * @see AbstractEntity
 */
public class Order extends AbstractEntity{
    /** date when order was created */
    private LocalDate createdDate;

    /** date when order was reserved */
    private LocalDate reservedDate;

    /** date when order was accepted by administrator */
    private LocalDate acceptedDate;

    /** date when order was rejected by administrator */
    private LocalDate rejectedDate;

    /** date when order was returned by client */
    private LocalDate returnedDate;

    /** date when order should be returned by client*/
    private LocalDate estimatedReturnDate;

    /** order type
     * @see OrderType
     */
    private OrderType type;

    /**
     * order status
     * @see OrderStatus
     */
    private OrderStatus status;

    /**
     * user to which the order relates, with user role CLIENT
     * @see Role
     * @see User
     */
    private User user;

    public Order() {
    }

    public Order(long id) {
        super(id);
    }

    /**
     * method-getter for {@link Order#createdDate} field
     * @return {@link Order#createdDate}
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * Method-setter for {@link Order#createdDate} filed
     * @param createdDate - created date, type {@link LocalDate}
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * method-getter for {@link Order#reservedDate} field
     * @return {@link Order#reservedDate}
     */
    public LocalDate getReservedDate() {
        return reservedDate;
    }

    /**
     * Method-setter for {@link Order#reservedDate} filed
     * @param reservedDate - reserved date, type {@link LocalDate}
     */
    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

    /**
     * method-getter for {@link Order#acceptedDate} field
     * @return {@link Order#acceptedDate}
     */
    public LocalDate getAcceptedDate() {
        return acceptedDate;
    }

    /**
     * Method-setter for {@link Order#acceptedDate} filed
     * @param acceptedDate - ordered date, type {@link LocalDate}
     */
    public void setAcceptedDate(LocalDate acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    /**
     * method-getter for {@link Order#rejectedDate} field
     * @return {@link Order#rejectedDate}
     */
    public LocalDate getRejectedDate() {
        return rejectedDate;
    }

    /**
     * Method-setter for {@link Order#rejectedDate} filed
     * @param rejectedDate - rejected date, type {@link LocalDate}
     */
    public void setRejectedDate(LocalDate rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    /**
     * method-getter for {@link Order#returnedDate} field
     * @return {@link Order#returnedDate}
     */
    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    /**
     * Method-setter for {@link Order#returnedDate} filed
     * @param returnedDate - returnedDate date, type {@link LocalDate}
     */
    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    /**
     * method-getter for {@link Order#type} field
     * @return {@link Order#type}
     */
    public OrderType getType() {
        return type;
    }

    /**
     * Method-setter for {@link Order#type} filed
     * @param type - order type, type {@link OrderType}
     */
    public void setType(OrderType type) {
        this.type = type;
    }

    /**
     * method-getter for {@link Order#status} field
     * @return {@link Order#status}
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Method-setter for {@link Order#status} filed
     * @param status - order status, type {@link OrderStatus}
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * method-getter for {@link Order#user} field
     * @return {@link Order#user}
     */
    public User getUser() {
        return user;
    }

    /**
     * Method-setter for {@link Order#user} filed
     * @param user - user, type {@link User}
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * {@code getOrderBuilder} method to get {@link Order.OrderBuilder}
     * @return {@link Order.OrderBuilder}
     */
    public static OrderBuilder getOrderBuilder(){
        return new Order().new OrderBuilder();
    }

    public LocalDate getEstimatedReturnDate() {
        return estimatedReturnDate;
    }

    public void setEstimatedReturnDate(LocalDate estimatedReturnDate) {
        this.estimatedReturnDate = estimatedReturnDate;
    }

    /**
     * {@code OrderBuilder} class to build {@link Order}
     */
    public class OrderBuilder{
        /**
         * Method defines {@link AbstractEntity#id} field
         * @param id - order id, type {@link Long}
         * @return - builder object {@link Order.OrderBuilder}
         */
        public OrderBuilder withId(long id){
            Order.this.setId(id);
            return this;
        }

        /**
         * Method defines {@link Order#createdDate} field
         * @param date - created date, type {@link LocalDate}
         * @return - builder object {@link Order.OrderBuilder}
         */
        public OrderBuilder withCreateDate(LocalDate date){
            Order.this.setCreatedDate(date);
            return this;
        }

        /**
         * Method defines {@link Order#reservedDate} field
         * @param date - reserved date, type {@link LocalDate}
         * @return - builder object {@link Order.OrderBuilder}
         */
        public OrderBuilder withReservedDate(LocalDate date){
            Order.this.setReservedDate(date);
            return this;
        }

        /**
         * Method defines {@link Order#acceptedDate} field
         * @param date - ordered date, type {@link LocalDate}
         * @return - builder object {@link Order.OrderBuilder}
         */
        public OrderBuilder withOrderedDate(LocalDate date){
            Order.this.setAcceptedDate(date);
            return this;
        }

        /**
         * Method defines {@link Order#rejectedDate} field
         * @param date - rejected date, type {@link LocalDate}
         * @return - builder object {@link Order.OrderBuilder}
         */
        public OrderBuilder withRejectedDate(LocalDate date){
            Order.this.setRejectedDate(date);
            return this;
        }

        /**
         * Method defines {@link Order#returnedDate} field
         * @param date - returned date, type {@link LocalDate}
         * @return - builder object {@link Order.OrderBuilder}
         */
        public OrderBuilder withReturnedDate(LocalDate date){
            Order.this.setReturnedDate(date);
            return this;
        }

        /**
         * Method defines {@link Order#type} field
         * @param type - order type, type {@link OrderType}
         * @return - builder object {@link Order.OrderBuilder}
         */
        public OrderBuilder withOrderType(OrderType type){
            Order.this.setType(type);
            return this;
        }

        /**
         * Method defines {@link Order#status} field
         * @param status - order status, type {@link OrderStatus}
         * @return - builder object {@link Order.OrderBuilder}
         */
        public OrderBuilder withOrderStatus(OrderStatus status){
            Order.this.setStatus(status);
            return this;
        }

        /**
         * Method defines {@link Order#user} field
         * @param user - user, type {@link User}
         * @return - builder object {@link Order.OrderBuilder}
         */
        public OrderBuilder withUser(User user){
            Order.this.setUser(user);
            return this;
        }

        public OrderBuilder withEstimatedReturnDate(LocalDate date){
            Order.this.setEstimatedReturnDate(date);
            return this;
        }

        /**
         * Method build order entity
         * @return - order object {@link Order}
         */
        public Order buildOrder(){
            return Order.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (!createdDate.equals(order.createdDate)) return false;
        if (acceptedDate != null ? !acceptedDate.equals(order.acceptedDate) : order.acceptedDate != null) return false;
        if (rejectedDate != null ? !rejectedDate.equals(order.rejectedDate) : order.rejectedDate != null) return false;
        if (returnedDate != null ? !returnedDate.equals(order.returnedDate) : order.returnedDate != null) return false;
        if (type != order.type) return false;
        if (status != order.status) return false;
        return user.equals(order.user);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + createdDate.hashCode();
        result = 31 * result + (acceptedDate != null ? acceptedDate.hashCode() : 0);
        result = 31 * result + (rejectedDate != null ? rejectedDate.hashCode() : 0);
        result = 31 * result + (returnedDate != null ? returnedDate.hashCode() : 0);
        result = 31 * result + type.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(String.valueOf(this.getId()))
                .append( ",")
                .append(this.status).toString();
    }
}