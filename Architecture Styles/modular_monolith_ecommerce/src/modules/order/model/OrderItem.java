package modules.order.model;

public class OrderItem {
    private final String productId;
    private final int quantity;
    private final double pricePerUnit;

    public OrderItem(String productId, int quantity, double pricePerUnit) {
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPricePerUnit() { return pricePerUnit; }
    public double getTotal() { return pricePerUnit * quantity; }
    public String toString() { return productId + " x" + quantity + " @" + pricePerUnit; }
}

