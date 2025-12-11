package orders;

import products.Product;

/*
 Module: Orders
 Represents an order item created by the user.
*/
public class Order {
    private Product product;
    private int quantity;
    private double total;

    public Order(Product p, int qty){
        this.product = p;
        this.quantity = qty;
        this.total = p.getPrice()*qty;
    }

    public String summary(){
        return product.getName() + " x " + quantity + " = Rs." + total;
    }
}
