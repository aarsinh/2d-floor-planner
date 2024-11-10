package src.main.java.com.floorplanner;

import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel{
    
    private Color MpanelColor;
    private int height;
    private int width;
    private int x;
    private int y;
    
    public MainPanel(){
        this.MpanelColor = new Color(0,0,204);
        setBackground(MpanelColor);
        setLayout(null);
    }    
    public void triggerCustomPaint(int a, int b, int w, int h){
        Room currentRoom = new Room(a, b, w, h); // Position it at (50, 50) with a width and height of 200.
        if(!OverlapChecker.roomOverlap(currentRoom, a, b)){
            Room.rooms.add(currentRoom);
        }

        this.add(currentRoom);
        this.revalidate();
        this.repaint();
    }

}
