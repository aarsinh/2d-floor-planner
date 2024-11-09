package main.java.com.floorplanner;

import java.awt.Rectangle;

class OverlapChecker{

    //returns true if newRoom overlaps with one of the rooms
    public static boolean roomOverlap(Room newRoom, int x, int y){
        Rectangle newBounds = new Rectangle(x, y, newRoom.getHeight(), newRoom.getWidth());
        for(Room r : Room.rooms){
            if(r!=newRoom && r.getBounds().intersects(newBounds)){
                return true;
            }
        }
        return false;
    }

        
}