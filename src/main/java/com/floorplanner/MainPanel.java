package src.main.java.com.floorplanner;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import javax.swing.*;

public class MainPanel extends JPanel {
    private Color MpanelColor;
    private int height;
    private int width;
    private int x;
    private int y;
    private File currentFile;

    public MainPanel() {
        this.MpanelColor = new Color(0xD9D9D9);
        setBackground(MpanelColor);
        setLayout(null);

        setDropTarget(new DropTarget() {
           public synchronized void drop(DropTargetDropEvent event) {
               try {
                   event.acceptDrop(DnDConstants.ACTION_COPY);
                   String droppedItem = (String) event.getTransferable().getTransferData(DataFlavor.stringFlavor);
                   int dropX = event.getLocation().x;
                   int dropY = event.getLocation().y;
                   triggerCustomPaint(dropX, dropY,
                           ControlPanel.iconToBounds.get(droppedItem)[0], ControlPanel.iconToBounds.get(droppedItem)[1],
                           droppedItem);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
        });
    }

    private Room createNewRoom(int a, int b, int w, int h) {
        String[] roomTypes = {"Bedroom", "Bathroom", "Kitchen", "Living Room", "Dining Room"};
        String selectedRoomType = (String) JOptionPane.showInputDialog(
                this,
                "Select Room Type: ",
                "Room Type",
                JOptionPane.PLAIN_MESSAGE,
                null,
                roomTypes,
                roomTypes[0]
        );
        Room newRoom = new Room(a, b, w, h, "Room", selectedRoomType);
        return newRoom;
    }

    public void triggerCustomPaint(int a, int b, int w, int h, String type) {
        CanvasElement newElement = type.equals("Room") ?
                createNewRoom(a, b, w, h) :
                new CanvasElement(a, b, w, h, type);

        if(OverlapChecker.roomOverlap(newElement, a, b, type) ||
        OverlapChecker.borderOverlap(newElement, a, b)){
            JOptionPane.showMessageDialog(MainPanel.this,
                    "You cannot place overlapping objects.",
                    "Overlapping Objects",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            if(newElement instanceof Room) {
                CanvasElement.rooms.add((Room) newElement);
            } else {
                CanvasElement.elements.add(newElement);
            }
            this.add(newElement);
            if(newElement instanceof Room) {
                this.setComponentZOrder(newElement, this.getComponentCount() - 1);
            } else {
                this.setComponentZOrder(newElement, 0);
            }
        }
        this.revalidate();
        this.repaint();
    }

    public void loadElementsFromFile(String filename) {
        File file = new File(filename);
        this.currentFile = file;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<CanvasElement> elementsList = (List<CanvasElement>) obj;
                if (elementsList.isEmpty()) {
                    System.out.println("No elements found in the file.");
                } else {
                    System.out.println("Loaded " + elementsList.size() + " elements from file.");
                    
                    this.removeAll(); // Clear existing components
                    CanvasElement.elements.clear();
                    CanvasElement.rooms.clear();


                    for (CanvasElement element : elementsList) {
                        if (element instanceof Room) {
                            CanvasElement.rooms.add((Room) element);
                        } else {
                            CanvasElement.elements.add(element);
                        }
                        setupElement(element);
                    }
                }
            } else {
                System.out.println("File content is not a valid list.");
            }
        } catch (EOFException e) {
            System.out.println("Reached the end of file unexpectedly.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load layout.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupElement(CanvasElement element) {
        element.setBounds(element.getX(), element.getY(), element.getWidth(), element.getHeight());
        if(!(element instanceof Room)) {
            element.setIcon(element.getType());
        }
        element.addMouseListeners();
        this.add(element);
        this.revalidate();
        this.repaint();
    }

    public File getCurrentFile() { return currentFile; }
}