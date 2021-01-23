package billing.port.impl;

import billing.store.domain.Product;
import billing.store.usecase.port.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepositoryInmemoryAdapter implements ProductRepository {

    Set<Product> productSet = new HashSet<Product>();
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productSet);
    }

    @Override
    public List<Product> findByNameMatching(String searchParam) {
        return productSet.stream().filter(p->p.getName().startsWith(searchParam)).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findByCode(String searchCode) {
        return productSet.stream().filter(p->p.getCode().equals(searchCode)).findFirst();
    }

    @Override
    public void add(Product product) {
        productSet.add(product);
    }

    @Override
    public void update(Product product) {
        productSet.stream().filter(p->p.equals(product)).forEach(p->p.restock(product.getStock()));
        productSet.add(product);
    }

    @Override
    public void addAll(List<Product> all) {
        productSet.addAll(all);
    }

    @Override
    public boolean contains(Product product) {
        return this.productSet.stream().filter(p -> p.equals(product)).count() > 0;
    }

    @Override
    public void save(Product product) {
        if (contains(product)) {
            update(product);
        } else {
            add(product);
        }
    }

}
