package modules.payment.service;

import modules.order.model.Order;

public class PaymentService {
    public boolean processPayment(Order order, String paymentMethod) {
        // very simple simulation
        if (order == null || order.getItems().isEmpty()) return false;
        // pretend payment always succeeds
        order.setPaid(true);
        return true;
    }
}

