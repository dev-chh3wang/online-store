package com.store.business.services;

import com.store.core.Product;
import com.store.port.ProductRepository;
import com.store.usecase.RestockProductUseCase;

import java.util.List;

/**
 * Low level Implementation detail for use case {@link RestockProductUseCase}
 *
 */
public class RestockProductService implements RestockProductUseCase{


    private final ProductRepository productRepository;
    public RestockProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void restock(RestockProductCommand command) {
        Product product = command.getProduct();
        if(this.productRepository.contains(product)){
            product.restock(command.getQty());
        }else {
            product.setStock(command.getQty());
        }
        this.productRepository.save(product);

    }

    @Override
    public void restock(List<RestockProductCommand> commands) {
        commands.forEach(this::restock);
    }
}
