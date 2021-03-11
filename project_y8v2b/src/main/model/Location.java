package model;

import org.json.JSONObject;
import persistence.Writable;


//A class to represent a single location having a name, type, rating and visiting status.
public class Location implements Writable {

    private String name;    // the name of the location
    private String type;    // the type of location eg. beach , mountain,etc.
    private int rating;     // rating on a scale of one to five stars
    private boolean isVisited;    // have you already visited the location ?


    //REQUIRES: rating>= 1 && rating<= 5
    //MODIFIES: this
    //EFFECTS: name on Location object is set to lname; type on Location object is set to ltype;
    // rating on Location is set to lrating; isVisited is set to lisVisited
    public Location(String name, String type, int rating, boolean isVisited) {
        this.name = name;
        this.type = type;

        this.rating = rating;


        this.isVisited = isVisited;

    }

    public void setName(String name) {

        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRating(int rating) {

        this.rating = rating;



    }

    public void setVisitingStatus(Boolean isVisited) {
        this.isVisited = isVisited;
    }


    // EFFECTS: returns name of Location(string)
    public String getName() {
        return name;
    }

    // EFFECTS: returns the type of Location(string)
    public String getType() {
        return type;
    }

    // EFFECTS: returns rating of Location(int)
    public int getRating() {
        return rating;
    }

    // EFFECTS: returns the visiting status of Location ( True for Visited and False for not visited)
    public boolean getVisitingStatus() {
        return isVisited;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", type);
        json.put("rating", rating);
        json.put("visiting status", isVisited);
        return json;

    }


}














