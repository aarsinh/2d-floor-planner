package src.main.java.com.floorplanner;

import javax.sql.rowset.spi.SyncResolver;
import java.awt.Rectangle;
import java.lang.annotation.ElementType;

class OverlapChecker{

    //returns true if newRoom overlaps with one of the rooms
    public static boolean roomOverlap(CanvasElement newElement, int x, int y) {
        Rectangle newBounds = new Rectangle(x, y, newElement.getWidth(), newElement.getHeight());
        for(CanvasElement e : CanvasElement.elements) {
            if(e != newElement && e.getBounds().intersects(newBounds)){
                return true;
            }
        }
        return false;
    }
}