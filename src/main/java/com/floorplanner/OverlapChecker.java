package src.main.java.com.floorplanner;

import javax.sql.rowset.spi.SyncResolver;
import java.awt.Rectangle;

class OverlapChecker{

    //returns true if newRoom overlaps with one of the rooms
    public static boolean roomOverlap(Room newRoom, int x, int y) {
        Rectangle newBounds = new Rectangle(x, y, newRoom.getHeight(), newRoom.getWidth());
        System.out.println("Number of rooms: " + Room.rooms.size());
        for(Room r : Room.rooms) {
            if(r != newRoom && r.getBounds().intersects(newBounds)){
                return true;
            }
        }
        return false;
    }
}