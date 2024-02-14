package ui;

import javax.swing.*;
import java.awt.*;


//Represents a JPanel that is added to MyFrame,contains graphic
public class MyPanel extends JPanel {
    Image image;

    //EFFECTS:Constructs a JPanel
    public MyPanel() {
        image = new ImageIcon("CPSClocation.jpg").getImage();
        this.setPreferredSize(new Dimension(500, 500));
    }

    //MODIFIES:this
    //EFFECTS:creates Graphic in panel
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(image, 0, 0, null);

    }


}
