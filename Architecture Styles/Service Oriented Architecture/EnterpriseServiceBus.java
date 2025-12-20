// EnterpriseServiceBus.java
// This class simulates the Enterprise Service Bus (ESB).
// It knows about all services and orchestrates the entire workflow.

import java.util.HashMap;
import java.util.Map;

public class EnterpriseServiceBus {

    // The ESB has a registry of all available services.
    private Map<String, Service> serviceRegistry = new HashMap<>();

    // Services can register themselves with the ESB.
    public void registerService(String serviceName, Service service) {
        serviceRegistry.put(serviceName, service);
        System.out.println("[ESB] Service '" + serviceName + "' has been registered.");
    }

    // This is the core method. The ESB receives a message and orchestrates the process.
    public OrderMessage routeMessage(OrderMessage message) {
        System.out.println("\n----------------------------------------");
        System.out.println("[ESB] Received message: " + message.getStatus());
        System.out.println("----------------------------------------");

        try {
            switch (message.getStatus()) {
                case "CHECK_INVENTORY":
                    // The ESB calls the Inventory Service
                    Service inventoryService = serviceRegistry.get("InventoryService");
                    message = inventoryService.execute(message);
                    // After inventory is checked, the ESB decides the next step is payment.
                    message.setStatus("PROCESS_PAYMENT");
                    return routeMessage(message); // The ESB routes the message to the next step

                case "PROCESS_PAYMENT":
                    // The ESB calls the Payment Service
                    Service paymentService = serviceRegistry.get("PaymentService");
                    message = paymentService.execute(message);
                    // After payment, the ESB decides the next step is shipment.
                    message.setStatus("CREATE_SHIPMENT");
                    return routeMessage(message); // The ESB routes again

                case "CREATE_SHIPMENT":
                    // The ESB calls the Shipping Service
                    Service shippingService = serviceRegistry.get("ShippingService");
                    message = shippingService.execute(message);
                    // The process is now complete.
                    message.setStatus("COMPLETED");
                    return message;

                default:
                    message.setDetails("Unknown status: " + message.getStatus());
                    message.setStatus("FAILED");
                    return message;
            }
        } catch (Exception e) {
            message.setDetails("Process failed: " + e.getMessage());
            message.setStatus("FAILED");
            return message;
        }
    }
}