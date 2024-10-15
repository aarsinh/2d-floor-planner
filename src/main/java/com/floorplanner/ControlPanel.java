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
        setUpButtons();
    }

    private void initializePanel() {
        this.setSize(width, height);
        this.setBackground(panelColor);
        this.setLayout(null);
    }

    private void setUpButtons() {
        ImageIcon addRoomIcon = new ImageIcon("src/main/resources/room-icon.png");
        JButton addRoomButton = new JButton(addRoomIcon);
        addRoomButton.setBounds(50, 50, 60, 60);
        addRoomButton.addActionListener(e -> System.out.println("Button 1 clicked"));
        this.add(addRoomButton);

        ImageIcon addDoorIcon = new ImageIcon("src/main/resources/door-symbol.png");
        JButton addDoorButton = new JButton(addDoorIcon);
        addDoorButton.setBounds(160, 50, 60, 60);
        addDoorButton.addActionListener(e -> System.out.println("Button 2 clicked"));
        this.add(addDoorButton);

        //ImageIcon addWindowIcon = new ImageIcon("src/main/resources/window-icon.png");
        JButton addWindowButton = new JButton("Add Window");
        addWindowButton.setBounds(270, 50, 60, 60);
        addWindowButton.addActionListener(e -> System.out.println("Button 3 clicked"));
        this.add(addWindowButton);

        ImageIcon addTableIcon = new ImageIcon("src/main/resources/table.png");
        JButton addTableButton = new JButton(addTableIcon);
        addTableButton.setBounds(50, 160, 60, 60);
        addTableButton.addActionListener(e -> System.out.println("Button 4 clicked"));
        this.add(addTableButton);

        ImageIcon addSofaIcon = new ImageIcon("src/main/resources/sofa.png");
        JButton addSofaButton = new JButton(addSofaIcon);
        addSofaButton.setBounds(160, 160, 60, 60);
        addSofaButton.addActionListener(e -> System.out.println("Button 5 clicked"));
        this.add(addSofaButton);

        ImageIcon addSingleBedIcon = new ImageIcon("src/main/resources/single-bed.png");
        JButton addSingleBedButton = new JButton(addSingleBedIcon);
        addSingleBedButton.setBounds(270, 160, 60, 60);
        addSingleBedButton.addActionListener(e -> System.out.println("Button 6 clicked"));
        this.add(addSingleBedButton);

        ImageIcon addDoubleBedIcon = new ImageIcon("src/main/resources/double-bed.png");
        JButton addDoubleBedButton = new JButton(addDoubleBedIcon);
        addDoubleBedButton.setBounds(50, 270, 60, 60);
        addDoubleBedButton.addActionListener(e -> System.out.println("Button 7 clicked"));
        this.add(addDoubleBedButton);

        ImageIcon addChairIcon = new ImageIcon("src/main/resources/chair.png");
        JButton addChairButton = new JButton(addChairIcon);
        addChairButton.setBounds(50, 380, 60, 60);
        addChairButton.addActionListener(e -> System.out.println("Button 8 clicked"));
        this.add(addChairButton);

        ImageIcon addToiletIcon = new ImageIcon("src/main/resources/toilet.png");
        JButton addToiletButton = new JButton(addToiletIcon);
        addToiletButton.setBounds(160, 380, 60, 60);
        addToiletButton.addActionListener(e -> System.out.println("Button 9 clicked"));
        this.add(addToiletButton);

        ImageIcon addShowerIcon = new ImageIcon("src/main/resources/shower.png");
        JButton addShowerButton = new JButton(addShowerIcon);
        addShowerButton.setBounds(270, 380, 60, 60);
        addShowerButton.addActionListener(e -> System.out.println("Button 10 clicked"));
        this.add(addShowerButton);

        ImageIcon addSinkIcon = new ImageIcon("src/main/resources/sink.png");
        JButton addSinkButton = new JButton(addSinkIcon);
        addSinkButton.setBounds(50, 490, 60, 60);
        addSinkButton.addActionListener(e -> System.out.println("Button 11 clicked"));
        this.add(addSinkButton);

        ImageIcon addStoveIcon = new ImageIcon("src/main/resources/stove.png");
        JButton addStoveButton = new JButton(addStoveIcon);
        addStoveButton.setBounds(160, 490, 60, 60);
        addStoveButton.addActionListener(e -> System.out.println("Button 12 clicked"));
        this.add(addStoveButton);

        ImageIcon addKitchenSinkIcon = new ImageIcon("src/main/resources/kitchen-sink.png");
        JButton addKitchenSinkButton = new JButton(addKitchenSinkIcon);
        addKitchenSinkButton.setBounds(270, 490, 60, 60);
        addKitchenSinkButton.addActionListener(e -> System.out.println("Button 13 clicked"));
        this.add(addKitchenSinkButton);

        ImageIcon addCupboardIcon = new ImageIcon("src/main/resources/cupboard.png");
        JButton addCupboardButton = new JButton(addCupboardIcon);
        addCupboardButton.setBounds(50, 570, 60, 60);
        addCupboardButton.addActionListener(e -> System.out.println("Button 14 clicked"));
        this.add(addCupboardButton);
    }
}   