package src.main.java.com.floorplanner;
import javax.swing.*;

class Canvas extends JFrame {
    private ControlPanel controlPanel;
    
    public void FloorPlanner() {
        this.controlPanel = new ControlPanel();
        JFrame frame = new JFrame("2D Floor Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(controlPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
    }
}