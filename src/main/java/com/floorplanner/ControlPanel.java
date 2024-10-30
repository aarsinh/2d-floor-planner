package src.main.java.com.floorplanner;

import java.awt.*;
import javax.swing.*;

public class ControlPanel extends JPanel {
    private Color panelColor;
    private int height;
    private int width;
    private int x;
    private int y;

    public ControlPanel() {
        this.panelColor = new Color(0xCDF8C9);
        this.width = 350;
        this.height = 700;
        this.x = 0;
        this.y = 100;
        this.setBounds(x, y, width, height);
        this.setBackground(panelColor);
        setUpLayout();
        setPreferredSize(new Dimension(350, 700));
    }
    
    private void setUpLayout() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // using BoxLayout with vertical stacking
        this.add(Box.createVerticalStrut(10)); // Add spacing above

        add(createCategoryPanel("Essentials", new String[]{"Square Room", "Door", "Window"}, new String[] {
            "src/main/resources/room-icon.png", "src/main/resources/door-symbol.png", "src/main/resources/window.png"
        }));//need to add image path for window
        
        add(createCategoryPanel("Furniture", new String[] {
                "Table", "Single Bed", "Double Bed",
                "Chair", "Cupboard", "Dining Set",
                "Sofa", "Small Sofa", "Big Sofa",
                "TV"
        }, new String[] { //need to add image path for TV
            "src/main/resources/table.png", "src/main/resources/single-bed.png", "src/main/resources/double-bed.png",
            "src/main/resources/chair.png", "src/main/resources/cupboard.png", "src/main/resources/diningset.png",
            "src/main/resources/sofa.png", "src/main/resources/small-sofa.png", "src/main/resources/big-sofa.png",
            "src/main/resources/room-icon.png"
        }));
        
        add(createCategoryPanel("Fixtures", new String[]{
                "Bathtub", "Toilet", "Shower",
                "Wash Basin", "Stove", "Kitchen Sink"
        }, new String[] { //need to add image path for bathtub
            "src/main/resources/bathtub.png", "src/main/resources/toilet.png", "src/main/resources/shower.png",
            "src/main/resources/sink.png", "src/main/resources/stove.png", "src/main/resources/kitchen-sink.png"
        }
        ));
    }

    private JPanel createCategoryPanel(String category, String[] items, String[] paths) {
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new BorderLayout());
        categoryPanel.setBackground(panelColor);

        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 24));
        categoryPanel.add(categoryLabel, BorderLayout.NORTH);

        JPanel itemsPanel = new JPanel(new GridLayout(0, 3));
        itemsPanel.setBackground(panelColor);
        int itemSize = items.length;
        for (int i = 0; i < itemSize; i++) {
            String item = items[i];
            itemsPanel.add(createButtonWithLabel(item, new ImageIcon(paths[i])));//paths[i]
        }
        categoryPanel.add(itemsPanel, BorderLayout.CENTER);
        return categoryPanel;
    }

    private JPanel createButtonWithLabel(String item, ImageIcon icon) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false); // Transparent background
        
        JButton button = new JButton(icon);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setSize(new Dimension(60, 60));
        button.addActionListener(e -> System.out.println(item + " button clicked"));

        JLabel label = new JLabel(item, JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(button);
        panel.add(label);
        return panel;
    }
}
