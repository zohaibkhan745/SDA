package ui;

import modules.catalog.controller.CatalogController;
import modules.customer.controller.CustomerController;
import modules.order.controller.OrderController;
import modules.order.model.Order;
import modules.catalog.model.Product;
import modules.customer.model.Customer;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final CatalogController catalog;
    private final CustomerController customer;
    private final OrderController order;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(CatalogController catalog, CustomerController customer, OrderController order) {
        this.catalog = catalog;
        this.customer = customer;
        this.order = order;
    }

    public void start() {
        boolean running = true;
        while (running) {
            showMenu();
            System.out.println("Please chose : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1 -> listProducts();
                case 2 -> listCustomers();
                case 3 -> createOrder();
                case 4 -> listOrders();
                case 5 -> running = false;
                default -> System.out.println("Invalid");
            }
        }
        System.out.println("Bye, Thanks for shopping with us");
    }

    private void showMenu() {
        System.out.println("\n=== Modular Monolith Store ===");
        System.out.println("1. View list of products");
        System.out.println("2. View list of customers");
        System.out.println("3. Place order "); 
        System.out.println("4. View list of orders");
        System.out.println("5. Exit");
    }

    private void listProducts() {
        List<Product> ps = catalog.listProducts();
        for(Product p : ps) {
        System.out.println(p);
         }
    
    }

    private void listCustomers() {
        List<Customer> cs = customer.listCustomers();
        for(Customer c : cs) {
    System.out.println(c);
}

    }

private void createOrder() {
    System.out.print("Enter customer id: ");
    String cid = scanner.nextLine();
    Customer c = customer.getCustomer(cid);

    if (c == null) {
        System.out.println("Customer not found");
        return;
    }

    List<Product> products = catalog.listProducts();
    if (products.isEmpty()) {
        System.out.println("No products available.");
        return;
    }

    System.out.println("\nAvailable Products:");
    for (Product p : products) {
        System.out.println(p.getId() + " - " + p.getName() + " (Stock: " + p.getStock() + ")");
    }

    // Product ID must be STRING
    System.out.print("Enter Product ID: ");
    String pid = scanner.nextLine().trim();

    Order o = order.newOrder(cid);

    Product selected = catalog.getProduct(pid);
    if (selected == null) {
        System.out.println("Product not found!");
        return;
    }

    System.out.print("Enter quantity: ");
    int qty = Integer.parseInt(scanner.nextLine().trim());

    if (selected.getStock() < qty) {
        System.out.println("Not enough stock! Available: " + selected.getStock());
        return;
    }

    // Reduce stock – adjust according to your actual Product class
    selected.setStock(selected.getStock() - qty);

    // Add item to order
    order.addItem(o, selected.getId(), qty, selected.getPrice());

    
    System.out.println("Created order: " + o.getId());

    System.out.println("Added: " + selected.getName() + " x " + qty);
    System.out.println("Remaining stock: " + selected.getStock());
    System.out.println("Order total: " + o.getTotal());
}


    private void listOrders() {
        for (Order o : order.listOrders()) {
            System.out.println(o);
        }
    }
}

