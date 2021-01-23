package billing.store.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderLine {

    private Product product;
    private int qty;

    public OrderLine(Product product, int qty) {
        Objects.requireNonNull(product,"Product must not be null");
        setQty(qty);
        this.product = product;
    }

    public void setQty(int newQty){
        if(newQty <1)
            throw new IllegalArgumentException("Quantity must be >= 1");
        this.qty = newQty;
    }

    public Product getProduct() {
        return product;
    }

    public int getQty() {
        return qty;
    }

    public BigDecimal price(){
        return product.getPrice().multiply(new BigDecimal(qty));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine that = (OrderLine) o;
        return qty == that.qty &&
                product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, qty);
    }

    @Override
    public String toString() {
        return String.format(" %s Qty = %s ",product,qty);
    }
}
