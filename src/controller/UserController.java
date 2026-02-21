package controller;

import entity.Booking;
import entity.Show;
import enums.BookingStatus;
import exception.InvalidInputException;
import exception.NotFoundException;
import service.BookingServices;
import service.ShowServices;

import java.util.Collections;
import java.util.List;

public class UserController {

    private ShowServices showServices;
    private BookingServices bookingServices;

    public UserController(ShowServices showServices, BookingServices bookingServices) {
        this.showServices = showServices;
        this.bookingServices=bookingServices;
    }


    public List<Show> searchShow(String showName){

        if (showName == null || showName.trim().isEmpty()) {
            System.out.println("Show name cannot be empty");
            return Collections.emptyList();
        }

        try {
            List<Show> result=showServices.searchShow(showName);
            System.out.println(result);
            return result;
         }
        catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }


    public Booking bookSeats(String showId, List<String> seats,String user, double amount) {
        return bookingServices.createBooking(showId, seats,user, amount);
    }

    public BookingStatus confirm(String bookingId, boolean paymentSuccess) {
        return bookingServices.confirmBooking(bookingId, paymentSuccess);
    }


}
