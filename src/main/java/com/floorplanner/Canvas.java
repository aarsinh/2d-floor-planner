package src.main.java.com.floorplanner;
import javax.swing.*;
import java.awt.*;

public class Canvas extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Floor Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setVisible(true);

        JPanel controlPanel = new JPanel();
        Color panelColor = new Color(170, 170, 170);
        controlPanel.setBackground(panelColor);
        controlPanel.setBounds(0, 0, 368, 582);
        controlPanel.setBackground(null);
    }
}