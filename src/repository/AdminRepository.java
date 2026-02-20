package repository;

import entity.*;

import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
List<Movie> movieList=new ArrayList<>();
List<Theatre>theaterList=new ArrayList<>();
List<Screen>screenList=new ArrayList<>();

List<Show> showList=new ArrayList<>();
    public void saveTheater(Theatre theatre){
            theaterList.add(theatre);
    }
    public void saveMovie(Movie movie){

            movieList.add(movie);
    }

    public void saveShow(Show show) {
        showList.add(show);

    }
    public Movie findMovieById(String movieId) {
        for (Movie movie : movieList) {
            if (movie.getMovieId().equals(movieId)) {
                return movie;
            }
        }
        throw new RuntimeException("Movie not found");
    }

    public Theatre findTheaterById(String theaterId) {
        for (Theatre theater : theaterList) {
            if (theater.getTheaterId().equals(theaterId)) {
                return theater;
            }
        }
        throw new RuntimeException("Screen not found");
    }


    public Screen findScreenById(String screenId) {
        for (Screen screen : screenList) {
            if (screen.getScreenId().equals(screenId)) {
                return screen;
            }
        }
        throw new RuntimeException("Screen not found");
    }

    public void saveScreen(Screen screen){
        screenList.add(screen);
    }

    public List<Show> getAllShows() {
        return showList;
    }

}
