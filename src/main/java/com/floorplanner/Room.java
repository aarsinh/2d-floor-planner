package src.main.java.com.floorplanner;

import java.awt.*;
import java.io.Serializable;

public class Room extends CanvasElement {
    private String roomType;
    private Color roomColor;
    public Room(int x1, int y1, int h, int w, String type, String roomType) {
        super(x1, y1, h, w, type);
        this.roomType = roomType;
        this.roomColor = getRoomColor(roomType);
    }

    public static Color getRoomColor(String roomType) {
        return switch (roomType) {
            case "Bedroom" -> Color.GREEN;
            case "Bathroom" -> Color.CYAN;
            case "Kitchen" -> Color.RED;
            case "Living Room" -> Color.YELLOW;
            case "Dining Room" -> Color.ORANGE;
            default -> Color.GRAY;
        };
    }

    public String getRoomType() {
        return roomType;
    }
}