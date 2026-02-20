package controller;

import entity.Show;
import exception.InvalidInputException;
import exception.NotFoundException;
import service.ShowServices;

import java.util.Collections;
import java.util.List;

public class ShowController {

    private ShowServices showServices;

    public ShowController(ShowServices showServices) {
        this.showServices = showServices;
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


}
