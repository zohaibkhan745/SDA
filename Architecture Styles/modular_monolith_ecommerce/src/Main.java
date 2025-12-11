import modules.catalog.service.CatalogService;
import modules.catalog.controller.CatalogController;
import modules.customer.service.CustomerService;
import modules.customer.controller.CustomerController;
import modules.order.service.OrderService;
import modules.order.controller.OrderController;
import modules.payment.service.PaymentService;
import ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        // Create services (modules)
        CatalogService catalogService = new CatalogService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();

        // Create controllers (module API for UI)
        CatalogController catalogController = new CatalogController(catalogService);
        CustomerController customerController = new CustomerController(customerService);
        OrderController orderController = new OrderController(orderService);

        // Create UI (entry point)
        ConsoleUI ui = new ConsoleUI(catalogController, customerController, orderController);
        ui.start();
    }
}

