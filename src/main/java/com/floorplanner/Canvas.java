package src.main.java.com.floorplanner;
import javax.swing.*;

class Canvas extends JFrame {
    public void FloorPlanner() {
        ControlPanel controlPanel = new ControlPanel();

        JFrame frame = new JFrame("2D Floor Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(controlPanel);
    }
}