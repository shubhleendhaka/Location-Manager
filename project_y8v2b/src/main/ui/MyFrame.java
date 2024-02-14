package ui;


import javax.swing.*;
import java.awt.*;


// Represents a JFrame that contains graphic-picture
//it is setVisible(true) when user completes a task (e.g. adds Location to LocationCollection)
public class MyFrame extends JFrame {
    MyPanel panel;

    //MODIFIES:this
    //EFFECTS:constructs a JFrame and adds panel to it
    public MyFrame() {
        panel = new MyPanel();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(panel);
        this.pack();
        this.setSize(500, 400);
        this.setVisible(false);
    }
}
