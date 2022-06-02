package by.vlad.library.entity;

import java.time.LocalDate;

public class Order extends AbstractEntity{
    private LocalDate createDate;
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
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

    public class OrderBuilder{
        public OrderBuilder withId(long id){
            Order.this.setId(id);
            return this;
        }

        public OrderBuilder withCreateDate(LocalDate date){
            Order.this.setCreateDate(date);
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
}