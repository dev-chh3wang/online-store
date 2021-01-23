package billing.port.impl;

import billing.store.domain.OrderLine;
import billing.store.usecase.port.UpdateItemInventoryPort;

import java.util.List;

public class UpdateItemInventoryPortAdapter implements UpdateItemInventoryPort {
    @Override
    public void updateProductStock(List<OrderLine> items) {

    }
}
