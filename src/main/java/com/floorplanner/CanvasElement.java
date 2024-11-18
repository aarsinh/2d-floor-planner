package src.main.java.com.floorplanner;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.*;
import java.util.Map;

class CanvasElement extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int x, y, width, height;
    private String type;
    private boolean isDragging = false;
    private boolean isResizing = false;
    private int dragOffsetX, dragOffsetY;
    private static final int RESIZE_MARGIN = 10;
    private int originalHeight, originalWidth;
    private int startX, startY;

    private String iconPath;
    private transient ImageIcon icon;
    static List<CanvasElement> elements = new ArrayList<>();
    static List<Room> rooms = new ArrayList<>();

    private static final Map<String, String> typeToIconPath = new HashMap<>();

    static {
        typeToIconPath.put("Room", null);
        typeToIconPath.put("Door", "src/main/resources/door-symbol.png");
        typeToIconPath.put("Window", "src/main/resources/window.png");
        typeToIconPath.put("Table", "src/main/resources/table.png");
        typeToIconPath.put("Single Bed", "src/main/resources/single-bed.png");
        typeToIconPath.put("Double Bed", "src/main/resources/double-bed.png");
        typeToIconPath.put("Chair", "src/main/resources/chair.png");
        typeToIconPath.put("Cupboard", "src/main/resources/cupboard.png");
        typeToIconPath.put("Dining Set", "src/main/resources/diningset.png");
        typeToIconPath.put("Sofa", "src/main/resources/sofa.png");
        typeToIconPath.put("Small Sofa", "src/main/resources/small-sofa.png");
        typeToIconPath.put("Big Sofa", "src/main/resources/big-sofa.png");
        typeToIconPath.put("Bathtub", "src/main/resources/bathtub.png");
        typeToIconPath.put("Toilet", "src/main/resources/toilet.png");
        typeToIconPath.put("Shower", "src/main/resources/shower.png");
        typeToIconPath.put("Sink", "src/main/resources/sink.png");
        typeToIconPath.put("Stove", "src/main/resources/stove.png");
        typeToIconPath.put("Kitchen Sink", "src/main/resources/kitchen-sink.png");
        typeToIconPath.put("TV", "src/main/resources/tv.png");
    }

    public CanvasElement(int x1, int y1, int h, int w, String type) {
        this.x = x1;
        this.y = y1;
        this.height = h;
        this.width = w;
        this.type = type;
        setOpaque(false);
        setIcon(type);
        setBounds(x, y, width + RESIZE_MARGIN, height + RESIZE_MARGIN);
        startX = getX();
        startY = getY();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isResizeRegion(e.getX(), e.getY())) {
                    isResizing = true;
                    originalWidth = width;
                    originalHeight = height;
                    startX = e.getXOnScreen();
                    startY = e.getYOnScreen();
                    System.out.println(startX);
                    System.out.println(startY);
                }
                else if (e.getX() >= 0 && e.getX() <= width &&
                        e.getY() >= 0 && e.getY() <= height) {
                    isDragging = true;
                    dragOffsetX = e.getX();
                    dragOffsetY = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
                isResizing = false;
                if(OverlapChecker.roomOverlap(CanvasElement.this, getX(), getY(), CanvasElement.this.type)) {
                    JOptionPane.showMessageDialog(null,
                            "You cannot place overlapping objects.",
                            "Overlapping Objects",
                            JOptionPane.WARNING_MESSAGE);
                    setLocation(startX, startY);
                } else {
                    startX = getX();
                    startY = getY();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isResizing) {
                    int deltaWidth = e.getXOnScreen() - startX;
                    int deltaHeight = e.getYOnScreen() - startY;

                    width = Math.max(50, originalWidth + deltaWidth);
                    height = Math.max(50, originalHeight + deltaHeight);
                    setBounds(getX(), getY(), width + RESIZE_MARGIN, height + RESIZE_MARGIN);
                    revalidate();
                    repaint();
                } else if (isDragging) {
                    int newX = getX() + e.getX() - dragOffsetX;
                    int newY = getY() + e.getY() - dragOffsetY;
                    setLocation(newX, newY);
                }
            }
        });
    }

    private void setIcon(String type) {
        this.iconPath = typeToIconPath.get(type);
        if(iconPath != null) {
            this.icon = new ImageIcon(iconPath);
        }
    }

    public int getElemX() { return x; }

    public int getElemY() { return y; }

    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public String getType() { return type; }
    private boolean isResizeRegion(int x, int y) {
        return ((x - width) * (x - width) +
                (y - height) * (y - height) <= RESIZE_MARGIN * RESIZE_MARGIN);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (icon != null) {
            icon.paintIcon(this, g, 0, 0);
        } else if (type.equals("Room")) {
            super.paintComponent(g);
            Room room = (Room) this;
            // Cast to Graphics2D for advanced features
            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Room.getRoomColor(room.getRoomType()));
            g2d.fillRect(0, 0 , width, height);
            // Draw the main blue rectangle border
            g2d.setColor(Color.BLUE);
            g2d.drawRect(0, 0, width - 1, height - 1);

            // Add a thicker or secondary border
            g2d.setStroke(new BasicStroke(3)); // Set thickness to 3 pixels
            g2d.setColor(Color.DARK_GRAY); // Set a secondary border color
            g2d.drawRect(1, 1, width - 3, height - 3); // Inset by 1 pixel
        }
    }



}
