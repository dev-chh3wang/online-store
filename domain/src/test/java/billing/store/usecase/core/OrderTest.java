package billing.store.usecase.core;

import billing.store.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderTest {

    OrderCart orderCart;
    @BeforeEach
    void setUp() {
        orderCart = mock(OrderCart.class);
    }

    @Test
    void ordersWithSameItemsAreNotSameOrder() {
        Product shoe = new Product("cod1", "shoe", BigDecimal.valueOf(200));
        Product bag = new Product("cod2", "bag", BigDecimal.valueOf(150));
        OrderLine item1 = new OrderLine(shoe,2);
        OrderLine item2 = new OrderLine(bag,1);

        List<OrderLine> lineItems = Arrays.asList(item1, item2);
        when(orderCart.getItems()).thenReturn(lineItems);
        Customer customer = new Customer("Jack", "doe", "Street 123", "test@email.com");
        Order order = new Order(customer,orderCart.getItems());
        Order order1 = new Order(customer,orderCart.getItems());

        assertFalse(order.equals(order1));
        assertThat(order.noOfItems(),is(2));
    }


    @Test
    void createOrderWithItems() {

        Product shoe = new Product("cod1", "shoe", BigDecimal.valueOf(200));
        Product bag = new Product("cod2", "bag", BigDecimal.valueOf(150));
        OrderLine item1 = new OrderLine(shoe,2);
        OrderLine item2 = new OrderLine(bag,1);
        List<OrderLine> lineItems = Arrays.asList(item1, item2);
        when(orderCart.getItems()).thenReturn(lineItems);
        Customer customer = new Customer("Jack", "doe", "Street 123", "test@email.com");
        Order order = new Order(customer,orderCart.getItems());
        assertNotNull(order.getOrderNo());
        assertThat(order.getTotalCost(),is(BigDecimal.valueOf(550)));
    }

    @Test
    void emptyOrderTotalCost() {
        Customer customer = new Customer("Jack", "doe", "Street 123", "test@email.com");
        Order order = new Order(customer, new ArrayList<>());
        assertThat(order.getTotalCost(),is(BigDecimal.ZERO));
    }
}