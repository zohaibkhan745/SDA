
// Presentation Layer
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingService bookingService = new BookingService();
        int choice;

        do {
            System.out.println("\n1. List Shows");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Bookings");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Available Shows:");
                    for (Show s : bookingService.listShows()) {
                        System.out.println(s.getId() + ". " + s.getName() + " (Seats: " + s.getAvailableSeats() + ")");
                    }
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter show ID to book: ");
                    int showId = scanner.nextInt();
                    bookingService.bookTicket(userName, showId);
                    break;
                case 3:
                    bookingService.listBookings();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
