package entity;

import java.util.List;
import java.util.UUID;
import enums.BookingStatus;

public class Booking {
    private UUID bookingId;
    private Show show;
    private String user;
    private List<Seat> seats;
    private BookingStatus status;
    private Payment payment;


}
