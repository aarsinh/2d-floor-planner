package src.main.java.com.floorplanner;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.util.Stack;
import javax.swing.*;

public class MainPanel extends JPanel {
    private Color MpanelColor;
    private int height;
    private int width;
    private int x;
    private int y;
    private final Stack<CanvasElement> undoStack = new Stack<>();

    public MainPanel(){
        this.MpanelColor = new Color(0,0,204);
        setBackground(MpanelColor);
        setLayout(null);

        setDropTarget(new DropTarget() {
           public synchronized void drop(DropTargetDropEvent event) {
               try {
                   event.acceptDrop(DnDConstants.ACTION_COPY);
                   String droppedItem = (String) event.getTransferable().getTransferData(DataFlavor.stringFlavor);
                   int dropX = event.getLocation().x;
                   int dropY = event.getLocation().y;
                   triggerCustomPaint(dropX, dropY, 100, 100);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
        });
    }

    public void triggerCustomPaint(int a, int b, int w, int h){
        CanvasElement currentRoom = new CanvasElement(a, b, h, w); // Position it at (50, 50) with a width and height of 200.
        if(OverlapChecker.roomOverlap(currentRoom, a, b)){
            JOptionPane.showMessageDialog(MainPanel.this,
                    "You cannot place overlapping objects.",
                    "Overlapping Objects",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            CanvasElement.elements.add(currentRoom);
            undoStack.push(currentRoom);
            System.out.println("DEBUG: Undo stack: " + undoStack);
            this.add(currentRoom);
        }
        this.revalidate();
        this.repaint();
    }

    public void undo() {
        System.out.println("DEBUG: Undo stack in the function: "+ undoStack);
        System.out.println("DEBUG:" + undoStack.isEmpty());
        if(!undoStack.isEmpty()) {
            System.out.println("Undo stack not empty");
            CanvasElement lastElement = undoStack.pop();
            CanvasElement.elements.remove(lastElement);
            this.remove(lastElement);
            this.revalidate();
            this.repaint();
        } else {
            System.out.println("Undo stack is empty");
            JOptionPane.showMessageDialog(this, "No action to undo", "Undo", JOptionPane.WARNING_MESSAGE);
        }
    }
}