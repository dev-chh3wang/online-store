package billing.store.usecase.core;

import billing.store.domain.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ProductTest {


    @Test
    void testEquality() {
        Product product = new Product("I100", "Cake", BigDecimal.valueOf(100));
        Product product2 = new Product("I100", "Cake", BigDecimal.valueOf(100));
        assertEquals(product,product2);
    }

    @Test
    void testInEquality() {
        Product product = new Product("I1001", "Cake", BigDecimal.valueOf(100));
        Product product2 = new Product("I100", "Cake", BigDecimal.valueOf(100));
        assertNotEquals(product,product2);
    }
}