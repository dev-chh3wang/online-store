package billing.config.cli;

import billing.ProductRepositoryInmemoryAdapter;
import com.store.business.services.RestockProductService;
import com.store.usecase.FindProduct;

/**
 * Configuration class for CLI based app.
 *
 */
public class ConfigCLI {


    private final FindProduct findProductUseCase;
    private final RestockProductService restockProduct;


    public ConfigCLI() {
        ProductRepositoryInmemoryAdapter productRepository = new ProductRepositoryInmemoryAdapter();
        this.findProductUseCase = new FindProduct(productRepository);
        this.restockProduct = new RestockProductService(productRepository);
    }

    public FindProduct getFindProductUseCase() {
        return findProductUseCase;
    }

    public RestockProductService reStockProducts() {
        return restockProduct;
    }
}
