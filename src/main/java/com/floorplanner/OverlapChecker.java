package src.main.java.com.floorplanner;

import javax.sql.rowset.spi.SyncResolver;
import java.awt.Rectangle;
import java.lang.annotation.ElementType;
import java.util.Objects;

class OverlapChecker{

    public static boolean roomOverlap(CanvasElement newElement, int x, int y, String type) {
        Rectangle newBounds = new Rectangle(x, y, newElement.getWidth(), newElement.getHeight());

        // creates an Iterable of any type that is a subclass of CanvasElement
        // and sets it to either the rooms list or elements list(contains everything but rooms) to iterate over in the foreach loop
        Iterable<? extends CanvasElement> elementsToCheck = type.equals("Room") ? CanvasElement.rooms : CanvasElement.elements;
        for (CanvasElement e : elementsToCheck) {
            if (e != newElement && e.getBounds().intersects(newBounds)) {
                return true;
            }
        }
        return false;
    }
}