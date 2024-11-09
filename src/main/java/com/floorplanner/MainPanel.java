package main.java.com.floorplanner;

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
        // this.width = 350;
        // this.height = 700;
        // this.x = 0;
        // this.y = 100;
        // this.setBounds(x, y, width, height);
        setLayout(new BorderLayout());
        //setPreferredSize(new Dimension(500, 700));
    }    
    // @Override
    // public Dimension getPreferredSize(){
    //     return new Dimension(500, 500);
    // }
    public void triggerCustomPaint(int a, int b, int w, int h){
        Room room = new Room(a, b, w, h); // Position it at (50, 50) with a width and height of 200.
        this.add(room);
        this.revalidate();
        this.repaint();
    }

}
