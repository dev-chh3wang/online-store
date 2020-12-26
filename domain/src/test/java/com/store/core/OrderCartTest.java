package com.store.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class OrderCartTest {


    @Test
    void shouldAddOrderLineItems() {
        OrderCart orderCart = new OrderCart();
        Product shoe = Product.of("C1","JK", BigDecimal.valueOf(300));
        Product bag = Product.of("C2","MI",BigDecimal.valueOf(100));
        orderCart.add(shoe,2);
        orderCart.add(bag,1);
        assertThat(orderCart.getItems().size(),is(2));
        assertThat(orderCart.totalPrice(),is(BigDecimal.valueOf(700)));
        assertThat(orderCart.totalPrice(),is(BigDecimal.valueOf(700)));

    }

    @Test
    void shouldUpdateQtyWhenSameItemsAddedAgain() {
        OrderCart orderCart = new OrderCart();
        Product shoe = Product.of("C1","JK", BigDecimal.valueOf(300));
        Product bag = Product.of("C2","MI",BigDecimal.valueOf(100));
        orderCart.add(shoe,1);
        orderCart.add(bag,1);

        assertThat(orderCart.getItems().size(),is(2));
        assertThat(orderCart.totalPrice(),is(BigDecimal.valueOf(400)));

        orderCart.add(shoe,1);
        assertThat(orderCart.getItems().size(),is(2));
        assertThat(orderCart.totalPrice(),is(BigDecimal.valueOf(700)));
    }

    @Test
    void shouldUPdateOrderCart() {
        OrderCart orderCart = new OrderCart();
        Product shoe = Product.of("C1","JK", BigDecimal.valueOf(300));
        Product bag = Product.of("C2","MI",BigDecimal.valueOf(100));
        orderCart.add(shoe,1);
        orderCart.add(bag,1);
        assertThat(orderCart.getItems().size(),is(2));
        assertThat(orderCart.totalPrice(),is(BigDecimal.valueOf(400)));

        orderCart.update(shoe,1);
        assertThat(orderCart.getItems().size(),is(2));
        assertThat(orderCart.totalPrice(),is(BigDecimal.valueOf(400)));

        orderCart.update(bag,3);
        assertThat(orderCart.getItems().size(),is(2));
        assertThat(orderCart.totalPrice(),is(BigDecimal.valueOf(600)));
    }

    @Test
    void shouldDoNothingWhenTryingToUpdateNonExistingProduct() {
        OrderCart orderCart = new OrderCart();
        Product shoe = Product.of("C1","JK", BigDecimal.valueOf(300));
        Product bag = Product.of("C2","MI",BigDecimal.valueOf(100));

        orderCart.add(shoe,1);
        orderCart.add(bag,1);
        assertThat(orderCart.getItems().size(),is(2));
        assertThat(orderCart.totalPrice(),is(BigDecimal.valueOf(400)));
        Product nonAdded = Product.of("C20","MI",BigDecimal.valueOf(100));

        orderCart.update(nonAdded,4);
    }

    @Test
    void shouldRemoveItemFromCart() {
        OrderCart orderCart = new OrderCart();
        Product shoe = Product.of("C1","JK", BigDecimal.valueOf(300));
        Product bag = Product.of("C2","MI",BigDecimal.valueOf(100));
        orderCart.add(shoe,1);
        orderCart.add(bag,1);
        assertThat(orderCart.getItems().size(),is(2));
        assertThat(orderCart.totalPrice(),is(BigDecimal.valueOf(400)));

        orderCart.remove(shoe);
        assertThat(orderCart.getItems().size(),is(1));
        assertThat(orderCart.totalPrice(),is(BigDecimal.valueOf(100)));

    }
}