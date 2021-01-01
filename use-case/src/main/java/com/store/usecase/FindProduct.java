package com.store.usecase;

import com.store.core.Product;
import com.store.port.ProductRepository;

import java.util.List;
import java.util.Objects;
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
        return this.productRepository.findAll();
    }

    public Optional<Product> findByCode(String code){
        Objects.requireNonNull(code,"Search code should not be null");
        return this.productRepository.findByCode(code);
    }

    public List<Product> findByMatchingName(String searchParam) {
        return productRepository.findByNameMatching(searchParam);
    }
}
