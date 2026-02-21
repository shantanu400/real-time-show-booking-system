import controller.AdminController;
import controller.UserController;
import domain.SeatLockManager;
import entity.Booking;
import entity.Show;
import enums.BookingStatus;
import repository.AdminRepository;
import repository.BookingRepository;
import repository.ShowRepository;
import service.AdminServices;
import service.BookingServices;
import service.ShowServices;

import java.time.LocalDateTime;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


   /*   Admin */
        AdminRepository adminRepository=new AdminRepository();
        AdminServices adminServices=new AdminServices(adminRepository);
        AdminController adminController=new AdminController(adminServices);
        adminController.addMovie("M1","little krishna",20,"hindi");
        adminController.addTheater("T1","RadhaVenimadhav Media","Goloka");
        adminController.addScreen(
                "SC1",
                "Nityananda",
                "T1",
                6,
                3
        );
        adminController.createShow("SH1","M1","SC1", LocalDateTime.now().plusHours(2),300);

        /* User  */
        ShowRepository showRepository = new ShowRepository(adminRepository);
        ShowServices showServices = new ShowServices(showRepository);
        BookingRepository bookingRepository=new BookingRepository();
        BookingServices bookingServices=new BookingServices(showRepository,bookingRepository);
        UserController UserController = new UserController(showServices,bookingServices);
        List<Show> shows= UserController.searchShow("little krishna");
        for (Show show : shows) {
            System.out.println(show);
        }

        List<String> selectedSeats = List.of("A1", "A2","A3","B2","B3");

        Booking booking = UserController.bookSeats(
                "SH1",
                selectedSeats,
                "HareKrishna",
                600
        );

        System.out.println("Booking Created by: " + booking.getUser()+", with booking id:"+ booking.getBookingId() );

// Simulate payment success
        BookingStatus bookingStatus=UserController.confirm(booking.getBookingId(), true);
        System.out.println(bookingStatus==BookingStatus.CONFIRMED?"Booking Confirmed":"Booking Failed");


    }
}