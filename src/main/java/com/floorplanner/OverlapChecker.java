package src.main.java.com.floorplanner;

import javax.sql.rowset.spi.SyncResolver;
import java.awt.Rectangle;
import java.lang.annotation.ElementType;
import java.util.Objects;

class OverlapChecker{

    public static boolean roomOverlap(CanvasElement newElement, int x, int y, String type) {
        Rectangle newBounds;
        Iterable<? extends CanvasElement> elementsToCheck = type.equals("Room") ? CanvasElement.rooms : CanvasElement.elements;
        for (CanvasElement e : elementsToCheck) {
            if (type.equals("Room") && e.getType().equals("Room")) {
                newBounds = new Rectangle(x + 5, y + 5, newElement.getWidth() - 10, newElement.getHeight() - 10);
                Rectangle existingBounds = new Rectangle(e.getX() + 5, e.getY() + 5, e.getWidth() - 10, e.getHeight() - 10);
                if (e != newElement && existingBounds.intersects(newBounds)) {
                    return true;
                }
            } else {
                newBounds = new Rectangle(x, y, newElement.getWidth(), newElement.getHeight());
                if (e != newElement && e.getBounds().intersects(newBounds)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean borderOverlap(CanvasElement e, int x, int y) {
        Rectangle newBounds = new Rectangle(x, y, e.getWidth(), e.getHeight());
        if(!(e.getType().equals("Room") || e.getType().equals("Door") || e.getType().equals("Window"))) {
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