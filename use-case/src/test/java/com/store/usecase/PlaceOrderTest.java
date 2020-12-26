package com.store.usecase;

import com.store.core.OrderLine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PlaceOrderTest {


    @Test
    void testOrder() {
        PlaceOrder order = new PlaceOrder();
        List<OrderLine> orderLIneItems = new ArrayList<>();
        order.process(orderLIneItems);
    }


    @Test
    void shouldSearchProduct() {

    }
}