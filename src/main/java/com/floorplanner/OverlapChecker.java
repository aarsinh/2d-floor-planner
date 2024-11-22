package src.main.java.com.floorplanner;

import javax.sql.rowset.spi.SyncResolver;
import java.awt.Rectangle;
import java.lang.annotation.ElementType;
import java.util.Objects;

class OverlapChecker{

    public static boolean roomOverlap(CanvasElement newElement, int x, int y, String type) {
        Rectangle newBounds = new Rectangle(x, y, newElement.getWidth(), newElement.getHeight());

        Iterable<? extends CanvasElement> elementsToCheck = type.equals("Room") ? CanvasElement.rooms : CanvasElement.elements;
        for (CanvasElement e : elementsToCheck) {
            if (e != newElement && e.getBounds().intersects(newBounds)) {
                return true;
            }
        }
        return false;
    }

    public static boolean borderOverlap(CanvasElement e, int x, int y) {
        Rectangle newBounds = new Rectangle(x, y, e.getWidth(), e.getHeight());
        if(!e.getType().equals("Room")) {
            for (Room r : CanvasElement.rooms) {
                if(r.getBounds().intersects(newBounds) && !r.getBounds().contains(newBounds)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}