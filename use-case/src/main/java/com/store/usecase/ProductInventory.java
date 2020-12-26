package com.store.usecase;

import com.store.core.Product;
import com.store.usecase.plugins.ProductRepository;

public class ProductInventory {
    private final ProductRepository repository;

    public ProductInventory(ProductRepository repository) {
        this.repository = repository;
    }


    public void restock(Product product, int stock){
        // update stock of existing product
    }

    public void update(Product product, int stock){
        //after product is sold, update the stock
    }

    public int checkStock(Product product){
        return 0;
    }

    public void add(Product product, int stock){
        //add new product with initial stock
    }

}
