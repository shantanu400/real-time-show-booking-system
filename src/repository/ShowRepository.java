package repository;

import entity.Show;
import exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShowRepository {

    private  AdminRepository adminRepository;
    Map<String, List<Show>> showList=new ConcurrentHashMap<>();

    public ShowRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Show> findByName(String showName){
        List<Show> shows=adminRepository.getAllShows();
        List<Show> result=new ArrayList<>();
        for(Show it: shows){
            if(it.getMovie().getMovieIdTitle().equals(showName)){
                result.add(it);
            }
        }

        if ( result.isEmpty()) {
            throw new NotFoundException("This show is not available anymore");
        }
        return result;
    }

}
