package src.main.java.com.floorplanner;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ControlPanel extends JPanel {
    private Color panelColor;
    private int height;
    private int width;
    private int x;
    private int y;
    public MainPanel mPanel;
    private boolean isMousePressed = false;

    static Map<String, int[]> iconToBounds = new HashMap<>();

    static {
        iconToBounds.put("Room", new int[]{100,100});
        iconToBounds.put("Table", new int[]{54, 31});
        iconToBounds.put("Single Bed", new int[]{29, 54});
        iconToBounds.put("Double Bed", new int[]{46, 55});
        iconToBounds.put("Chair", new int[]{46, 46});
        iconToBounds.put("Cupboard", new int[]{46, 27});
        iconToBounds.put("Dining Set", new int[]{41, 41});
        iconToBounds.put("Sofa", new int[]{50, 28});
        iconToBounds.put("Small Sofa", new int[]{47, 38});
        iconToBounds.put("Big Sofa", new int[]{47, 33});
        iconToBounds.put("Bathtub", new int[]{27, 48});
        iconToBounds.put("Toilet", new int[]{32, 50});
        iconToBounds.put("Shower", new int[]{59, 58});
        iconToBounds.put("Sink", new int[]{54, 36});
        iconToBounds.put("Stove", new int[]{59, 59});
        iconToBounds.put("Kitchen Sink", new int[]{60, 34});
        iconToBounds.put("TV", new int[]{0, 0}); // Default size
    }

    public String[] essentials = new String[]{"Room", "Door", "Window"};
    public String[] essentialsLogos = new String[]{"src/main/resources/room-icon.png", "src/main/resources/door-symbol.png", "src/main/resources/room-icon.png"};

    public String[] furniture = new String[]{
            "Table", "Single Bed", "Double Bed",
            "Chair", "Cupboard", "Dining Set",
            "Sofa", "Small Sofa", "Big Sofa",
            "TV"
    };
    public String[] furnitureLogos = new String[]{ //need to add image path for TV
            "src/main/resources/table.png", "src/main/resources/single-bed.png", "src/main/resources/double-bed.png",
            "src/main/resources/chair.png", "src/main/resources/cupboard.png", "src/main/resources/diningset.png",
            "src/main/resources/sofa.png", "src/main/resources/small-sofa.png", "src/main/resources/big-sofa.png",
            "src/main/resources/room-icon.png"
    };
    public String[] fixtures = new String[]{
            "Bathtub", "Toilet", "Shower",
            "Sink", "Stove", "Kitchen Sink"
    };
    public String[] fixturesLogos = new String[]{
            "src/main/resources/bathtub.png", "src/main/resources/toilet.png", "src/main/resources/shower.png",
            "src/main/resources/sink.png", "src/main/resources/stove.png", "src/main/resources/kitchen-sink.png"
    };

    public ControlPanel() {
        this.panelColor = new Color(0xCDF8C9);
        this.width = 350;
        this.height = 700;
        this.x = 0;
        this.y = 100;
        mPanel = new MainPanel();
        this.setBounds(x, y, width, height);
        this.setBackground(panelColor);
        setUpLayout();
        setPreferredSize(new Dimension(350, 700));
    }

    private void setUpLayout() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // using BoxLayout with vertical stacking
        this.add(Box.createVerticalStrut(10)); // Add spacing above
        this.add(createCategoryPanel("Essentials", essentials, essentialsLogos));//need to add image path for window
        this.add(createCategoryPanel("Furniture", furniture, furnitureLogos));
        this.add(createCategoryPanel("Fixtures", fixtures, fixturesLogos));

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
            itemsPanel.add(new ButtonLabel(item, new ImageIcon(paths[i])).panel);

        }
        categoryPanel.add(itemsPanel, BorderLayout.CENTER);
        return categoryPanel;
    }

    private class ButtonLabel {
        private String item;
        private ImageIcon icon;
        private JButton button;
        private JPanel panel;

        public ButtonLabel(String item, ImageIcon icon) {
            this.item = item;
            this.icon = icon;
            this.panel = new JPanel();
            this.button = new JButton(icon);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setOpaque(false); // Transparent background
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setSize(new Dimension(60, 60));
            createButtonWithLabel(item, icon, button);

            // Transfer Handle Action
            button.setTransferHandler(new ElementTransferHandler(item));
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    isMousePressed = true;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    isMousePressed = false;
                }
            });

            button.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if(isMousePressed) {
                        JComponent comp = (JComponent) e.getSource();
                        TransferHandler handler = comp.getTransferHandler();
                        handler.exportAsDrag(comp, e, TransferHandler.COPY);
                    }
                }
            });
        }

        private JPanel createButtonWithLabel(String item, ImageIcon icon, JButton button) {
        
            button.addActionListener(e -> mPanel.triggerCustomPaint(200, 200, iconToBounds.get(item)[0], iconToBounds.get(item)[1], item));
            JLabel label = new JLabel(item, JLabel.CENTER);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            panel.add(button);
            panel.add(label);
            return panel;
        }
    }
}
