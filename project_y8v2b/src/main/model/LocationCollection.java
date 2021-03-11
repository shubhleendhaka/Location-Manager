package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//Represents a collection of locations
public class LocationCollection {
    private String name;
    private ArrayList<Location> loc;


    //EFFECTS: constructs LocationCollection with a name and empty list of locations
    public LocationCollection(String name) {
        this.name = name;
        loc = new ArrayList<Location>();


    }

    //EFFECTS: returns LocationCollection's name
    public String getCollectionName() {
        return name;
    }


    //MODIFIES:this
    //EFFECTS: if location l is not present in locationList, add Location l to locationList
    public void addLocation(Location l) {
        if (!loc.contains(l)) {
            loc.add(l);
        }
    }


    //MODIFIES:this
    //EFFECTS:if Location l is present in the locationList, remove Location l from locationList
    public void removeLocation(Location l) {
        loc.remove(l);

    }


    //EFFECTS:if location l is present in locationList, return Location l from locationList,if not,
    // return a null location
    public Location returnLocation(Location l) {
        if (loc.contains(l)) {
            return l;
        } else {
            return null;
        }

    }


    //MODIFIES: l,this
    //EFFECTS: the selected location from the locationList is marked as visited,i.e. change the visitingStatus to true
    public void markVisited(Location l) {
        if (loc.contains(l)) {
            l.setVisitingStatus(true);
        }
    }


    //MODIFIES: l,this
    //EFFECTS: the selected location from the locationList is given rating r,i.e. change the rating of l to r
    public void rateLocation(Location l, int r) throws InvalidRatingException {
        if (r >= 1 && r <= 5) {
            if (loc.contains(l)) {
                l.setRating(r);
            }
        } else {
            throw new InvalidRatingException();
        }


    }

    //EFFECTS:returns the number of locations in this LocationCollection
    public int getsize() {
        return loc.size();
    }

    //EFFECTS:returns an unmodifiable list of Locations in LocationCollection
    public List<Location> getLocations() {
        return Collections.unmodifiableList(loc);
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("locations", locationsToJson());
        return json;
    }

    //EFFECTS: returns locations in this LocationCollection as a JSON array
    private JSONArray locationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Location l : loc) {
            jsonArray.put(l.toJson());
        }

        return jsonArray;
    }


}






