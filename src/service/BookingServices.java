package service;

import domain.SeatLockManager;
import entity.Booking;
import entity.Seat;
import entity.Show;
import enums.BookingStatus;
import enums.SeatsStatus;
import repository.BookingRepository;
import repository.ShowRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookingServices {
    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;
    private final SeatLockManager lockManager;
    public BookingServices(ShowRepository showRepository,
                          BookingRepository bookingRepository) {

        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
        this.lockManager = SeatLockManager.getInstance();
    }

    public synchronized Booking createBooking(String showId, List<String> seatIds,String user, double amount) {
        Show show = showRepository.findById(showId);

        // Validate seats AVAILABLE
        for (String seatId : seatIds) {
            if (show.getSeatStatus(seatId) != SeatsStatus.AVAILABLE) {
                throw new RuntimeException("Seat not available: " + seatId);
            }
        }
        String bookingId = UUID.randomUUID().toString();
        // lock seats
        boolean locked = lockManager.lockSeats(showId, bookingId, seatIds);

        if (!locked) {
            throw new RuntimeException("Seats already locked");
        }

        // Create booking
        Booking booking = new Booking(bookingId, showId,user, seatIds, amount);

        bookingRepository.save(booking);

        return booking;
    }

    public BookingStatus confirmBooking(String bookingId, boolean paymentSuccess) {

        Booking booking = bookingRepository.findById(bookingId);
        String showId = booking.getShowId();
        List<String> seatIds = booking.getSeatIds();

        if (paymentSuccess) {
            Show show = showRepository.findById(showId);
            show.markSeatsBooked(seatIds);

            lockManager.confirmSeats(show.getShowId(), seatIds, bookingId);

            booking.setConfirmed();
            return BookingStatus.CONFIRMED;

        } else {

            lockManager.releaseSeats(showId, seatIds);

            booking.setFailed();
            return  BookingStatus.FAILED;
        }
    }

}
