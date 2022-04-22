package by.vlad.library.entity;

import java.util.Date;
import java.util.List;

public class Order extends AbstractEntity{
    private Date startDate;
    private Date expirationDate;
    private Double sum;
    private List<Book> books;

    public Order() {
    }

    public Order(long id) {
        super(id);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public static OrderBuilder getOrderBuilder(){
        return new Order().new OrderBuilder();
    }

    public class OrderBuilder{
        public OrderBuilder withId(long id){
            Order.this.setId(id);
            return this;
        }

        public OrderBuilder withStartDate(Date date){
            Order.this.setStartDate(date);
            return this;
        }

        public Order buildOrder(){
            return Order.this;
        }
    }
}