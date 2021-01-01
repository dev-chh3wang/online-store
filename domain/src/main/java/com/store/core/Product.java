package com.store.core;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String code;
    private String name;
    private BigDecimal price;
    private int stock;
    public Product(String code, String name, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = 0;
    }

    public static Product of(String code, String name, BigDecimal price) {
        return new Product(code,name,price);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void restock(int stock){
        this.stock += stock;
    }

    public boolean inStock(){
        return this.stock > 0 ? true : false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return code.equals(product.code) &&
                name.equals(product.name) &&
                price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, price);
    }

    public int getStock() {
        return this.stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
