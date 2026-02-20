package service;

import entity.City;
import entity.Movie;
import entity.Show;
import repository.ShowRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowServices {

private ShowRepository showRepository;

public ShowServices(ShowRepository showRepository){
    this.showRepository=showRepository;
}

    public List<Show> searchShow(String showName){

        return showRepository.findByName(showName);


    }

    public void addShow(Show show){

    }
}
