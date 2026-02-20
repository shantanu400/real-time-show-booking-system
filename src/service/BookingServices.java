package service;

import entity.Booking;
import entity.Seat;
import entity.Show;
import entity.User;

import java.util.List;

public class BookingServices {
    synchronized Booking createBooking(User user, Show show, List<Seat> seats) {
        // lock seats

        // create booking
        return new Booking();
    }

    void confirmBooking(String bookingId) {
        // mark seats BOOKED
    }

    void cancelBooking(String bookingId) {
        // release seats
    }
}
