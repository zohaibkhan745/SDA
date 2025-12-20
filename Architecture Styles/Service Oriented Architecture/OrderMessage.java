// OrderMessage.java
// This is our standardized message format that all services will use.
// This demonstrates the "Standardization" principle of SOA.

public class OrderMessage {
    private String orderId;
    private String productId;
    private double amount;
    private String status; // e.g., "CHECK_INVENTORY", "PROCESS_PAYMENT", "COMPLETED", "FAILED"
    private String details;

    public OrderMessage(String orderId, String productId, double amount) {
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
        this.status = "CHECK_INVENTORY"; // The first step in our process
        this.details = "Order created.";
    }

    // Getters and Setters...
    public String getOrderId() { return orderId; }
    public String getProductId() { return productId; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    public String getDetails() { return details; }
    public void setStatus(String status) { this.status = status; }
    public void setDetails(String details) { this.details = details; }

    @Override
    public String toString() {
        return "OrderMessage{" +
               "orderId='" + orderId + '\'' +
               ", productId='" + productId + '\'' +
               ", amount=" + amount +
               ", status='" + status + '\'' +
               ", details='" + details + '\'' +
               '}';
    }
}
