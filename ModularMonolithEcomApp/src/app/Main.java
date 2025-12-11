package app;

import inventory.InventoryService;
import orders.OrderService;
import java.util.Scanner;

/*
 Module: App
 Main console interface demonstrating Modular Monolithic Architecture.
 Contains ONLY UI flow; business logic stays inside modules.
*/
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        InventoryService inventory = new InventoryService();
        OrderService orders = new OrderService(inventory);

        while(true){
            System.out.println("\n=== Modular Monolith Shop ===");
            System.out.println("1) View Products");
            System.out.println("2) Create Order");
            System.out.println("3) View All Orders");
            System.out.println("0) Exit");
            System.out.print("Choose: ");
            
            int ch = sc.nextInt();

            switch(ch){
                case 1:
                    System.out.println("\n📦 Available Products:");
                    inventory.getAll().forEach(System.out::println);
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.println(orders.createOrder(id, qty));
                    break;

                case 3:
                    System.out.println("\n🧾 Orders:");
                    orders.getOrders().forEach(o -> System.out.println(o.summary()));
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
