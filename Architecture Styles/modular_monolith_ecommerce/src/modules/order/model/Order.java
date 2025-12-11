package modules.order.model;

import java.util.*;

public class Order {
    private final String id;
    private final String customerId;
    private final List<OrderItem> items = new ArrayList<>();
    private boolean paid;

    public Order(String id, String customerId) {
        this.id = id;
        this.customerId = customerId;
        this.paid = false;
    }

    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public List<OrderItem> getItems() { return items; }
    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }
    public void addItem(OrderItem item) { items.add(item); }
    public double getTotal() {
        return items.stream().mapToDouble(OrderItem::getTotal).sum();
    }
    @Override
    public String toString() {
        return "Order " + id + " customer=" + customerId + " items=" + items + " total=" + getTotal() + " paid=" + paid;
    }
}

