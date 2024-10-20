package src.main.java.com.floorplanner;

import javax.swing.*;
import java.awt.*;

public class SavePanel extends JPanel {
    private Color panelColor;
    private int height;
    private int width;
    private int x;
    private int y;

    public SavePanel() {
        this.panelColor = new Color(0xD9D9D9);
        this.width = 350;
        this.height = 700;
        this.x = 0;
        this.y = 100;
        initializePanel();
        setUpLayout();
    }

    private void initializePanel() {
        this.setBounds(x, y, width, height);
        this.setBackground(panelColor);
        this.setLayout(new GridLayout(4, 0));
        this.setPreferredSize(new Dimension(200, 700));
    }

    private void setUpLayout() {
        add(createButtonWithLabel("Save", new ImageIcon("src/main/resources/room-icon.png")));
        add(createButtonWithLabel("Undo", new ImageIcon("src/main/resources/room-icon.png")));
        add(createButtonWithLabel("Rotate", new ImageIcon("src/main/resources/room-icon.png")));
        add(createButtonWithLabel("Load File", new ImageIcon("src/main/resources/room-icon.png")));
    }

    private JPanel createButtonWithLabel(String item, ImageIcon icon) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // Transparent background
        
        JButton button = new JButton(icon);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setSize(new Dimension(200, 150));
        button.addActionListener(e -> System.out.println(item + " button clicked"));

        JLabel label = new JLabel(item, JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 26));

        panel.add(button);
        panel.add(label);
        return panel;
    }

}