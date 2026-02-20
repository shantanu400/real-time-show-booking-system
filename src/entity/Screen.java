package entity;

import java.util.List;

public class Screen {

    private String screenId;      // Unique identifier
    private String name;          // Screen 1, Audi 2, etc.
    private Theatre theatre;      // Parent theatre
    private List<Seat> seats;     // Seat layout (template)
    private int capacity;         // Derived from seats count

    public Screen(String screenId,
                  String name,
                  Theatre theatre,
                  List<Seat> seats) {

        this.screenId = screenId;
        this.name = name;
        this.theatre = theatre;
        this.seats = seats;
        this.capacity = seats.size();
    }

    public String getScreenId() {
        return screenId;
    }

    public String getName() {
        return name;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public int getCapacity() {
        return capacity;
    }
}
