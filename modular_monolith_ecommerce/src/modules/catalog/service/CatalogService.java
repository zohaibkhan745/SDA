package modules.catalog.service;

import modules.catalog.model.Product;
import java.util.*;

public class CatalogService {
    private final Map<String, Product> products = new HashMap<>();

    public CatalogService() {
        // seed sample products
        addProduct(new Product("P001","Wireless Mouse",25.99,10));
        addProduct(new Product("P002","Mechanical Keyboard",79.99,5));
        addProduct(new Product("P003","27\" Monitor",199.99,2));
        addProduct(new Product("P004","USB-C Cable",9.99,50));
    }

    public List<Product> listAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(String id) {
        return products.get(id);
    }

    public void addProduct(Product p) {
        products.put(p.getId(), p);
    }

    public boolean reduceStock(String productId, int qty) {
        Product p = products.get(productId);
        if (p == null) return false;
        if (p.getStock() < qty) return false;
        p.setStock(p.getStock() - qty);
        return true;
    }
}

