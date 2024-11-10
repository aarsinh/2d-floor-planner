package src.main.java.com.floorplanner;

import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel {
    private Color MpanelColor;
    private int height;
    private int width;
    private int x;
    private int y;

    public MainPanel(){
        this.MpanelColor = new Color(0,0,204);
        setBackground(MpanelColor);
        setLayout(null);
    }

    public void triggerCustomPaint(int a, int b, int w, int h){
        CanvasElement currentRoom = new CanvasElement(a, b, w, h); // Position it at (50, 50) with a width and height of 200.
        if(OverlapChecker.roomOverlap(currentRoom, a, b)){
            JOptionPane.showMessageDialog(MainPanel.this,
                    "You cannot place overlapping objects.",
                    "Overlapping Objects",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            CanvasElement.elements.add(currentRoom);
            this.add(currentRoom);
        }
        this.revalidate();
        this.repaint();
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.RED);
//        for(CanvasElement element: CanvasElement.elements) {
//            g.drawRect(element.getElemX(), element.getElemY(), element.getHeight(), element.getWidth());
//        }
//    }
//
//    public void reloadElements() {
//        this.removeAll();
//        for(CanvasElement element : CanvasElement.elements) {
//            element.setBounds(element.getElemX(), element.getElemY(),
//                    element.getWidth(), element.getHeight());
//            this.add(element);
//        }
//        this.revalidate();
//        this.repaint();
//    }
}