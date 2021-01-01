package com.store.port;

import com.store.core.Product;
import com.store.core.ProductStock;

import java.util.List;

public interface ProductInventory {
    List<ProductStock> allProductStocks();

    void add(ProductStock productStock);

    void restock(Product product, int qty);
}
