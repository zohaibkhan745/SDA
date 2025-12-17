import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryService {

    private static final Map<Integer, Integer> stock = new ConcurrentHashMap<>();

    static {
        // In-memory stock data
        stock.put(1, 50); // 50 Laptops
        stock.put(2, 200); // 200 Mice
        stock.put(3, 100); // 100 Keyboards
    }

    public static void main(String[] args) throws IOException {
        int port = 9002;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/stock/", new StockHandler());
        server.createContext("/reduce", new ReduceStockHandler());
        server.start();
        System.out.println("Inventory Service started on port " + port);
    }

    static class StockHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                String path = exchange.getRequestURI().getPath();
                String idStr = path.substring(path.lastIndexOf('/') + 1);
                try {
                    int id = Integer.parseInt(idStr);
                    Integer quantity = stock.get(id);
                    if (quantity != null) {
                        String response = quantity.toString();
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

    static class ReduceStockHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                // Read request body
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String body = br.readLine();
                String[] parts = body.split(",");
                
                try {
                    int productId = Integer.parseInt(parts[0]);
                    int quantityToReduce = Integer.parseInt(parts[1]);

                    stock.computeIfPresent(productId, (key, currentStock) -> {
                        if (currentStock >= quantityToReduce) {
                            return currentStock - quantityToReduce;
                        }
                        return currentStock; // Not enough stock, do nothing
                    });

                    String response = "Stock reduced for product " + productId;
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } catch (Exception e) {
                    String response = "Invalid request body";
                    exchange.sendResponseHeaders(400, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            }
        }
    }

    private static void sendNotFoundResponse(HttpExchange exchange) throws IOException {
        String response = "Product stock not found";
        exchange.sendResponseHeaders(404, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

// http://127.0.0.1:9002/reduce -H "Content-Type: text/plain" -d "2,3"


