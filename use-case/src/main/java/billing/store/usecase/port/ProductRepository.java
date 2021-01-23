package billing.store.usecase.port;

import billing.store.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    List<Product> findByNameMatching(String searchParam);

    Optional<Product> findByCode(String searchCode);

    void add(Product product);

    void update(Product product);

    void addAll(List<Product> all);

    boolean contains(Product product);

    void save(Product product);
}
