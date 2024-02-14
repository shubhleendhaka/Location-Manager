
package ui;

import java.io.FileNotFoundException;

// creates an instance for LocationManager application
public class Main {

    //EFFECTS:runs the GUI Location Manager application
    public static void main(String[] args) {
        new GuiLocationManager();

        //For console based application:
/*        try {
            new LocationManagerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: File not found");
        }

 */


    }
}