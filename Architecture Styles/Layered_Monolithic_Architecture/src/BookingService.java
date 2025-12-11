// Business Logic Layer

public class BookingService {
    private ShowRepository showRepo = new ShowRepository();
    private BookingRepository bookingRepo = new BookingRepository();

    public Show[] listShows() {
        return showRepo.getAllShows();
    }

    public void bookTicket(String userName, int showId) {
        Show show = showRepo.getShowById(showId);
        if (show == null) {
            System.out.println("Invalid show ID.");
            return;
        }
        if (show.getAvailableSeats() <= 0) {
            System.out.println("Sorry, no seats available for this show.");
            return;
        }
        show.bookSeat();
        bookingRepo.addBooking(new Booking(userName, show));
        System.out.println("Ticket booked successfully for " + show.getName());
    }

    public void listBookings() {
        Booking[] bookings = bookingRepo.getAllBookings();
        if (bookings.length == 0) {
            System.out.println("No bookings yet.");
        } else {
            System.out.println("Bookings:");
            for (Booking booking : bookings) {
                System.out.println("- " + booking.getUserName() + " booked " + booking.getShow().getName());
            }
        }
    }
}
