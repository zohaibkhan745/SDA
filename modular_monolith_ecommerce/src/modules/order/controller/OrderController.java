package modules.order.controller;

import modules.order.model.Order;
import modules.order.service.OrderService;
import java.util.List;
import java.util.Optional;

public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    public Order newOrder(String customerId) { return service.createOrder(customerId); }
    public void addItem(Order order, String productId, int qty, double price) { service.addItem(order, productId, qty, price); }
    public Optional<Order> getOrder(String id) { return service.findById(id); }
    public List<Order> listOrders() { return service.listAll(); }
}

