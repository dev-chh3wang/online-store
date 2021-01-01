package com.store.usecase;

import com.store.core.Order;
import com.store.core.OrderLine;

import java.util.List;

public class PlaceOrder {



    public void process(List<OrderLine> orderLines) {
        Order order = new Order(null,orderLines);
        System.out.println(order.getOrderNo()+" Order created");
        System.out.println("Total Amount" +order.getTotalCost());
    }

    //1. search Item
    //2. add to cart
    // 3. checkOut
    // 4.  process order
            // 4.1 get customer billing address & shipping address
            // 4.2 get cutomer payment detail
            // 4.3 done



}
