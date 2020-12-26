package com.store.usecase.plugins;

import com.store.core.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
}
