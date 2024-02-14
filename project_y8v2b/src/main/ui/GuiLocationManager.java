package ui;


import model.InvalidRatingException;
import model.LocationCollection;
import model.Location;
import persistence.JsonReader;
import persistence.JsonWriter;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Represents GUI location manager application
public class GuiLocationManager extends JFrame implements ActionListener {

    public JFrame frame;        //main window
    public JFrame newFrame;     //addLocationWindow
    public JFrame newFrame2;    //markAsVisitedWindow
    public JFrame newFrame3;    //rateLocationWindow
    MyFrame myFrame;            //imageWindow
    public JFrame displayFrame; //displayAllLocationsWindow
    private JFrame invalidRatingFrame;
    public JFrame invalidVisitingStatusFrame;


    public JPanel toolPanel;
    public JPanel displayLoc;
    public JPanel newPanel;

    public LocationCollection loc;

    JTextField locName;
    JTextField locType;
    JTextField locRating;
    JTextField locVisited;
    JTextArea displayLocTextArea;
    JLabel header;


    public JsonWriter jsonWriter;
    public JsonReader jsonReader;
    private static final String JSON_STORE = "data/myFile.json";

    String s1 = "                 " + "           " + " " + "               ";
    String s2 = "                                            ";

    int uiRating = -1;


    //MODIFIES:this
    //EFFECTS:constructs main window in which LocationManagerApp runs and instantiates declared fields
    public GuiLocationManager() {
        myFrame = new MyFrame();             //imageFrame
        myFrame.setLocation(470, 200);

        frame = new JFrame("My Location Collection"); //mainFrame
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 600);
        //frame.setMaximumSize(new Dimension(1000, 1000));
        //frame.setMinimumSize(new Dimension(600, 600));

        //initialize LocationList
        loc = new LocationCollection("My Collection");


        //initialise Json
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        //INSTANTIATE THE JTEXTFIELDS
        locName = null;
        locType = null;
        locRating = null;
        locVisited = null;

        //addUpperPanel
        displayLocationsInCollectionUpperPanel();

        //addLowerPanel
        displayButtonOptionsBottomPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        centreOnScreen();
        frame.setVisible(true);

    }


    //Centres frame on desktop
    //MODIFIES: this
    //EFFECTS: location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((scrn.width - frame.getWidth()) / 2, (scrn.height - frame.getHeight()) / 2);
    }


    //MODIFIES:this
    //EFFECTS: declares and instantiates a LocationCollection(new LocationCollection),
    //and add it as a panel on the frame
    //upper panel displays the multiple locations in the collection and has a button that opens a new window to display
    //the locations in the collection
    private void displayLocationsInCollectionUpperPanel() {

        displayLoc = new JPanel();


        helperMethodForUpperPanel();


        JButton checkListOfLocations = new JButton("Check details of all locations in Collection");
        checkListOfLocations.setActionCommand("Check");
        checkListOfLocations.addActionListener(this);
        checkListOfLocations.setBounds(250, 300, 200, 40);
        displayLoc.add(checkListOfLocations);


        JButton reloadButton = new JButton("Reload");
        reloadButton.setActionCommand("Reload");
        reloadButton.addActionListener(this);
        reloadButton.setBounds(250, 360, 200, 40);
        displayLoc.add(reloadButton);

        displayLoc.setVisible(true);
        frame.add(displayLoc, BorderLayout.CENTER);

    }

    //EFFECTS: helper method that creates JLabel, JTextArea and adds both to a JPanel
    private void helperMethodForUpperPanel() {
        header = new JLabel("My Locations");
        header.setLocation(400, 10);
        header.setBounds(400, 10, 100, 20);
        header.setBorder(new LineBorder(Color.black));
        header.setBackground(Color.white);

        header.setLocation(150, 10);

        displayLocTextArea = new JTextArea(s1 + "   No location to show        " + s2, 20, 1);
        displayLocTextArea.setBorder(new LineBorder(Color.black));
        displayLocTextArea.setBackground(Color.darkGray);
        displayLocTextArea.setSize(new Dimension(600, 200));
        displayLocTextArea.setMaximumSize(new Dimension(300, 200));
        displayLocTextArea.setBounds(0, 50, 600, 300);

        displayLocTextArea.setEditable(false);


        displayLoc.add(header);
        displayLoc.add(displayLocTextArea);


    }


    //MODIFIES:this
    //EFFECTS: a helper method which declares and instantiates all buttons
    //and adds all buttons to a panel on the frame (at the bottom)
    private void displayButtonOptionsBottomPanel() {
        toolPanel = new JPanel();
        toolPanel.setBounds(400, 0, 600, 500);
        toolPanel.setMinimumSize(new Dimension(600, 500));
        toolPanel.setBackground(Color.darkGray);
        toolPanel.setLayout(new GridLayout(0, 1));

        toolPanel.add(addLocationButton());
        toolPanel.add(markAsVisitedButton());
        toolPanel.add(changeRatingButton());
        toolPanel.add(saveButton());
        toolPanel.add(loadButton());
        toolPanel.add(quitButton());

        frame.add(toolPanel, BorderLayout.SOUTH);
    }


    //MODIFIES: this
    //EFFECTS: creates new button to add new location to the collection
    //and associates button with a new ActionCommand
    private JButton addLocationButton() {
        JButton addLocationB = new JButton("Add Location");
        //addLocationB.setBounds(600,50,300,100);
        addLocationB.setActionCommand("Add Location");
        addLocationB.addActionListener(this);
        return addLocationB;

    }


    //MODIFIES: this
    //EFFECTS: creates new button to mark a location (in the collection) as visited
    //and associates button with a new ActionCommand
    private JButton markAsVisitedButton() {
        JButton markAsVisitedB = new JButton("Mark as Visited");
        //markAsVisitedB.setBounds(600,200,300,100);
        markAsVisitedB.setActionCommand("Mark as Visited");
        markAsVisitedB.addActionListener(this);
        return markAsVisitedB;

    }

    //MODIFIES: this
    //EFFECTS: creates new button to rate a location (in the collection)
    //and associates button with a new ActionCommand
    private JButton changeRatingButton() {
        JButton changeRatingB = new JButton("Change Rating");
        //changeRatingB.setBounds(600,350,300,100);
        changeRatingB.setActionCommand("Change Rating");
        changeRatingB.addActionListener(this);
        return changeRatingB;

    }

    //MODIFIES: this
    //EFFECTS: creates new button to save collection to file
    //and associates button with a new ActionCommand
    private JButton saveButton() {
        JButton saveB = new JButton("Save");
        //saveB.setBounds(600,500,300,100);
        saveB.setActionCommand("Save");
        saveB.addActionListener(this);
        return saveB;
    }

    //MODIFIES: this
    //EFFECTS: creates new button to load collection from file
    //and associates button with a new ActionCommand
    private JButton loadButton() {
        JButton loadB = new JButton("Load");
        //loadB.setBounds(600,650,300,100);
        loadB.setActionCommand("Load");
        loadB.addActionListener(this);
        return loadB;
    }

    //MODIFIES: this
    //EFFECTS: creates new button to exit the GUI application
    //and associates button with a new ActionCommand
    private JButton quitButton() {
        JButton quitB = new JButton("Quit");
        //quitB.setBounds(600,800,300,100);
        quitB.setActionCommand("Quit");
        quitB.addActionListener(this);
        return quitB;
    }




    // EFFECTS:called by the framework when the button is clicked
    //performs the action based on which button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Location")) {
            addLocationHelper();
        }

        if (e.getActionCommand().equals("done1")) {
            helperAddLocationSetsValuesOfFields(); //helper

        }

        if (e.getActionCommand().equals("Mark as Visited")) {
            markAsVisitedHelper();
        }
        if (e.getActionCommand().equals("done2")) {
            helperMarkLocationSetAsMarked();
        }


        if (e.getActionCommand().equals("Change Rating")) {
            changeRatingHelper();
        }
        if (e.getActionCommand().equals("done3")) {
            helperRateLocationSetsRating();//helper
        }

        helperActionListener(e);

    }


    //MODIFIES:location,loc
    //EFFECTS: helper method that sets the name,type,rating and visitingStatus of lnew and add lnew to loc
    private void helperAddLocationSetsValuesOfFields()  {
        Location lnew = new Location(null, null, 1, false);
        lnew.setName(locName.getText());
        lnew.setType(locType.getText());

        helperRateLocationCheckIfSelectionIsValid();

        if ((uiRating == 1) || (uiRating == 2) || (uiRating == 3) || (uiRating == 4) || (uiRating == 5)) {
            lnew.setRating(uiRating);
            myFrame.setVisible(true);
        } else {
            System.out.println("Invalid selection -> Rating is set to 1. Please use 'Change Rating' "
                    + "button to change the rating according to your liking. ");
        }


        String uiVisited = locVisited.getText();
        uiVisited = uiVisited.toLowerCase();
        if (uiVisited.equals("t")) {
            lnew.setVisitingStatus(true);
        } else if (uiVisited.equals("f")) {
            lnew.setVisitingStatus(false);
        }

        loc.addLocation(lnew);

        displayLoc.revalidate();


        newFrame.setVisible(false);//in addLocationWindow
    }

    //MODIFIES:location,loc,this
    //EFFECTS: helper method that checks if the name of the location entered by the user is in the collection,
    //if yes, sets the rating of that location according to user input
    private void helperRateLocationSetsRating() /*throws InvalidRatingException*/ {
        List<Location> locations = loc.getLocations();
        ArrayList<String> locationsName = new ArrayList<>();
        for (Location l : locations) {
            locationsName.add(l.getName());
        }

        helperRateLocationCheckIfSelectionIsValid();

        if (locationsName.contains(locName.getText())) {
            for (Location lnew : locations) {
                if (lnew.getName().equals(locName.getText())) {
                    if ((uiRating == 1) || (uiRating == 2) || (uiRating == 3) || (uiRating == 4) || (uiRating == 5)) {
                        lnew.setRating(uiRating);
                        myFrame.setVisible(true);
                    } else {
                        System.out.println("Invalid selection -> Rating not changed. Please use 'Change Rating' "
                                + "button to change the rating according to your liking. ");
                    }
                }
            }
        }
        displayLoc.revalidate();
        newFrame3.setVisible(false);
    }


    //EFFECTS: helper method that catches NumberFormatException
    private void helperRateLocationCheckIfSelectionIsValid() {
        try {
            uiRating = Integer.parseInt(locRating.getText());
        } catch (NumberFormatException ne) {
            //
        }
    }

    //MODIFIES: this,loc,location
    //EFFECTS:helper method that checks if the name of the location entered by the user is in the collection,
    // if yes, sets the visiting status of that location according to user input
    private void helperMarkLocationSetAsMarked() {
        List<Location> locations = loc.getLocations();
        ArrayList<String> locationsName = new ArrayList<>();
        for (Location l : locations) {
            locationsName.add(l.getName());
        }

        if (locationsName.contains(locName.getText())) {
            for (Location l : locations) {
                if (l.getName().equals(locName.getText())) {
                    l.setVisitingStatus(true);
                }
            }
        }
        myFrame.setVisible(true);
        displayLoc.revalidate();
        newFrame2.setVisible(false);//in markAsVisited window
    }

    //EFFECTS: performs the action(save to file, load from file, display display frame, reload)
    // based on which button is clicked
    private void helperActionListener(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            saveHelper();
            myFrame.setVisible(true);
        }
        if (e.getActionCommand().equals("Load")) {
            loadHelper();
            displayLoc.revalidate();
            myFrame.setVisible(true);
        }
        if (e.getActionCommand().equals("Quit")) {
            frame.setVisible(false);
        }

        if (e.getActionCommand().equals("Check")) {
            makeDisplayLocationsFrame();


        }
        if (e.getActionCommand().equals("Reload")) {
            reloadTextAreaHelper();
        }
    }


    //MODIFIES:this,loc
    //EFFECTS:add a Location with name,type,rating and visitingStatus to LocationCollection
    public void addLocationHelper() {
        newFrame = new JFrame("Add new location here...");
        newFrame.setSize(1000, 400);
        newFrame.setLocation(250, 200);

        helperAddLocation();

        helperAddLocation1();


        JButton doneButton = new JButton("Done");
        doneButton.setActionCommand("done1");
        doneButton.addActionListener(this);
        doneButton.setBorder(new LineBorder(Color.black));
        doneButton.setBounds(350, 220, 200, 30);
        newFrame.add(doneButton);

        JButton bb = new JButton(" ");
        newFrame.add(bb);

        newFrame.setVisible(true);

    }

    //EFFECTS: helper method creates JLabels and JTextField for locName and locType
    private void helperAddLocation() {
        //user input:Location name
        JLabel lnameLabel = new JLabel("Enter location's name:");
        lnameLabel.setBounds(20, 20, 200, 30);
        lnameLabel.setBorder(new LineBorder(Color.black));
        newFrame.add(lnameLabel);

        locName = new JTextField();
        locName.setBorder(new LineBorder(Color.blue));
        locName.setBounds(250, 20, 200, 30);
        newFrame.add(locName);


        //user input:Location type
        JLabel ltypeLabel = new JLabel("Enter location's type:");
        ltypeLabel.setBounds(20, 70, 200, 30);
        ltypeLabel.setBorder(new LineBorder(Color.black));
        newFrame.add(ltypeLabel);

        locType = new JTextField();
        locType.setBorder(new LineBorder(Color.blue));
        locType.setBounds(250, 70, 200, 30);
        newFrame.add(locType);


    }

    //EFFECTS: helper method creates JLabels and JTextField for locRating and locVisited
    private void helperAddLocation1() {
        //user input: Location rating
        JLabel lratingLabel = new JLabel("Enter location's rating (1 to 5):");
        lratingLabel.setBounds(20, 120, 200, 30);
        lratingLabel.setBorder(new LineBorder(Color.black));
        newFrame.add(lratingLabel);

        locRating = new JTextField();
        locRating.setBorder(new LineBorder(Color.blue));
        locRating.setBounds(250, 120, 200, 30);
        newFrame.add(locRating);

        //user input: Location isVisited
        JLabel lvisitedLabel = new JLabel("Enter location's visiting status (t for Visited, f for Not Visited):");
        lvisitedLabel.setBounds(20, 170, 430, 30);
        lvisitedLabel.setBorder(new LineBorder(Color.black));
        newFrame.add(lvisitedLabel);

        locVisited = new JTextField();
        locVisited.setBorder(new LineBorder(Color.blue));
        locVisited.setBounds(480, 170, 300, 30);
        newFrame.add(locVisited);
    }


    //MODIFIES:this,loc,location
    //EFFECTS:takes user's input to select a location from the locations in loc
    //and marks the selected Location as visited(sets isVisited=true)
    public void markAsVisitedHelper() {
        //select a location from the existing locations
        //set isVisitedStatus to true
        newFrame2 = new JFrame("Mark as visited window ");
        newFrame2.setSize(660, 400);//height=300
        newFrame2.setLocation(390, 250);

        //user input:Location name( from the list)
        JLabel lnameLabel = new JLabel("Enter selected location's name (from the list) :");
        lnameLabel.setBounds(20, 20, 300, 30);
        lnameLabel.setBorder(new LineBorder(Color.black));
        newFrame2.add(lnameLabel);

        locName = new JTextField();
        locName.setBorder(new LineBorder(Color.blue));
        locName.setBounds(350, 20, 300, 30);
        newFrame2.add(locName);

        helperMarkAsVisited();


        JButton doneButton = new JButton("Done");
        doneButton.setBounds(285, 300, 100, 30);//y=500
        doneButton.setActionCommand("done2");
        doneButton.addActionListener(this);
        doneButton.setBackground(Color.gray);
        doneButton.setBorder(new LineBorder(Color.black));
        newFrame2.add(doneButton);

        JButton bb = new JButton(" ");
        newFrame2.add(bb);

        newFrame2.setVisible(true);
    }

    //EFFECTS: helper method that creates JLabels that display details(name and visiting status) of locations in loc
    private void helperMarkAsVisited() {
        JLabel listOfLocationNames = new JLabel("Choose from given locations:");
        listOfLocationNames.setBounds(20, 70, 300, 30);
        //listOfLocationNames.setBorder(new LineBorder(Color.black));
        newFrame2.add(listOfLocationNames);

        int y = 120;

        List<Location> locations = loc.getLocations();
        if (locations.isEmpty()) {
            JLabel noLocation = new JLabel("No location to show.");
            noLocation.setBounds(20, y, 300, 20);
            //noLocation.setBorder(new LineBorder(Color.black));
            newFrame2.add(noLocation);
        } else {
            for (Location l : locations) {

                JLabel locationName = new JLabel(l.getName() + "     (with previous Visiting Status : "
                        + l.getVisitingStatus() + ")");
                locationName.setBounds(20, y, 500, 20);
                newFrame2.add(locationName);
                y += 30;

            }
        }

    }


    //MODIFIES:this,loc,location
    //EFFECTS:takes user's input to select a location from the locations in loc
    // and rate the selected Location according to user input
    public void changeRatingHelper() {
        newFrame3 = new JFrame("Change Location Rating Window");
        newFrame3.setSize(670, 500);//height"300
        newFrame3.setLocation(385, 250);

        //user input:Location name(from the list)
        JLabel lnameLabel = new JLabel("Enter selected location's name (from the list) :");
        lnameLabel.setBounds(20, 20, 300, 30);
        lnameLabel.setBorder(new LineBorder(Color.black));
        newFrame3.add(lnameLabel);

        locName = new JTextField();
        locName.setBorder(new LineBorder(Color.blue));
        locName.setBounds(350, 20, 300, 30);
        newFrame3.add(locName);

        //user input:Location rating
        JLabel lratingLabel = new JLabel("Enter location's rating (from 1 to 5) :");
        lratingLabel.setBounds(20, 70, 300, 30);
        lratingLabel.setBorder(new LineBorder(Color.black));
        newFrame3.add(lratingLabel);

        locRating = new JTextField();
        locRating.setBorder(new LineBorder(Color.blue));
        locRating.setBounds(350, 70, 300, 30);
        newFrame3.add(locRating);

        helperChangeRating();


        JButton bb = new JButton(" ");
        newFrame3.add(bb);


        newFrame3.setVisible(true);

    }

    //EFFECTS: helper method that creates JLabels that display details(name and previous rating) of locations in loc
    private void helperChangeRating() {
        JLabel listOfLocationNames = new JLabel("Choose from given locations:");
        listOfLocationNames.setBounds(20, 120, 300, 30);
        //listOfLocationNames.setBorder(new EtchedBorder(Color.black));
        //listOfLocationNames.setBorder(new LineBorder(Color.black));
        newFrame3.add(listOfLocationNames);

        int y = 160;

        List<Location> locations = loc.getLocations();
        if (locations.isEmpty()) {
            JLabel noLocation = new JLabel("No location to show");
            noLocation.setBounds(20, y, 300, 20);
            //noLocation.setBorder(new LineBorder(Color.black));
            newFrame3.add(noLocation);
        } else {
            for (Location l : locations) {

                JLabel locationName = new JLabel(l.getName() + "     (with previous Rating : "
                        + l.getRating() + ")");
                locationName.setBounds(20, y, 300, 20);
                newFrame3.add(locationName);
                y += 30;

            }
        }

        helperChangeRatingMakeDoneButton();


    }

    //EFFECTS: creates JButton (Done) that is added to newFrame3
    private void helperChangeRatingMakeDoneButton() {
        JButton doneButton = new JButton("Done");
        doneButton.setActionCommand("done3");
        doneButton.addActionListener(this);
        doneButton.setBackground(Color.gray);
        doneButton.setBorder(new LineBorder(Color.black));
        doneButton.setBounds(235, 400, 200, 30);//y=150
        newFrame3.add(doneButton);

    }


    //EFFECTS:saves loc to file
    public void saveHelper() {
        try {
            jsonWriter.open();
            jsonWriter.write(loc);
            jsonWriter.close();
            System.out.println("Saved " + loc.getCollectionName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }


    //MODIFIES:this,loc
    //EFFECTS: loads LocationCollection from file
    public void loadHelper() {
        try {
            loc = jsonReader.read();
            System.out.println("Loaded " + loc.getCollectionName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }

    //MODIFIES:this
    //EFFECTS: construct new JFrame that prints the details of all locations in loc
    public void makeDisplayLocationsFrame() {
        displayFrame = new JFrame("All locations in collection");
        displayFrame.setSize(450, 700);
        displayFrame.setLocation(300, 125);
        displayFrame.setBackground(Color.lightGray);
        //displayFrame.setLayout(new BorderLayout());

        newPanel = new JPanel();
        newPanel.setSize(700, 700);
        newPanel.setLocation(300, 125);
        newPanel.setBackground(Color.lightGray);

        JLabel allLocations = new JLabel("All locations in My Collection:");
        allLocations.setBounds(0, 0, 250, 30);
        allLocations.setBorder(new LineBorder(Color.black));
        newPanel.add(allLocations);

        helperMakeDisplayFrame();

        displayFrame.add(newPanel);
        displayFrame.setVisible(true);
    }

    //EFFECTS: helper method that prints the details of locations in loc as JLabels and adds them to newPanel
    private void helperMakeDisplayFrame() {
        int n = 1;
        int y = 40;
        List<Location> locations = loc.getLocations();
        if (locations.isEmpty()) {
            JLabel noLocation = new JLabel("No location to show.");
            noLocation.setBounds(30, y, 300, 20);
            newPanel.add(noLocation);
        } else {
            for (Location l : locations) {
                JLabel emptyLable = new JLabel("                                   ");
                newPanel.add(emptyLable);
                JLabel emptyLable1 = new JLabel("                                   ");
                newPanel.add(emptyLable1);
                JLabel locaLable = new JLabel(n + ". Name: " + l.getName() + "   , Type: " + l.getType()
                        + "   , Rating: " + l.getRating() + "   , Visiting Status: " + l.getVisitingStatus() + "!");
                locaLable.setBounds(30, y, 500, 20);
                newPanel.add(locaLable);
                y += 40;
                n += 1;

            }
        }
    }

    //MODIFIES:this
    //EFFECTS: clears out displayLocTextArea and appends strings to displayLocTextArea based on the locations in loc
    public void reloadTextAreaHelper() {
        displayLocTextArea.setText(null);

        int i = 1;
        int y = 40;

        List<Location> locations = new ArrayList<>();
        locations = loc.getLocations();
        if (locations.isEmpty()) {
            displayLocTextArea.append(s1 + "   No location to show        " + s2);
        } else {
            for (Location l : locations) {
                String s = "\n";
                displayLocTextArea.append(s);
                displayLocTextArea.append("  " + i + ".  Name: " + l.getName() + "   ,  Type: " + l.getType()
                        + "   ,  Rating: " + l.getRating() + "   ,  Visiting Status: " + l.getVisitingStatus() + "!  ");


                i += 1;

            }

        }

    }


}
