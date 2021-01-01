package com.store.core;

public class ProductStock {
    private Product product;
    private int stock;

    public ProductStock(Product product, int stock) {
        this.product = product;
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public int getStock() {
        return stock;
    }

    public void restock(int stock) {
        this.stock +=stock;
    }
}
