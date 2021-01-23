package billing.store.usecase.port;

import billing.store.domain.Order;

public interface SaveOrderPort {
    Order saveOrder(Order order);
}
