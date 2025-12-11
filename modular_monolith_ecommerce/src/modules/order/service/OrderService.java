package modules.order.service;

import modules.order.model.Order;
import modules.order.model.OrderItem;
import java.util.*;

public class OrderService {
    private final Map<String, Order> orders = new LinkedHashMap<>();
    private static int seq = 1;

    public Order createOrder(String customerId) {
        String id = String.format("O%03d", seq++);
        Order o = new Order(id, customerId);
        orders.put(id, o);
        return o;
    }

    public Optional<Order> findById(String id) { return Optional.ofNullable(orders.get(id)); }

    public void addItem(Order order, String productId, int qty, double pricePerUnit) {
        order.addItem(new OrderItem(productId, qty, pricePerUnit));
    }

    public List<Order> listAll() { return new ArrayList<>(orders.values()); }
}

