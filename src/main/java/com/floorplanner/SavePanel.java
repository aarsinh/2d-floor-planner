package src.main.java.com.floorplanner;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import javax.swing.*;

//import javafx.scene.layout.GridPane;

public class SavePanel extends JPanel {
    private Color panelColor;
    private int height;
    private int width;
    private int x;
    private int y;
    private MainPanel mainPanel;
    private File currentFile;
    public SavePanel(MainPanel mainPanel) {
        this.panelColor = new Color(0xC4C4C4);
        this.width = 200;
        this.height = 700;
        this.x = 1000;
        this.y = 200;
        this.mainPanel = mainPanel;
        initializePanel();
        setUpLayout();
    }   

    private void initializePanel() {
        this.setBounds(x, y, width, height);
        this.setBackground(panelColor);
        this.setLayout(new GridLayout(2, 0));
        this.setPreferredSize(new Dimension(200, 400));
    }

    private void setUpLayout() {
        add(createButtonWithLabel("Save", new ImageIcon("src/main/resources/save.png"), e -> saveAction()));
        add(createButtonWithLabel("Load File", new ImageIcon("src/main/resources/load.png"), e -> loadAction()));
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
        currentFile = mainPanel.getCurrentFile();
        if (currentFile != null) {
            saveElementsToFile(currentFile);
        } else {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showSaveDialog(mainPanel);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                saveElementsToFile(file);
            }
        }
    }

    private void saveElementsToFile(File file) {
        List<CanvasElement> elementsList = new ArrayList<>();
        elementsList.addAll(CanvasElement.elements);
        elementsList.addAll(CanvasElement.rooms);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            if (elementsList.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "No elements to save.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                oos.writeObject(elementsList);
                JOptionPane.showMessageDialog(mainPanel, "Layout saved successfully!");
                System.out.println("File size in bytes: " + file.length());
                System.out.println(String.format("Saved %d elements to file.", elementsList.size()));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainPanel, "Failed to save layout.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    void loadAction() {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(mainPanel);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            CanvasElement.elements.clear();
            mainPanel.loadElementsFromFile(file.getAbsolutePath());
        }
    }
}