package controller;

import entity.Movie;
import service.AdminServices;

import java.time.LocalDateTime;

public class AdminController {
    private AdminServices adminServices;

    public AdminController(AdminServices adminServices){
        this.adminServices=adminServices;

    }
    public void addTheater(String theaterId,String name,String city){
        adminServices.addTheater(theaterId,name,city);

    }
    public void addMovie(String movieId,String title, int durationInMinutes, String language){

    adminServices.addMovie(movieId,title,durationInMinutes,language);
    }


    public void addScreen(String screenId,String name,String theaterId,int rows,
                          int seatsPerRow){
        adminServices.addScreen(screenId,name,theaterId,rows,seatsPerRow);
    }
    public void createShow(String movieId,
                           String screenId,
                           LocalDateTime startTime,
                           double price) {
        adminServices.createShow(movieId, screenId, startTime, price);
    }


}
