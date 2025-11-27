package view;

import model.Product;

public class ProductView {

    public void displayProductDetails(Product product) {
        System.out.println("========= PRODUCT DETAILS =========");
        System.out.println("Name: " + product.getName());
        System.out.println("Base Price: $" + product.getPrice());
        System.out.println("Discount: " + product.getDiscountPercentage() + "%");
        System.out.println("Price After Discount: $" + product.getDiscountedPrice());
        System.out.println("Stock Available: " + product.getStock());
        System.out.println("===================================");
        System.out.println();
    }

    public void displayMessage(String message) {
        System.out.println("[INFO] " + message);
    }

    public void displayError(String error) {
        System.out.println("[ERROR] " + error);
    }
}
