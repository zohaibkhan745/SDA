
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static final String SERVER_HOST = "127.0.0.1"; 
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {

        
        try (
           
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
           
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
           
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected to the server. Enter your message (or type 'exit' to quit):");

            String userInput;
         
            while (true) {
                System.out.print("> ");
                userInput = scanner.nextLine();

                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }

                
                out.println(userInput);

                
                String serverResponse = in.readLine();
                System.out.println("Server says: " + serverResponse);
            }
        } catch (UnknownHostException e) {
            System.err.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
        }
    }
}