package billing.store.usecase.port;

import billing.store.domain.Order;
import billing.store.domain.OrderLine;
import billing.store.domain.Customer;

import java.util.List;
import java.util.Objects;

public interface Ordering {

    Order placeOrder(OrderCommand cmd);

    class OrderCommand {
        private final Customer customer;
        private final List<OrderLine> items;

        public OrderCommand(Customer customer, List<OrderLine> items) {
            Objects.requireNonNull(customer,"Customer must not be null");
            Objects.requireNonNull(items,"Items must not be null");
            this.customer = customer;
            this.items = items;
        }

        public Customer getCustomer() {
            return customer;
        }

        public List<OrderLine> getItems() {
            return items;
        }
    }
}
