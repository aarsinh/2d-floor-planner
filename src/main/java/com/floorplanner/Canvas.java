package src.main.java.com.floorplanner;
import java.awt.*;
import javax.swing.*;


class Canvas extends JFrame {
    private ControlPanel controlPanel;
    private SavePanel savePanel;
    public Canvas() {
        setTitle("2D Floor Planer");
        this.controlPanel = new ControlPanel();
        this.savePanel = new SavePanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.WEST);
        add(controlPanel.mPanel, BorderLayout.CENTER);
        add(savePanel, BorderLayout.EAST);

        //m.setLayout(null);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        //this.savePanel = new SavePanel();
    }
}
