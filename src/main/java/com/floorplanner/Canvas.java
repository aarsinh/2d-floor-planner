package src.main.java.com.floorplanner;
import com.sun.tools.javac.Main;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.*;


class Canvas extends JFrame {
    private ControlPanel controlPanel;
    public static Stack<CanvasElement> undoStack = new Stack<>();
    private MainPanel mainPanel;
    private File currentFile;

    public Canvas() {
        setTitle("2D Floor Planner");
        this.controlPanel = new ControlPanel();
        this.mainPanel = controlPanel.mPanel;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.WEST);
        add(controlPanel.mPanel, BorderLayout.CENTER);
        JToolBar toolBar = new JToolBar();
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        saveButton.addActionListener(e -> saveAction());
        loadButton.addActionListener(e -> loadAction());
        add(toolBar, BorderLayout.NORTH);
        toolBar.add(saveButton);
        toolBar.add(loadButton);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
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
