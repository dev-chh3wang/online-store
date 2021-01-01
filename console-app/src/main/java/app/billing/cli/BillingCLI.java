package app.billing.cli;

import billing.config.cli.ConfigCLI;
import com.store.core.Product;
import com.store.usecase.FindProduct;
import com.store.usecase.RestockProductUseCase.RestockProductCommand;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillingCLI {

    public static void main(String[] args) {

        ConfigCLI configCLI = new ConfigCLI();
        configCLI.reStockProducts().restock(RestockProductCommand.of(items()));
        FindProduct findProduct = configCLI.getFindProductUseCase();
        List<Product> allProducts = findProduct.getAll();
        allProducts.forEach(System.out::println);
    }



    private static Map<Product, Integer> items() {
        Map<Product,Integer> productQty = new HashMap<>();
        productQty.put(new Product("S1001","Shoe NewBalance", BigDecimal.valueOf(100)),12);
        productQty.put(new Product("S1002","Jacket", BigDecimal.valueOf(150)),10);
        productQty.put(new Product("S1003","T-Shirt", BigDecimal.valueOf(10)),60);
        productQty.put(new Product("S1004","Jumper", BigDecimal.valueOf(60)),12);
        return productQty;
    }
}
