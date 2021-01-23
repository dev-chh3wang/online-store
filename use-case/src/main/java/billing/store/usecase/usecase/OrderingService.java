package billing.store.usecase.usecase;

import billing.store.domain.Order;
import billing.store.usecase.port.SaveOrderPort;
import billing.store.usecase.port.Ordering;
import billing.store.usecase.port.UpdateItemInventoryPort;

import javax.transaction.Transactional;


public class OrderingService implements Ordering {

    private final SaveOrderPort saveOrderPort;
    private final UpdateItemInventoryPort updateInventoryPort;

    public OrderingService(SaveOrderPort saveOrderPort, UpdateItemInventoryPort updateItemInventoryPort) {
        this.saveOrderPort = saveOrderPort;
        this.updateInventoryPort = updateItemInventoryPort;

    }

    @Transactional
    public Order placeOrder(OrderCommand orderCommand) {
        Order saved = this.saveOrderPort.saveOrder(new Order(orderCommand.getCustomer(), orderCommand.getItems()));
        this.updateInventoryPort.updateProductStock(orderCommand.getItems());
        return saved;
    }


}