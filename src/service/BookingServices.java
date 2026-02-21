package service;

import domain.SeatLockManager;
import entity.Booking;
import entity.Payment;
import entity.Seat;
import entity.Show;
import enums.BookingStatus;
import enums.PaymentMode;
import enums.PaymentStatus;
import enums.SeatsStatus;
import payment.PaymentStrategy;
import repository.BookingRepository;
import repository.ShowRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookingServices {
    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;
    private final SeatLockManager lockManager;
    private final PaymentServices paymentServices;
    public BookingServices(ShowRepository showRepository,
                          BookingRepository bookingRepository,PaymentServices paymentServices) {

        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
        this.lockManager = SeatLockManager.getInstance();
        this.paymentServices=paymentServices;
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

    public BookingStatus confirmBooking(String bookingId, PaymentMode mode, PaymentStrategy paymentStrategy) {

        Booking booking = bookingRepository.findById(bookingId);
        String showId = booking.getShowId();
        List<String> seatIds = booking.getSeatIds();

        Payment payment = paymentServices.processPayment(
                bookingId,
                booking.getTotalAmount(),
                mode,
                paymentStrategy
        );

        if (payment.getStatus() == PaymentStatus.SUCCESS) {
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
