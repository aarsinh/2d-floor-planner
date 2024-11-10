package src.main.java.com.floorplanner;

import java.awt.*;
import java.awt.event.ActionListener;
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
    private MainPanel mainPanel = new MainPanel();

    public SavePanel() {
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
        this.setLayout(new GridLayout(4, 0));
        this.setPreferredSize(new Dimension(200, 400));
    }

    private void setUpLayout() {
        add(createButtonWithLabel("Save", new ImageIcon("src/main/resources/save.png"), e -> saveAction()));
        add(createButtonWithLabel("Undo", new ImageIcon("src/main/resources/undo.png"), e -> undo()));
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
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showSaveDialog(this);
        if(response == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileToSave))) {
                oos.writeObject(CanvasElement.elements);
                JOptionPane.showMessageDialog(this, "Plan saved successfully.");
            } catch(IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error saving the plan.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void loadAction() {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(this);
        if(response == JFileChooser.APPROVE_OPTION) {
            // LOADING WORKS BUT REPAINTING OF MAINPANEL IS NOT WORKING
            File fileToLoad = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileToLoad))) {
                CanvasElement.elements = (List<CanvasElement>) ois.readObject();
                System.out.println(CanvasElement.elements);
//                mainPanel.reloadElements();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    void rotate() {
        // Rotate action
    }

    void undo() {
        // Undo action
    }   
}