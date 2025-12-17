import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderService {

    private static final List<Order> orders = new CopyOnWriteArrayList<>();
    private static final AtomicInteger orderIdCounter = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        int port = 9003;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/create", new CreateOrderHandler());
        server.createContext("/orders", new ViewOrdersHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Order Service started on port " + port);
    }

    static class CreateOrderHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String body = br.readLine();
                String[] parts = body.split(",");
                
                try {
                    int productId = Integer.parseInt(parts[0]);
                    int quantity = Integer.parseInt(parts[1]);

                    // 1. Call Product Service to validate product exists
                    String productJson = callService("http://localhost:9001/product/" + productId);
                    if (productJson == null) {
                        sendResponse(exchange, 400, "Error: Product with ID " + productId + " not found.");
                        return;
                    }

                    // 2. Call Inventory Service to check stock
                    String stockStr = callService("http://localhost:9002/stock/" + productId);
                    if (stockStr == null) {
                        sendResponse(exchange, 500, "Error: Could not check stock for product " + productId + ". Inventory service may be down.");
                        return;
                    }

                    int availableStock = Integer.parseInt(stockStr);
                    if (availableStock < quantity) {
                        sendResponse(exchange, 400, "Error: Not enough stock for product " + productId + ". Available: " + availableStock);
                        return;
                    }

                    // 3. If all is good, create the order
                    int newOrderId = orderIdCounter.incrementAndGet();
                    Order newOrder = new Order(newOrderId, productId, quantity, "SUCCESS");
                    orders.add(newOrder);

                    // 4. Call Inventory Service to reduce stock
                    callReduceStockService(productId, quantity);

                    String response = "Order created successfully!\n" + newOrder.toJson();
                    sendResponse(exchange, 200, response);

                } catch (Exception e) {
                    sendResponse(exchange, 400, "Invalid request format. Expected 'productId,quantity'.");
                }
            }
        }
    }

    static class ViewOrdersHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String response = orders.stream()
                        .map(Order::toJson)
                        .collect(java.util.stream.Collectors.joining(",\n", "[\n", "\n]"));
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    private static String callService(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                return in.readLine();
            } else {
                return null; // Product or stock not found
            }
        } catch (IOException e) {
            // Service is down or connection failed
            System.err.println("Error calling service at " + urlString + ": " + e.getMessage());
            return null;
        }
    }

    private static void callReduceStockService(int productId, int quantity) {
        try {
            URL url = new URL("http://localhost:9002/reduce");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            String postData = productId + "," + quantity;
            conn.getOutputStream().write(postData.getBytes());
            conn.getResponseCode(); // Just to trigger the request
        } catch (IOException e) {
            System.err.println("Error calling reduce stock service: " + e.getMessage());
        }
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    static class Order {
        int orderId;
        int productId;
        int quantity;
        String status;

        public Order(int orderId, int productId, int quantity, String status) {
            this.orderId = orderId;
            this.productId = productId;
            this.quantity = quantity;
            this.status = status;
        }

        public String toJson() {
            return String.format("{\"orderId\": %d, \"productId\": %d, \"quantity\": %d, \"status\": \"%s\"}", orderId, productId, quantity, status);
        }
    }
}