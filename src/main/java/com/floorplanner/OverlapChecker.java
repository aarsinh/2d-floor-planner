package src.main.java.com.floorplanner;

import javax.sql.rowset.spi.SyncResolver;
import java.awt.Rectangle;
import java.lang.annotation.ElementType;
import java.util.Objects;

class OverlapChecker{

    public static boolean roomOverlap(CanvasElement newElement, int x, int y, String type) {
        Rectangle newBounds = new Rectangle(x, y, newElement.getWidth(), newElement.getHeight());

        // Iterable<? extends CanvasElement>
        if(type.equals("Room")) {
            for (Room r : CanvasElement.rooms) {
                if (r != newElement && r.getBounds().intersects(newBounds)) {
                    return true;
                }
            }
        } else if(!type.equals("Room")){
            Rectangle currentBounds = new Rectangle(x, y, ControlPanel.iconToBounds.get(type)[0], ControlPanel.iconToBounds.get(type)[1]);
            for (CanvasElement e : CanvasElement.elements){
                Rectangle eBounds = new Rectangle(e.getX(), e.getY(), ControlPanel.iconToBounds.get(e.getType())[0], ControlPanel.iconToBounds.get(e.getType())[1]);
                if (e != newElement && eBounds.intersects(currentBounds)) {
                    return true;
                }
            }
        }
        return false;
    }
}