package billing.port.impl;

import billing.store.domain.Order;
import billing.store.usecase.port.SaveOrderPort;

import java.util.HashSet;
import java.util.Set;

public class SaveOrderInMemoryAdapter implements SaveOrderPort {

    Set<Order> orderSet = new HashSet();

    @Override
    public Order saveOrder(Order order) {
        orderSet.add(order);
        return order;
    }
}
