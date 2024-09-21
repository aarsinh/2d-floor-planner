package src.main.java.com.floorplanner;

import java.awt.Color;
import java.util.List;

public class Room {
    int x;
    int y;
    int width;
    int height;
    String type;
    Color color;
    List<Furniture> furnitureList;
    List<Door> doors;
    List<Window> windows;
    List<Fixture> fixtures;
}