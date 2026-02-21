package repository;

import entity.Booking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookingRepository {
    private final Map<String, Booking> bookings = new ConcurrentHashMap<>();

    public void save(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }

    public Booking findById(String bookingId) {
        return bookings.get(bookingId);
    }
}
