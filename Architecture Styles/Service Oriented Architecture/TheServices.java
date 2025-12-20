// TheServices.java
// These are our individual services. They are simple and focused on one task.
// They implement a common Service interface so the ESB can treat them uniformly.

// A simple interface for all our services
interface Service {
    OrderMessage execute(OrderMessage message);
}

// --- The Three Services ---

class InventoryService implements Service {
    @Override
    public OrderMessage execute(OrderMessage message) {
        System.out.println("[InventoryService] Checking stock for product: " + message.getProductId());
        if ("PROD-123".equals(message.getProductId())) {
            message.setDetails("Product is in stock.");
            System.out.println("[InventoryService] SUCCESS: " + message.getDetails());
        } else {
            throw new RuntimeException("Product out of stock!"); // This will fail the whole process
        }
        return message;
    }
}

class PaymentService implements Service {
    @Override
    public OrderMessage execute(OrderMessage message) {
        System.out.println("[PaymentService] Processing payment of $" + message.getAmount());
        // Simulate a payment processing delay
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        message.setDetails("Payment of $" + message.getAmount() + " approved.");
        System.out.println("[PaymentService] SUCCESS: " + message.getDetails());
        return message;
    }
}

class ShippingService implements Service {
    @Override
    public OrderMessage execute(OrderMessage message) {
        System.out.println("[ShippingService] Creating shipment for order: " + message.getOrderId());
        message.setDetails("Shipment created with tracking number: 1Z" + message.getOrderId());
        System.out.println("[ShippingService] SUCCESS: " + message.getDetails());
        return message;
    }
}