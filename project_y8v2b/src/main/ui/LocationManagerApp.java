package ui;

import model.Location;
import model.LocationCollection;
import persistence.JsonReader;
import persistence.JsonWriter;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


//Represents the location manager application
public class LocationManagerApp {

    private LocationCollection loc;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "data/myFile.json";

    //EFFECTS:runs the Location Manager application
    public LocationManagerApp() throws FileNotFoundException {
        runApplication();
    }

    //MODIFIES:this
    //EFFECTS:processes user input
    private void runApplication() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    //MODIFIES:this
    //EFFECTS; initializes LocationList and Locations
    private void init() {
        //l1 = new Location("Vancouver", "City", 5, true);
        loc = new LocationCollection("My Collection");
        input = new Scanner(System.in);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

    }


    //MODIFIES:this
    //EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAddLocation();
        } else if (command.equals("v")) {
            printLocationNames();
        } else if (command.equals("r")) {
            doLocationRating();
        } else if (command.equals("m")) {
            changeVisitingStatus();
        } else if (command.equals("d")) {
            viewLocationDetails();
        } else if (command.equals("s")) {
            saveCollectionToFile();
        } else if (command.equals("l")) {
            loadCollectionFromFile();
        } else {
            System.out.println("Selection is not valid");
            System.out.println("Select again...");
        }

    }

    //EFFECTS: display menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view names of locations in the collection");
        System.out.println("\ta -> add location to collection");
        System.out.println("\tr -> rate a location");
        System.out.println("\tm -> mark a location as Visited");
        //System.out.println("\tx -> Remove Location from Collection");
        System.out.println("\td -> view details of a location");
        System.out.println("\ts -> save collection to file");
        System.out.println("\tl -> load collection from file");
        System.out.println("\tq -> Quit");
    }


    //EFFECTS: print the names of all the Locations in the LocationCollection to the console
    private void printLocationNames() {
        List<Location> locations = loc.getLocations();

        System.out.println("The list of names of locations in the collection:");
        if (locations.isEmpty()) {
            System.out.println("Empty Collection");
        } else {
            for (Location l : locations) {
                System.out.println(l.getName());

            }
        }

    }


    //MODIFIES:loc
    //EFFECTS: add a Location with name,type,rating and visitingStatus to LocationCollection
    private void doAddLocation() {
        Location lnew = new Location(null, null, 1, false);
        System.out.println("Enter Location's name, type and rating (on a scale of 1 to 5):");
        String name = input.next();
        lnew.setName(name);
        String type = input.next();
        lnew.setType(type);
        //System.out.println("Enter Location's rating (on a scale of 1 to 5):");
        int rating = input.nextInt();
        if (rating == 1 || rating == 2 || rating == 3 || rating == 4 || rating == 5) {
            lnew.setRating(rating);
        } else {
            System.out.println("\nInvalid rating -> rating set to 1");
        }
        System.out.println("Enter Location's visiting status [T for visited, F for not visited]");
        String visitingStatus = input.next();
        visitingStatus = visitingStatus.toLowerCase();
        if (visitingStatus.equals("t")) {
            lnew.setVisitingStatus(true);
        } else if (visitingStatus.equals("f")) {
            lnew.setVisitingStatus(false);
        } else {
            System.out.println("Input is not valid,visiting status is set to false");
        }
        loc.addLocation(lnew);

        //System.out.println(" ");
        //System.out.println("The location given below is added to the Collection of locations!");
        //System.out.println("Location's name: " + lnew.getName() + " , type: " + lnew.getType()
        //       + " , rating: " + lnew.getRating() + " , visiting status: " + lnew.getVisitingStatus());
        //checkLocationDetails(lnew);
    }


    //EFFECTS: prints details of Location
    private void checkLocationDetails(Location lnew) {
        System.out.println("Location's name: " + lnew.getName() + " , type: " + lnew.getType()
                + " , rating: " + lnew.getRating() + " , visiting status: " + lnew.getVisitingStatus());

    }

    //EFFECTS: prints the details of the selected Location on the console
    private void viewLocationDetails() {
        List<Location> locations = loc.getLocations();
        printLocationNames();

        System.out.println("Input selected location's name (from the list):");
        String locationToView = input.next();
        System.out.println("The selected location is: " + locationToView);

        ArrayList<String> locationsName = new ArrayList<>();
        for (Location l : locations) {
            locationsName.add(l.getName());
        }

        // for (String s : locationsName) {
        //    System.out.println(s);
        //}

        if (locationsName.contains(locationToView)) {
            for (Location l : locations) {
                if (l.getName().equals(locationToView)) {
                    System.out.println("Location's name: " + l.getName() + " , type: " + l.getType()
                            + " , rating: " + l.getRating() + " , visiting status: " + l.getVisitingStatus());

                }
            }
        } else {
            System.out.println("Invalid selection");
        }
    }






/*
        for(Location l: locations){
           // if(!l.getName().equals(locationToView)){
           //     System.out.println("Invalid selection");
            } else{ System.out.println("Location's name: " + l.getName() + " , type: " + l.getType()
                    + " , rating: " + l.getRating() + " , visiting status: " + l.getVisitingStatus());

            }
        }

       /* for (Location l : locations) {
            if (l.getName().equals(locationToView)) {
                System.out.println("Location's name: " + l.getName() + " , type: " + l.getType()
                        + " , rating: " + l.getRating() + " , visiting status: " + l.getVisitingStatus());
            }
        }


    }

 */


    //MODIFIES:loc,location
    //EFFECTS: select the location from a list of locations and rate the selected location
    private void doLocationRating() {
        List<Location> locations = loc.getLocations();
        ArrayList<String> locationsName = new ArrayList<>();
        for (Location l : locations) {
            locationsName.add(l.getName());
        }
        printLocationNames();

        System.out.println("Input selected location's name (from the list):");
        String locationToRate = input.next();
        System.out.println("The selected location is: " + locationToRate);

        helper(locationsName, locationToRate, locations);

    }

    //EFFECTS: helper function that checks if locationToRate is in locationsName,then takes rating input from user and
    // rates the locationToRate
    private void helper(ArrayList<String> locationsName, String locationToRate, List<Location> locations) {
        if (locationsName.contains(locationToRate)) {
            System.out.println("What rating would you like to give to the location:(on a scale of 1 to 5)");
            String inputRating = input.next();
            int inputRating1 = Integer.parseInt(inputRating);
            if ((inputRating1 == 1) || (inputRating1 == 2) || (inputRating1 == 3) || (inputRating1 == 4)
                    || (inputRating1 == 5)) {
                for (Location l : locations) {
                    if (l.getName().equals(locationToRate)) {
                        l.setRating(inputRating1);
                        checkLocationDetails(l);
                    }
                }
            } else {
                System.out.println("Invalid rating -> no changes");
            }
        } else {
            System.out.println("Invalid selection");
        }

    }


    

   /* private int takeInputRating(){
        System.out.println("What rating would you like to give to the location:(on a scale of 1 to 5)");

        String inputRating = input.next();

        int inputRating1 = Integer.parseInt(inputRating);
        
        return inputRating1;
        
    }

    */

    //MODIFIES:loc,location
    //EFFECTS: select the location from a list of locations and mark the selected location as visited,
    // i.e. change Visiting Status to true
    private void changeVisitingStatus() {

        List<Location> locations = loc.getLocations();//list of locations

        ArrayList<String> locationsName = new ArrayList<>();//list of names of locations
        for (Location l : locations) {
            locationsName.add(l.getName());
        }

        printLocationNames();// prints list of names of locations in the collection on the console

        System.out.println("Input selected location's name (from the list):");
        String locationToMark = input.next();
        System.out.println("The selected location is: " + locationToMark);

        if (locationsName.contains(locationToMark)) {
            for (Location l : locations) {
                if (l.getName().equals(locationToMark)) {
                    l.setVisitingStatus(true);

                    System.out.println("The updated location has name: " + l.getName() + " , type: " + l.getType()
                            + " , rating: " + l.getRating() + " , visiting status: " + l.getVisitingStatus());
                }
            }
        } else {
            System.out.println("Invalid selection");
        }


    }

    /*
    //MODIFIES:loc
    //EFFECTS: select and remove a location from the LocationCollection
    private void doRemoveLocation() {
        List<Location> locations = loc.getLocations();
        printLocationNames();

        System.out.println("Input selected location's name (from the list):");
        String locationToRemove = input.next();
        System.out.println("The selected location is: " + locationToRemove);

        for (Location l : locations) {
            if (l.getName().equals(locationToRemove)) {
                loc.removeLocation(l);
            }
        }
        System.out.println(" ");
        System.out.println(" ");

        System.out.println("Updated list of names of Locations in the collection:");
        printLocationNames();


    }

     */


    //EFFECTS: saves the collection to file
    private void saveCollectionToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(loc);
            jsonWriter.close();
            System.out.println("Saved " + loc.getCollectionName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }


    //MODIFIES:this
    //EFFECTS: loads collection from file
    private void loadCollectionFromFile() {
        try {
            loc = jsonReader.read();
            System.out.println("Loaded " + loc.getCollectionName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }




}

















































