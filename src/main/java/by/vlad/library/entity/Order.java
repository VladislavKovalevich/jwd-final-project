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
     * {@code getOrderBuilder} method to get {@link Order.OrderBuilder}
     * @return {@link Order.OrderBuilder}
     */
    public static OrderBuilder getOrderBuilder(){
        return new Order().new OrderBuilder();
    }


    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

    public LocalDate getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(LocalDate acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public LocalDate getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(LocalDate rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

        public OrderBuilder withId(long id){
            Order.this.setId(id);
            return this;
        }

        public OrderBuilder withCreateDate(LocalDate date){
            Order.this.setCreatedDate(date);
            return this;
        }

        public OrderBuilder withReservedDate(LocalDate date){
            Order.this.setReservedDate(date);
            return this;
        }

        public OrderBuilder withOrderedDate(LocalDate date){
            Order.this.setAcceptedDate(date);
            return this;
        }

        public OrderBuilder withRejectedDate(LocalDate date){
            Order.this.setRejectedDate(date);
            return this;
        }

        public OrderBuilder withReturnedDate(LocalDate date){
            Order.this.setReturnedDate(date);
            return this;
        }

        public OrderBuilder withOrderType(OrderType type){
            Order.this.setType(type);
            return this;
        }

        public OrderBuilder withOrderStatus(OrderStatus status){
            Order.this.setStatus(status);
            return this;
        }

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
        if (reservedDate != null ? !reservedDate.equals(order.reservedDate) : order.reservedDate != null) return false;
        if (acceptedDate != null ? !acceptedDate.equals(order.acceptedDate) : order.acceptedDate != null) return false;
        if (rejectedDate != null ? !rejectedDate.equals(order.rejectedDate) : order.rejectedDate != null) return false;
        if (returnedDate != null ? !returnedDate.equals(order.returnedDate) : order.returnedDate != null) return false;
        if (estimatedReturnDate != null ? !estimatedReturnDate.equals(order.estimatedReturnDate) : order.estimatedReturnDate != null)
            return false;
        if (type != order.type) return false;
        if (status != order.status) return false;
        return user.equals(order.user);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + createdDate.hashCode();
        result = 31 * result + (reservedDate != null ? reservedDate.hashCode() : 0);
        result = 31 * result + (acceptedDate != null ? acceptedDate.hashCode() : 0);
        result = 31 * result + (rejectedDate != null ? rejectedDate.hashCode() : 0);
        result = 31 * result + (returnedDate != null ? returnedDate.hashCode() : 0);
        result = 31 * result + (estimatedReturnDate != null ? estimatedReturnDate.hashCode() : 0);
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