package model;

public class Product {
    private String name;
    private double price;
    private int stock;
    private double discountPercentage; // 0–100 %

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.discountPercentage = 0.0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setDiscountPercentage(double discountPercentage) {
        if (discountPercentage < 0) discountPercentage = 0;
        if (discountPercentage > 100) discountPercentage = 100;
        this.discountPercentage = discountPercentage;
    }

    // Computed price after discount
    public double getDiscountedPrice() {
        return price - (price * (discountPercentage / 100.0));
    }
}


