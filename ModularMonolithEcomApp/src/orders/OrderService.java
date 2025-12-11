package orders;

import inventory.InventoryService;
import products.Product;
import java.util.ArrayList;
import java.util.List;

/*
 Module: Orders
 Handles business logic for creating orders and maintaining order history.
*/
public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private InventoryService inventory;

    public OrderService(InventoryService inv){
        this.inventory = inv;
    }

    public String createOrder(int productId, int qty){
        Product p = inventory.getById(productId);
        if(p == null) return "❌ Product not found!";
        if(p.getStock() < qty) return "❌ Not enough stock!";

        inventory.reduceStock(productId, qty);
        Order o = new Order(p, qty);
        orders.add(o);
        return "✅ Order created: " + o.summary();
    }

    public List<Order> getOrders(){ return orders; }
}
