package by.vlad.library.entity;

import java.time.LocalDate;

public class Order extends AbstractEntity{
    private LocalDate createdDate;
    private LocalDate reservedDate;
    private LocalDate orderedDate;
    private LocalDate rejectedDate;
    private LocalDate returnedDate;
    private OrderType type;
    private OrderStatus status;
    private User user;

    public Order() {
    }

    public Order(long id) {
        super(id);
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(LocalDate orderedDate) {
        this.orderedDate = orderedDate;
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


    public static OrderBuilder getOrderBuilder(){
        return new Order().new OrderBuilder();
    }

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

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
            Order.this.setOrderedDate(date);
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
        if (orderedDate != null ? !orderedDate.equals(order.orderedDate) : order.orderedDate != null) return false;
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
        result = 31 * result + (orderedDate != null ? orderedDate.hashCode() : 0);
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