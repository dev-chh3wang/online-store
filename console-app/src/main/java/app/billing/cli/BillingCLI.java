package app.billing.cli;

import billing.config.ConfigCLI;
import billing.store.domain.Customer;
import billing.store.domain.Order;
import billing.store.domain.OrderCart;
import billing.store.domain.Product;
import billing.store.usecase.port.Ordering;
import billing.store.usecase.usecase.FindProduct;
import billing.store.usecase.usecase.OrderingService;
import billing.store.usecase.usecase.RestockProductUseCase.RestockProductCommand;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BillingCLI {

    public static void main(String[] args) {

        ConfigCLI configCLI = new ConfigCLI();
        configCLI.reStockProducts().restock(RestockProductCommand.of(items()));
        FindProduct findProduct = configCLI.getFindProductUseCase();
        List<Product> allProducts = findProduct.getAll();
        //browse products
        Optional<Product> s1001 = findProduct.findByCode("S1001");
        Optional<Product> s1002 = findProduct.findByCode("S1002");

        allProducts.forEach(System.out::println);

        // add to cart
        OrderCart orderCart = new OrderCart();
        orderCart.add(s1001.get(),2);
        orderCart.add(s1002.get(),1);

        //checkout
        Customer jack = new Customer("Jack","Back","123Street 12","jackMail@mail.com");
        OrderingService.OrderCommand orderCommand = new Ordering.OrderCommand(jack,orderCart.getItems());
        Order order = configCLI.ordering().placeOrder(orderCommand);

        List<String> orderdItems = order.getItems().stream().map(oit -> oit.toString()).collect(Collectors.toList());
        String order_detail = new CliStringFormatter()
                .str("Order Detail : ").append(order.getCustomer().fullName())
                .newLine("Order no: ").append(order.getOrderNo())
                .newLine("Order Items: ").formatList(orderdItems)
                .newLine("Total price: ").append(order.getTotalCost().toString())
                .build();
        System.out.println(order_detail);

        String billingAndShipping = new CliStringFormatter()
                .newLine("Billing address : ").append(order.getCustomer().getBillingAddress())
                .newLine("Shipping address : ").append(order.getCustomer().getShippingAddress())
                .build();
        System.out.println(billingAndShipping);

        //checkout

    }



    private static Map<Product, Integer> items() {
        Map<Product,Integer> productQty = new HashMap<>();
        productQty.put(new Product("S1001","Shoe NewBalance", BigDecimal.valueOf(100)),12);
        productQty.put(new Product("S1002","Jacket", BigDecimal.valueOf(150)),10);
        productQty.put(new Product("S1003","T-Shirt", BigDecimal.valueOf(10)),60);
        productQty.put(new Product("S1004","Jumper", BigDecimal.valueOf(60)),12);
        return productQty;
    }

    public static class CliStringFormatter{

        StringBuilder strBuilder = new StringBuilder();

        public CliStringFormatter str(String order_detail) {
            strBuilder.append(order_detail);
            return this;
        }

        public CliStringFormatter newLine(String s) {
            strBuilder.append("\n").append(s);
            return this;
        }

        public CliStringFormatter append(String s) {
            strBuilder.append(s);
            return this;
        }

        public CliStringFormatter formatList(List<String> strings) {
            strings.forEach(str->newLine(str));
            return this;
        }

        public String build() {
            return strBuilder.toString();
        }
    }
}
