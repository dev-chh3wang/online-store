package billing.store.usecase.business.services;

import billing.store.domain.Product;
import billing.store.usecase.port.ProductRepository;
import billing.store.usecase.usecase.RestockProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestockProductServiceTest {



    RestockProductService restockProductService;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        restockProductService = new RestockProductService(productRepository);
    }

    @Test
    void shouldRestock() {

        Product product = new Product("100","Shoe", BigDecimal.valueOf(100));
        product.restock(1);
        RestockProductUseCase.RestockProductCommand cmd = new RestockProductUseCase.RestockProductCommand(product,4);

        when(productRepository.contains(product)).thenReturn(true);
        product.restock(4);
        doNothing().when(productRepository).save(product);

        restockProductService.restock(cmd);
        verify(productRepository,times(1)).save(any(Product.class));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void shouldRestockListOfProducts() {
        Product shoe = new Product("100","Shoe", BigDecimal.valueOf(100));
        shoe.restock(1); // initial stock
        Product jeans = new Product("101","Jeans", BigDecimal.valueOf(160));
        jeans.restock(10); // initial stock

        Map<Product,Integer> productstock = new HashMap<>();
        productstock.put(shoe,1);
        productstock.put(jeans,5);

        List<RestockProductUseCase.RestockProductCommand> batch = RestockProductUseCase.RestockProductCommand.of(productstock);
        when(productRepository.contains(any(Product.class))).thenReturn(true);
        doNothing().when(productRepository).save(any(Product.class));
        restockProductService.restock(batch);

        verify(productRepository,times(2)).save(any(Product.class));
        verifyNoMoreInteractions(productRepository);

    }

    @Test
    void shouldAddProductWhenNoProductExists() {
        Product product = new Product("200", "Test", BigDecimal.valueOf(200));
        RestockProductUseCase.RestockProductCommand command = mock(RestockProductUseCase.RestockProductCommand.class);
        when(command.getProduct()).thenReturn(product);
        when(command.getQty()).thenReturn(1);
        when(productRepository.contains(any(Product.class))).thenReturn(false);

        restockProductService.restock(command);
        doNothing().when(productRepository).save(any(Product.class));

        verify(productRepository,times(1)).save(any(Product.class));

        verifyNoMoreInteractions(productRepository);
    }
}