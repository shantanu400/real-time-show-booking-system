package entity;

import java.util.UUID;

public class Theatre {
   private String theatreId;
    private String name;
    private City city;

    public Theatre(){

    }
    public Theatre(String theatreId,String name, City city){
        this.theatreId= theatreId;
        this.city=city;
        this.name=name;
    }
    public String getTheaterId(){
        return this.theatreId;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(String theatreId) {
        this.theatreId = theatreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
