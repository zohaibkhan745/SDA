import model.Product;
import view.ProductView;
import controller.ProductController;

public class Main {
    public static void main(String[] args) {

        // Create objects
        Product product = new Product("Laptop", 1200.00, 10);
        ProductView view = new ProductView();
        ProductController controller = new ProductController(product, view);

        // Display initial state
        controller.showProductDetails();

        // Update name
        controller.setProductName("Gaming Laptop");

        // Update price
        controller.setProductPrice(1500.00);

        // Apply discount
        controller.setProductDiscount(10);

        // Add stock
        controller.addStock(5);

        // Reduce stock
        controller.reduceStock(3);

        // Show final state
        controller.showProductDetails();
    }
}
