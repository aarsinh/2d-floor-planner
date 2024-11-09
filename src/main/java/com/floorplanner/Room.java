package main.java.com.floorplanner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class Room extends JPanel{
    int x;
    int y;
    int width;
    int height;
    private boolean isDragging = false;
    private int dragOffsetX, dragOffsetY;
    String type;
    Color color;
    List<Furniture> furnitureList;
    List<Door> doors;
    List<Window> windows;
    List<Fixture> fixtures;

    public Room(int x1, int y1, int width1, int height1){
        this.x=x1;
        this.y=y1;
        this.width=width1;
        this.height=height1;
        setOpaque(false);
        //setBounds(x, y, width, height);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Check if the click is inside the rectangle
                if (e.getX() >= x && e.getX() <= x + width && e.getY() >= y && e.getY() <= y + height) {
                    isDragging = true;
                    dragOffsetX = e.getX() - x;
                    dragOffsetY = e.getY() - y;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    // Update rectangle position based on mouse drag
                    x = e.getX() - dragOffsetX;
                    y = e.getY() - dragOffsetY;
                    repaint(); // Request re-painting with new position
                }
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);  // Set the color for the square
        g.drawRect(x, y, width, height);
    }

}