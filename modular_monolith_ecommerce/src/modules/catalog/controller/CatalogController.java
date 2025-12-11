package modules.catalog.controller;

import modules.catalog.model.Product;
import modules.catalog.service.CatalogService;
import java.util.List;

public class CatalogController {
    private final CatalogService service;

    public CatalogController(CatalogService service) {
        this.service = service;
    }

    public List<Product> listProducts() {
        return service.listAll();
    }

    public Product getProduct(String id) {
        return service.findById(id);
    }
}

