package persistence;

import model.Location;
import model.LocationCollection;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


//Represents a reader that reads LocationCollection from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS:constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads LocationCollection from file and returns it;
    //throws IOException if an error occurs reading data from file
    public LocationCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLocationCollection(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS:parses LocationCollection from JSON object and returns it
    private LocationCollection parseLocationCollection(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        LocationCollection loc = new LocationCollection(name);
        addLocations(loc, jsonObject);
        return loc;
    }

    //MODIFIES:col
    //EFFECTS: parses locations from JSON object and adds them to loc
    private void addLocations(LocationCollection loc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("locations");
        for (Object json : jsonArray) {
            JSONObject nextLocation = (JSONObject) json;
            addLocation(loc, nextLocation);
        }

    }

    //MODIFIES: col
    //EFFECTS: parses location from JSON object and adds it to LocationCollection
    private void addLocation(LocationCollection loc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        int rating = jsonObject.getInt("rating");
        boolean isVisited = jsonObject.getBoolean("visiting status");
        Location l = new Location(name, type, rating, isVisited);
        loc.addLocation(l);

    }


}


