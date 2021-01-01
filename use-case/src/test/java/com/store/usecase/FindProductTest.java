package com.store.usecase;

import com.store.core.Product;
import com.store.port.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class FindProductTest {



    private ProductRepository productRepo;
    private FindProduct findProduct;


    @BeforeEach
    void setUp() {
        productRepo = mock(ProductRepository.class);
        findProduct = new FindProduct(productRepo);
    }

    @Test
    void shouldFindAllProducts() {
        Product shoe = new Product("P100","Timberland", BigDecimal.valueOf(200));
        Product jeans = new Product("Pj00","Denim", BigDecimal.valueOf(60));
        List<Product> items = Arrays.asList(shoe,jeans);
        when(productRepo.findAll()).thenReturn(items);
        assertThat(findProduct.getAll().size(),is(items.size()));
        verify(productRepo,times(1)).findAll();
        verifyNoMoreInteractions(productRepo);
    }

    @Test
    void shouldFindProductByName() {
        Product shoe = new Product("P100","Timberland Blue", BigDecimal.valueOf(200));
        Product shoe2 = new Product("P100","Timberland Black", BigDecimal.valueOf(200));
        List<Product> items = Arrays.asList(shoe,shoe2);
        String searchParam = "Timberland";
        when(productRepo.findByNameMatching(searchParam)).thenReturn(items);
        assertThat(findProduct.findByMatchingName(searchParam).size(),is(items.size()));
        verify(productRepo,times(1)).findByNameMatching(searchParam);
        verifyNoMoreInteractions(productRepo);
    }

    @Test
    void shouldFindProductByCode() {

        Product jeans1 = new Product("C100","Jeans Black", BigDecimal.valueOf(100));
        String searchCode = "P100";
        Optional<Product> blackJeans = Optional.of(jeans1);
        when(productRepo.findByCode(searchCode)).thenReturn(blackJeans);
        assertThat(findProduct.findByCode(searchCode).get(),is(blackJeans.get()));
        verify(productRepo,times(1)).findByCode(searchCode);
        verifyNoMoreInteractions(productRepo);
    }

    @Test
    void shouldReturnEmptyWhenNoProductFound() {
        String searchCode = "P100";
        when(productRepo.findByCode(searchCode)).thenReturn(Optional.empty());
        assertTrue(findProduct.findByCode(searchCode).isEmpty());
        verify(productRepo,times(1)).findByCode(searchCode);
        verifyNoMoreInteractions(productRepo);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenNullIsSupplied() {
        assertThrows(NullPointerException.class,()->findProduct.findByCode(null));
        verifyNoInteractions(productRepo);
    }
}