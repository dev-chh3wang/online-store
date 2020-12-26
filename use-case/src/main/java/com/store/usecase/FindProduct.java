package com.store.usecase;

import com.store.core.Product;
import com.store.usecase.plugins.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindProduct {


    private final ProductRepository productRepository;

    public FindProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * List all stored products
     * @return
     */
    public List<Product> getAll(){
        return new ArrayList<>();
    }

    public List<Product> findBy(String param){
        return new ArrayList<>();
    }

    public Optional<Product> findByCode(String code){
        return Optional.empty();
    }
}
