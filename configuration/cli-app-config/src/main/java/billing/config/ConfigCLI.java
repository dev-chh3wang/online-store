package billing.config;

import billing.port.impl.ProductRepositoryInmemoryAdapter;
import billing.port.impl.SaveOrderInMemoryAdapter;
import billing.port.impl.UpdateItemInventoryPortAdapter;
import billing.store.usecase.business.services.RestockProductService;
import billing.store.usecase.port.SaveOrderPort;
import billing.store.usecase.port.UpdateItemInventoryPort;
import billing.store.usecase.usecase.FindProduct;
import billing.store.usecase.usecase.OrderingService;

/**
 * Configuration class for CLI based app.
 *
 */
public class ConfigCLI {


    private final FindProduct findProductUseCase;
    private final RestockProductService restockProduct;
    private final OrderingService ordering;


    public ConfigCLI() {
        ProductRepositoryInmemoryAdapter productRepository = new ProductRepositoryInmemoryAdapter();
        this.findProductUseCase = new FindProduct(productRepository);
        this.restockProduct = new RestockProductService(productRepository);
        SaveOrderPort saveOrderPort = new SaveOrderInMemoryAdapter();
        UpdateItemInventoryPort updateInventoryPort = new UpdateItemInventoryPortAdapter();
        this.ordering  =  new OrderingService(saveOrderPort,updateInventoryPort);
    }

    public FindProduct getFindProductUseCase() {
        return findProductUseCase;
    }

    public RestockProductService reStockProducts() {
        return restockProduct;
    }


    public OrderingService ordering() {
        return ordering;
    }
}
