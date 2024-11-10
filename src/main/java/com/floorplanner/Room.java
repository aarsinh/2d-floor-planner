package src.main.java.com.floorplanner;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class Room extends CanvasElement {
    public Room(int x1, int y1, int h, int w) {
        super(x1, y1, h, w);
        super.setIconPath("src/main/java/com/resources/room-icon.png");
    }
}