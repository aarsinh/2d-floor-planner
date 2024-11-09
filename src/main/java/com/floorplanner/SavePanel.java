package main.java.com.floorplanner;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

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
        add(createButtonWithLabel("Save", new ImageIcon("src/main/resources/room-icon.png"), e -> saveAction()));
        add(createButtonWithLabel("Undo", new ImageIcon("src/main/resources/room-icon.png"), e -> undo()));
        add(createButtonWithLabel("Rotate", new ImageIcon("src/main/resources/room-icon.png"), e -> rotate()));
        add(createButtonWithLabel("Load File", new ImageIcon("src/main/resources/room-icon.png"), e -> loadAction()));
    }

    private JPanel createButtonWithLabel(String item, ImageIcon icon, ActionListener actionListener) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // Transparent background
        
        JButton button = new JButton(icon);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setSize(new Dimension(200, 150));
        button.addActionListener(actionListener);

        JLabel label = new JLabel(item, JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 26));

        panel.add(button);
        panel.add(label);
        return panel;
    }

    void saveAction() {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showSaveDialog(this);
        if(response == JFileChooser.APPROVE_OPTION) {
            // Save the file
        }
    }

    void loadAction() {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(this);
        if(response == JFileChooser.APPROVE_OPTION) {
            // Load the file
        }
    }

    void rotate() {
        // Rotate action
    }

    void undo() {
        // Undo action
    }   
}