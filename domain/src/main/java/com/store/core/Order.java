package com.store.core;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BinaryOperator;


public class Order {

    private String orderNo;
    private List<OrderLine> orderedItems;
    private BigDecimal totalCost;
    private LocalDateTime timestamp;
    private Customer customer;
    public Order() {
        this(new ArrayList<>());
    }

    public Order(List<OrderLine> orderedItems) {
        this.orderedItems = orderedItems;
        totalCost = BigDecimal.ZERO;
        timestamp = LocalDateTime.now();
        orderNo = UUID.randomUUID().toString();
    }

    public Order(Customer customer,List<OrderLine> orderedItems){
        this(orderedItems);
        this.customer = customer;

    }


    private void updateTotal() {
        BinaryOperator<BigDecimal> reducedSum = (subtotal, element) -> subtotal.add(element);
        totalCost = orderedItems.stream()
                .map(d -> d.price()).reduce(new BigDecimal(0), reducedSum);
    }


    public String getOrderNo() {
        return orderNo;
    }

    public BigDecimal getTotalCost() {
        updateTotal();
        return totalCost;
    }

    public int noOfItems() {
        return this.orderedItems.size();
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
}
