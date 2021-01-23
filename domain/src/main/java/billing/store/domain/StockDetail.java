package billing.store.domain;

public class StockDetail {
    private String productCode;
    private int stock;

    public StockDetail(String productCode, int stock) {
        this.productCode = productCode;
        this.stock = stock;
    }

    public String getProductCode(){
        return this.productCode;
    }

    public int getStock() {
        return stock;
    }

    public void restock(int stock) {
        this.stock +=stock;
    }
}
