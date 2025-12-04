// Data Access Layer

public class BookingRepository {
    private Booking[] bookings;
    private int count;

    public BookingRepository() {
        bookings = new Booking[50]; // max 50 bookings
        count = 0;
    }

    public void addBooking(Booking booking) {
        if (count < bookings.length) {
            bookings[count++] = booking;
        } else {
            System.out.println("Booking limit reached!");
        }
    }

    public Booking[] getAllBookings() {
        Booking[] result = new Booking[count];
        for (int i = 0; i < count; i++) {
            result[i] = bookings[i];
        }
        return result;
    }
}
 class Booking {
    private String userName;
    private Show show;

    public Booking(String userName, Show show) {
        this.userName = userName;
        this.show = show;
    }

    public String getUserName() { return userName; }
    public Show getShow() { return show; }
}
