// Data Access Layer

public class ShowRepository {
    private Show[] shows;
    private int count;

    public ShowRepository() {
        shows = new Show[10]; // max 10 shows
        shows[0] = new Show(1, "Movie A", 5);
        shows[1] = new Show(2, "Movie B", 3);
        shows[2] = new Show(3, "Movie C", 10);
        count = 3;
    }

    public Show[] getAllShows() {
        Show[] result = new Show[count];
        for (int i = 0; i < count; i++) {
            result[i] = shows[i];
        }
        return result;
    }

    public Show getShowById(int id) {
        for (int i = 0; i < count; i++) {
            if (shows[i].getId() == id) return shows[i];
        }
        return null;
    }
}


class Show {
    private int id;
    private String name;
    private int availableSeats;

    public Show(int id, String name, int availableSeats) {
        this.id = id;
        this.name = name;
        this.availableSeats = availableSeats;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAvailableSeats() { return availableSeats; }
    public void bookSeat() { availableSeats--; }
}
