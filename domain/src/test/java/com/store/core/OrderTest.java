package com.store.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderTest {


    @Test
    void testEquality() {
        Order order = new Order();
        Product shoe = new Product("cod1", "shoe", BigDecimal.valueOf(200));
        Product bag = new Product("cod2", "bag", BigDecimal.valueOf(150));
        OrderLine item1 = new OrderLine(shoe,2);
        OrderLine item2 = new OrderLine(bag,1);
        order.addItem(item1);
        order.addItem(item2);

        Order order1 = new Order();
        order1.addItem(item1);
        order1.addItem(item2);
        assertFalse(order.equals(order1));
        assertThat(order.noOfItems(),is(2));
    }


    @Test
    void createOrderWithItems() {

        Order order = new Order();
        Product shoe = new Product("cod1", "shoe", BigDecimal.valueOf(200));
        Product bag = new Product("cod2", "bag", BigDecimal.valueOf(150));
        OrderLine item1 = new OrderLine(shoe,2);
        OrderLine item2 = new OrderLine(bag,1);
        order.addItem(item1);
        order.addItem(item2);

        assertNotNull(order.getOrderNo());
        assertThat(order.getTotalCost(),is(BigDecimal.valueOf(550)));
    }

    @Test
    void shouldRemoveOrderItemFromOrder() {
        Order order = new Order();
        Product shoe = new Product("cod1", "shoe", BigDecimal.valueOf(200));
        Product bag = new Product("cod2", "bag", BigDecimal.valueOf(150));
        OrderLine item1 = new OrderLine(shoe,2);
        OrderLine item2 = new OrderLine(bag,1);
        order.addItem(item1);
        order.addItem(item2);
        assertNotNull(order.getOrderNo());
        assertThat(order.getTotalCost(),is(BigDecimal.valueOf(550)));

    }
}