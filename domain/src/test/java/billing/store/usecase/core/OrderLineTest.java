package billing.store.usecase.core;

import billing.store.domain.OrderLine;
import billing.store.domain.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class OrderLineTest {


    @Test
    void testEquality() {
        Product shoe = Product.of("P100","Timberland", BigDecimal.valueOf(100));
        OrderLine orderLineItem = new OrderLine(shoe,2);
        OrderLine orderLineItem2 = new OrderLine(shoe,2);
        assertTrue(orderLineItem.equals(orderLineItem2));

    }

    @Test
    void testPrice() {
        Product shoe = Product.of("P100","Timberland", BigDecimal.valueOf(100));
        OrderLine orderLineItem = new OrderLine(shoe,2);
        assertTrue(orderLineItem.getProduct().equals(shoe));
        assertThat(orderLineItem.getQty(),is(2));
        assertThat(orderLineItem.price(),is(new BigDecimal(200)));
        assertThat(orderLineItem.price(),is(new BigDecimal(200)));
    }

    @Test
    void shouldThrowExceptionWHenTryingToAddNullProduct() {
        assertThrows(NullPointerException.class,() ->new OrderLine(null, 2));
    }

    @Test
    void shouldThrowExceptionWhenSettingZeroQty() {
        Product shoe = Product.of("P100","Timberland", BigDecimal.valueOf(100));
        assertThrows(IllegalArgumentException.class,() ->new OrderLine(shoe, 0));
    }
}