package billing.store.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BinaryOperator;


public class Order {

    private final String orderNo;
    private final List<OrderLine> orderedItems;
    private final LocalDateTime timestamp;
    private final Customer customer;

    private final BinaryOperator<BigDecimal> sumCost = BigDecimal::add;
    public Order(Customer customer,List<OrderLine> orderedItems){
        this.orderedItems = orderedItems;
        timestamp = LocalDateTime.now();
        orderNo = UUID.randomUUID().toString();
        this.customer = customer;

    }


    public String getOrderNo() {
        return orderNo;
    }

    public BigDecimal getTotalCost() {
        return orderedItems.stream()
                .map(OrderLine::price).reduce(new BigDecimal(0), sumCost);
    }

    public int noOfItems() {
        return this.orderedItems.size();
    }

    public Customer getCustomer() {
        return customer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return  Objects.equals(customer,order.customer) &&
                Objects.equals(orderedItems, order.orderedItems) &&
                Objects.equals(timestamp, order.timestamp) &&
                orderNo.equals(order.orderNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer,orderedItems, timestamp, orderNo);
    }

    public List<OrderLine> getItems() {
        return new ArrayList<>(this.orderedItems);
    }
}
