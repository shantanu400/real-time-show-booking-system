package service;

import entity.*;
import enums.SeatType;
import factory.SeatFactory;
import repository.AdminRepository;

import java.time.LocalDateTime;
import java.util.List;

public class AdminServices {
    private AdminRepository adminRepository;

    public AdminServices(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }
    public void addTheater(String theaterId,String name,String cityName){
            City city=new City("C",cityName);
            Theatre theatre=new Theatre(theaterId,name,city);
        adminRepository.saveTheater(theatre);

    }

    public void addMovie(String movieId,String title, int durationInMinutes, String language){

            Movie movie=new Movie(movieId,title,durationInMinutes,language);


    adminRepository.saveMovie(movie);
    }


    public void addScreen(String screenId,String name,String theaterId,int rows,
                          int seatsPerRow){
        Theatre theatre=adminRepository.findTheaterById(theaterId);
        List<Seat> seats = SeatFactory.createSeats(rows, seatsPerRow);
            Screen screen = new Screen(screenId, name, theatre, seats);
            adminRepository.saveScreen(screen);


    }

    public void createShow(String movieId,
                           String screenId,
                           LocalDateTime startTime,
                           double price) {
        Movie movie=adminRepository.findMovieById(movieId);
        Screen screen=adminRepository.findScreenById(screenId);

            Show show = new Show("M",movie, screen, startTime, price);
            adminRepository.saveShow(show);



    }
}
