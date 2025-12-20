
// MainApplication.java
// This is the client application that kicks off the process.
// It only knows about the ESB, not the individual services.

public class MainApplication {
    public static void main(String[] args) {
        // 1. Setup the ESB
        EnterpriseServiceBus esb = new EnterpriseServiceBus();

        // 2. Register our services with the ESB. In a real SOA world, this
        // would be a configuration process.
        esb.registerService("InventoryService", new InventoryService());
        esb.registerService("PaymentService", new PaymentService());
        esb.registerService("ShippingService", new ShippingService());

        // 3. Create a new order message. This is our standardized data packet.
        OrderMessage order = new OrderMessage("ORD-9876", "PROD-123", 99.99);

        // 4. Send the initial message to the ESB and let it handle everything.
        // The client is "dumb" and the ESB is "smart".
        System.out.println("Client: Sending new order to the ESB. My job is done.");
        OrderMessage finalResult = esb.routeMessage(order);

        // 5. Print the final result.
        System.out.println("\n========================================");
        System.out.println("FINAL RESULT: " + finalResult);
        System.out.println("========================================");
    }
}