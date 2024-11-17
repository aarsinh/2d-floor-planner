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
import java.util.Stack;
import javax.swing.*;

public class MainPanel extends JPanel {
    private Color MpanelColor;
    private int height;
    private int width;
    private int x;
    private int y;
    private File currentFile;

    public MainPanel(){
        this.MpanelColor = new Color(0xFFFBFB);
        setBackground(MpanelColor);
        setLayout(null);

        setDropTarget(new DropTarget() {
           public synchronized void drop(DropTargetDropEvent event) {
               try {
                   event.acceptDrop(DnDConstants.ACTION_COPY);
                   String droppedItem = (String) event.getTransferable().getTransferData(DataFlavor.stringFlavor);
                   int dropX = event.getLocation().x;
                   int dropY = event.getLocation().y;
                   triggerCustomPaint(dropX, dropY, 100, 100, droppedItem);
               } catch (Exception ex) {
                   ex.printStackTrace();
               }
           }
        });
    }

    public void triggerCustomPaint(int a, int b, int w, int h, String type) {
        CanvasElement currentRoom = new CanvasElement(a, b, h, w, type); // Position it at (50, 50) with a width and height of 200.
        if(OverlapChecker.roomOverlap(currentRoom, a, b)){
            JOptionPane.showMessageDialog(MainPanel.this,
                    "You cannot place overlapping objects.",
                    "Overlapping Objects",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            CanvasElement.elements.add(currentRoom);
            Canvas.undoStack.push(currentRoom);
            System.out.println("DEBUG: Undo stack: " + Canvas.undoStack);
            this.add(currentRoom);
        }
        this.revalidate();
        this.repaint();
    }

    public void loadElementsFromFile(String filename) {
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
                    for (CanvasElement element : elementsList) {
                        // Create label or component for the element
                        CanvasElement.elements.add(element);
                        JLabel label = new JLabel(getIconForItem(element.getType()));
                        label.setBounds(element.getElemX(), element.getElemY(), element.getWidth(), element.getHeight());

                        // Add the element to the panel
                        this.add(label);

                        // Reapply interactivity: add mouse listeners for drag or resize
                        label.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                // Handle dragging or interaction
                                System.out.println("Element clicked: " + element);
                            }
                        });

                        label.addMouseMotionListener(new MouseAdapter() {
                            @Override
                            public void mouseDragged(MouseEvent e) {
                                // Handle element dragging logic
                                int newX = e.getX() + label.getX();
                                int newY = e.getY() + label.getY();
                                label.setLocation(newX, newY);
                                repaint();
                            }
                        });
                    }
                    this.revalidate();  // Revalidate layout after adding elements
                    this.repaint();     // Refresh the panel to reflect changes
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

    private ImageIcon getIconForItem(String itemName) {
        switch (itemName) {
            case "Square Room": return new ImageIcon("src/main/resources/room-icon.png");
            case "Door": return new ImageIcon("src/main/resources/door-symbol.png");
            case "Window": return new ImageIcon("src/main/resources/window.png");
            case "Table": return new ImageIcon("src/main/resources/table.png");
            case "Single Bed": return new ImageIcon("src/main/resources/single-bed.png");
            case "Double Bed": return new ImageIcon("src/main/resources/double-bed.png");
            case "Chair": return new ImageIcon("src/main/resources/chair.png");
            case "Cupboard": return new ImageIcon("src/main/resources/cupboard.png");
            case "Dining Set": return new ImageIcon("src/main/resources/diningset.png");
            case "Sofa": return new ImageIcon("src/main/resources/sofa.png");
            case "Small Sofa": return new ImageIcon("src/main/resources/small-sofa.png");
            case "Big Sofa": return new ImageIcon("src/main/resources/big-sofa.png");
            case "TV": return new ImageIcon("src/main/resources/room-icon.png");
            case "Bathtub": return new ImageIcon("src/main/resources/bathtub.png");
            case "Toilet": return new ImageIcon("src/main/resources/toilet.png");
            case "Shower": return new ImageIcon("src/main/resources/shower.png");
            case "Wash Basin": return new ImageIcon("src/main/resources/sink.png");
            case "Stove": return new ImageIcon("src/main/resources/stove.png");
            case "Kitchen Sink": return new ImageIcon("src/main/resources/kitchen-sink.png");
            default: return null;
        }
    }
}