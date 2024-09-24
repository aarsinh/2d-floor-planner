package src.main.java.com.floorplanner;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private Color panelColor;
    private int height;
    private int width;

    public ControlPanel() {
        this.panelColor = new Color(170, 170, 170);
        this.width = 350;
        this.height = 700;
        initializePanel();
        addButtons();
    }

    private void initializePanel() {
        this.setSize(width, height);
        this.setBackground(panelColor);
        this.setLayout(null);
    }

    private void addButtons() {
        ImageIcon addRoomIcon = new ImageIcon("src/main/resources/room-icon.png");
        
        JButton addRoomButton = new JButton(addRoomIcon);
        addRoomButton.setBounds(50, 50, 60, 60);
        addRoomButton.addActionListener(e -> System.out.println("Button 1 clicked"));
        this.add(addRoomButton);

        JButton addDoorButton = new JButton("Add Door");
        addDoorButton.setBounds(160, 50, 60, 60);
        addDoorButton.addActionListener(e -> System.out.println("Button 2 clicked"));
        this.add(addDoorButton);

        JButton addWindowButton = new JButton("Add Window");
        addWindowButton.setBounds(270, 50, 60, 60);
        addWindowButton.addActionListener(e -> System.out.println("Button 3 clicked"));
        this.add(addWindowButton);

        JButton addTableButton = new JButton("Add Table");
        addTableButton.setBounds(50, 160, 60, 60);
        addTableButton.addActionListener(e -> System.out.println("Button 4 clicked"));
        this.add(addTableButton);

        JButton addSofaButton = new JButton("Add Sofa");
        addSofaButton.setBounds(160, 160, 60, 60);
        addSofaButton.addActionListener(e -> System.out.println("Button 5 clicked"));
        this.add(addSofaButton);

        JButton addSingleBedButton = new JButton("Add Single Bed");
        addSingleBedButton.setBounds(270, 160, 60, 60);
        addSingleBedButton.addActionListener(e -> System.out.println("Button 6 clicked"));
        this.add(addSingleBedButton);

        JButton addDoubleBedButton = new JButton("Add Double Bed");
        addDoubleBedButton.setBounds(50, 270, 60, 60);
        addDoubleBedButton.addActionListener(e -> System.out.println("Button 7 clicked"));
        this.add(addDoubleBedButton);

        JButton addChairButton = new JButton("Add Chair");
        addChairButton.setBounds(50, 380, 60, 60);
        addChairButton.addActionListener(e -> System.out.println("Button 8 clicked"));
        this.add(addChairButton);

        JButton addToiletButton = new JButton("Add Toilet");
        addToiletButton.setBounds(160, 380, 60, 60);
        addToiletButton.addActionListener(e -> System.out.println("Button 9 clicked"));
        this.add(addToiletButton);

        JButton addShowerButton = new JButton("Add Shower");
        addShowerButton.setBounds(270, 380, 60, 60);
        addShowerButton.addActionListener(e -> System.out.println("Button 10 clicked"));
        this.add(addShowerButton);

        JButton addSinkButton = new JButton("Add Sink");
        addSinkButton.setBounds(50, 490, 60, 60);
        addSinkButton.addActionListener(e -> System.out.println("Button 11 clicked"));
        this.add(addSinkButton);

        JButton addStoveButton = new JButton("Add Stove");
        addStoveButton.setBounds(160, 490, 60, 60);
        addStoveButton.addActionListener(e -> System.out.println("Button 12 clicked"));
        this.add(addStoveButton);

        JButton addKitchenSinkButton = new JButton("Add Kitchen Sink");
        addKitchenSinkButton.setBounds(160, 490, 60, 60);
        addKitchenSinkButton.addActionListener(e -> System.out.println("Button 13 clicked"));
        this.add(addKitchenSinkButton);

    }
}   