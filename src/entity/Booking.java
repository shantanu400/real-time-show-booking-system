package entity;

import java.util.List;
import enums.BookingStatus;

public class Booking {
    private String bookingId;
    private String showId;
    private String user;
    private List<String> seatIds;
    private BookingStatus status;
    private double totalAmount;

    public Booking(String bookingId, String showId,
                   String user,
                   List<String> seatIds,
                   double totalAmount) {

        this.bookingId = bookingId;
        this.showId = showId;
        this.user = user;
        this.seatIds = seatIds;
        this.totalAmount = totalAmount;
        this.status = BookingStatus.PAYMENT_PENDING;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getShowId() {
        return showId;
    }
    public String getUser() {
        return user;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public void setConfirmed() {
        this.status = BookingStatus.CONFIRMED;
    }

    public void setFailed() {
        this.status = BookingStatus.FAILED;
    }
    public BookingStatus getStatus(){
        return this.status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
