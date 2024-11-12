package src.main.java.com.floorplanner;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

class CanvasElement extends JPanel {
    private int x, y, width, height;
    private boolean isDragging = false;
    private boolean isResizing = false;
    private int dragOffsetX, dragOffsetY;
    private static final int RESIZE_MARGIN = 10;
    private int originalHeight, originalWidth;
    private int startX, startY;

    private String iconPath;
    static List<CanvasElement> elements = new ArrayList<>();

    public CanvasElement(int x1, int y1, int h, int w) {
        this.x = x1;
        this.y = y1;
        this.height = h;
        this.width = w;

        setOpaque(false);
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
                if(OverlapChecker.roomOverlap(CanvasElement.this, getX(), getY())) {
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

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public int getElemX() {
        return x;
    }

    public int getElemY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private boolean isResizeRegion(int x, int y) {
        return ((x - width) * (x - width) +
                (y - height) * (y - height) <= RESIZE_MARGIN * RESIZE_MARGIN);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawRect(0, 0, width - 1, height - 1);
    }
}
