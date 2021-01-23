package billing.store.usecase.business.services;

import billing.store.domain.Product;
import billing.store.usecase.port.ProductRepository;
import billing.store.usecase.usecase.RestockProductUseCase;

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
        product.restock(command.getQty());
        this.productRepository.save(product);
    }

    @Override
    public void restock(List<RestockProductCommand> commands) {
        commands.forEach(this::restock);
    }
}
