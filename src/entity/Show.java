package entity;

import enums.SeatsStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class Show {
    private String showId;
    private Movie movie;
    private Screen screen;
    private LocalDateTime startTime;
    private double price;

    public Show(){

    }
public Show(String showId,Movie movie,Screen screen,LocalDateTime startTime,double price){
    this.showId=showId;
    this.movie=movie;
    this.screen=screen;
    this.startTime=startTime;
    this.price=price;

}
public Movie getMovie() {return this.movie;}

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Show{" +
                "showId='" + showId + '\'' +
                ", movie='" + movie.getTitle() + '\'' +
                ", screen='" + screen.getName() + '\'' +
                ", theatre='" + screen.getTheatre().getName() + '\'' +
                ", startTime=" + startTime +
                ", price=" + price +
                '}';
    }


}
