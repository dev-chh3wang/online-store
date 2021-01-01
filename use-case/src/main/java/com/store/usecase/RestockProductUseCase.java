package com.store.usecase;

import com.store.core.Product;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface RestockProductUseCase {

    void restock(RestockProductCommand command);
    void restock(List<RestockProductCommand> commands);

    class RestockProductCommand {

        private final Product product;
        private final int qty;
        public RestockProductCommand(Product product, int quantity) {
            this.product = product;
            this.qty = quantity;
        }
        public static List<RestockProductCommand> of(Map<Product,Integer> productQty){
            return productQty.entrySet().stream()
                    .map(entry->new RestockProductCommand(entry.getKey(),entry.getValue()))
                    .collect(Collectors.toList());
        }

        public Product getProduct() {
            return product;
        }

        public int getQty() {
            return qty;
        }
    }



}
