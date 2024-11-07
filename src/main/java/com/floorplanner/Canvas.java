package src.main.java.com.floorplanner;
import java.awt.*;

import javax.swing.*;

class Canvas extends JFrame {
    private ControlPanel controlPanel;
    private SavePanel savePanel;
    private MiddlePanel middlePanel;

    public Canvas() {  
        setTitle("2D Floor Planer");
        this.controlPanel = new ControlPanel();
        this.savePanel = new SavePanel();
        this.middlePanel = new MiddlePanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        controlPanel.setBounds(0, 0, 350, 1000);
        savePanel.setBounds(1720, 200, 200, 600);
        middlePanel.setBounds(450, 80, 1200, 840);
        
        add(controlPanel);
        add(middlePanel);
        add(savePanel);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
    }
}
