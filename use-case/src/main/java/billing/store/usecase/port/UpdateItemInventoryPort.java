package billing.store.usecase.port;

import billing.store.domain.OrderLine;

import java.util.List;

public interface UpdateItemInventoryPort {
    void updateProductStock(List<OrderLine> items);
}
