package controller;

import model.Product;
import view.ProductView;

public class ProductController {

    private Product model;
    private ProductView view;

    public ProductController(Product model, ProductView view) {
        this.model = model;
        this.view = view;
    }

    // Update fields
    public void setProductName(String name) {
        if (name == null || name.isBlank()) {
            view.displayError("Product name cannot be empty.");
        } else {
            model.setName(name);
            view.displayMessage("Name updated to: " + name);
        }
    }

    public void setProductPrice(double price) {
        if (price <= 0) {
            view.displayError("Price must be positive.");
        } else {
            model.setPrice(price);
            view.displayMessage("Price updated to: $" + price);
        }
    }

    public void setProductDiscount(double discount) {
        if (discount < 0 || discount > 100) {
            view.displayError("Discount must be between 0 and 100.");
        } else {
            model.setDiscountPercentage(discount);
            view.displayMessage("Discount set to " + discount + "%");
        }
    }

    // Stock operations
    public void addStock(int amount) {
        if (amount <= 0) {
            view.displayError("Stock amount must be positive.");
            return;
        }
        model.setStock(model.getStock() + amount);
        view.displayMessage(amount + " units added to stock.");
    }

    public void reduceStock(int amount) {
        if (amount <= 0) {
            view.displayError("Amount must be positive.");
            return;
        }
        if (amount > model.getStock()) {
            view.displayError("Not enough stock available.");
            return;
        }
        model.setStock(model.getStock() - amount);
        view.displayMessage(amount + " units sold/removed from stock.");
    }

    // Display info
    public void showProductDetails() {
        view.displayProductDetails(model);
    }
}


