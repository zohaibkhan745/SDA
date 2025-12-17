import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ProductService {

    private static final Map<Integer, Product> products = new ConcurrentHashMap<>();

    static {
        // In-memory data store
        products.put(1, new Product(1, "Laptop Pro", 1200.00));
        products.put(2, new Product(2, "Wireless Mouse", 25.50));
        products.put(3, new Product(3, "Mechanical Keyboard", 75.00));
    }

    public static void main(String[] args) throws IOException {
        int port = 9001;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/products", new ProductsHandler());
        server.createContext("/product/", new ProductHandler());
        server.start();
        System.out.println("Product Service started on port " + port);
    }

    static class ProductsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = products.values().stream()
                        .map(Product->Product.toJson())
                        .collect(Collectors.joining(",\n", "[\n", "\n]\n"));
                exchange.getResponseHeaders().set("Content-Type", "json");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    static class ProductHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String path = exchange.getRequestURI().getPath();
                String idStr = path.substring(path.lastIndexOf('/') + 1);
                try {
                    int id = Integer.parseInt(idStr);
                    Product product = products.get(id);
                    if (product != null) {
                        String response = product.toJson();
                        exchange.getResponseHeaders().set("Content-Type", "application/json");
                        exchange.sendResponseHeaders(200, response.getBytes().length);
                        OutputStream os = exchange.getResponseBody();
                        os.write(response.getBytes());
                        os.close();
                    } else {
                        sendNotFoundResponse(exchange);
                    }
                } catch (NumberFormatException | IOException e) {
                    sendNotFoundResponse(exchange);
                }
            }
        }
    }
    
    private static void sendNotFoundResponse(HttpExchange exchange) throws IOException {
        String response = "Product not found";
        exchange.sendResponseHeaders(404, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    static class Product {
        int id;
        String name;
        double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String toJson() {
            return String.format("{\"id\": %d, \"name\": \"%s\", \"price\": %.2f}", id, name, price);
        }
    }
}