package entity;

import java.util.UUID;

public class Movie {
    String movieId;
    String title;
    int durationInMinutes;
    String language;
//    Genre genre;

    public Movie(){

    }
    public Movie(String movieId,String title, int durationInMinutes, String language){
        this.movieId= movieId;
        this.title=title;
        this.durationInMinutes=durationInMinutes;
        this.language=language;
    }

    public String getMovieId(){
        return this.movieId;
    }
    public String getMovieIdTitle(){
        return this.title;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

