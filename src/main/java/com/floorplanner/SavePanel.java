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
        this.mainPanel = mainPanel;
        this.panelColor = new Color(0xD9D9D9);
        this.width = 200;
        this.height = 700;
        this.x = 1000;
        this.y = 200;
        initializePanel();
        setUpLayout();
    }   

    private void initializePanel() {
        this.setBounds(x, y, width, height);
        this.setBackground(panelColor);
        this.setLayout(new GridLayout(3, 0));
        this.setPreferredSize(new Dimension(200, 400));
    }

    private void setUpLayout() {
        add(createButtonWithLabel("Save", new ImageIcon("src/main/resources/save.png"), e -> saveAction()));
        add(createButtonWithLabel("Rotate", new ImageIcon("src/main/resources/rotate.png"), e -> rotate()));
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
        // Check if a file has already been selected (loaded or saved previously)
        currentFile = mainPanel.getCurrentFile();
        if (currentFile != null) {
            //If a file is already loaded, append to it without JFileChooser
            try {
                // Load existing elements from the file
                List<CanvasElement> savedElementsList = new ArrayList<>();
                if (currentFile.exists()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(currentFile))) {
                        Object obj = ois.readObject();
                        if (obj instanceof List<?>) {
                            savedElementsList = (List<CanvasElement>) obj;
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(this, "Failed to load existing elements.", "Error", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                        return;
                    }
                }

                // Add new elements to the loaded list
                // List<CanvasElement> newElements = CanvasElement.elements; 
                // savedElementsList.addAll(newElements);
                // Avoid duplicate additions to savedElementsList
                for (CanvasElement element : CanvasElement.elements) {
                    if (!savedElementsList.contains(element)) {
                        savedElementsList.add(element);
                    }
                }

                // Save the updated list back to the file
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentFile))) {
                    if (savedElementsList.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No elements to save.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        oos.writeObject(savedElementsList);
                        JOptionPane.showMessageDialog(this, "Layout saved successfully!");
                        System.out.println("File size in bytes: " + currentFile.length());
                        System.out.println(String.format("Saved %d elements to file.", savedElementsList.size()));
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Failed to save layout.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            //No file specified yet, so show JFileChooser for the initial area
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showSaveDialog(this);
            if (response == JFileChooser.APPROVE_OPTION) {
                // Get the file chosen by the user
                File file = fileChooser.getSelectedFile();
                
                // Check if the file exists and load existing elements if necessary
                List<CanvasElement> savedElementsList = new ArrayList<>();
                if (file.exists()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        Object obj = ois.readObject();
                        if (obj instanceof List<?>) {
                            savedElementsList = (List<CanvasElement>) obj;
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        JOptionPane.showMessageDialog(this, "Failed to load existing elements.", "Error", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                        return; // Exit the method if loading fails
                    }
                }
                
                // Add new elements to the list
                List<CanvasElement> newElements = CanvasElement.elements; // Assuming this is a static list of CanvasElement objects
                savedElementsList.addAll(newElements); // Append new elements to the existing list
                
                // Save the updated list back to the file
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    if (savedElementsList.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No elements to save.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        oos.writeObject(savedElementsList); // Serialize the list of CanvasElements
                        JOptionPane.showMessageDialog(this, "Layout saved successfully!");
                        System.out.println("File size in bytes: " + file.length());
                        System.out.println(String.format("Saved %d elements to file.", savedElementsList.size()));
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Failed to save layout.", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        }
    }

    void loadAction() {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(this);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            CanvasElement.elements.clear();
            
            mainPanel.loadElementsFromFile(file.getAbsolutePath());
        }
    }

    void rotate() {
        // Rotate action
    }
}