package src.main.java.com.floorplanner;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;
import javax.swing.*;

public class MiddlePanel extends JPanel {
    private Color panelColor;

    public MiddlePanel() {
        this.panelColor = new Color(0x9C504E);
        this.setBounds(450, 250, 400, 400);
        this.setBackground(panelColor);
        // drop functionality
        this.setTransferHandler(new IconTransferHandler());
    }

    private class IconTransferHandler extends TransferHandler {
        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            if (!canImport(support))
                return false;
            try {
                String itemName = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                ImageIcon imageIcon = getIconForItem(itemName);
                JLabel droppedImageLabel = new JLabel(imageIcon);
                droppedImageLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());

                // bounds for dropped image
                int x = Math.min(getWidth() - imageIcon.getIconWidth(), 0);
                int y = Math.min(getHeight() - imageIcon.getIconHeight(), 0);
                droppedImageLabel.setLocation(x, y);

                // resizing
                DragResizeHandler resizeHandler = new DragResizeHandler(droppedImageLabel);
                droppedImageLabel.addMouseListener(resizeHandler);
                droppedImageLabel.addMouseMotionListener(resizeHandler);

                add(droppedImageLabel);
                revalidate();
                repaint();
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }

        private ImageIcon getIconForItem(String itemName) {
            switch (itemName) {
                case "Square Room":
                    return new ImageIcon("src/main/resources/room-icon.png");
                case "Door":
                    return new ImageIcon("src/main/resources/door-symbol.png");
                case "Window":
                    return new ImageIcon("src/main/resources/window.png");
                case "Table":
                    return new ImageIcon("src/main/resources/table.png");
                case "Single Bed":
                    return new ImageIcon("src/main/resources/single-bed.png");
                case "Double Bed":
                    return new ImageIcon("src/main/resources/double-bed.png");
                case "Chair":
                    return new ImageIcon("src/main/resources/chair.png");
                case "Cupboard":
                    return new ImageIcon("src/main/resources/cupboard.png");
                case "Dining Set":
                    return new ImageIcon("src/main/resources/diningset.png");
                case "Sofa":
                    return new ImageIcon("src/main/resources/sofa.png");
                case "Small Sofa":
                    return new ImageIcon("src/main/resources/small-sofa.png");
                case "Big Sofa":
                    return new ImageIcon("src/main/resources/big-sofa.png");
                case "TV":
                    return new ImageIcon("src/main/resources/room-icon.png");
                case "Bathtub":
                    return new ImageIcon("src/main/resources/bathtub.png");
                case "Toilet":
                    return new ImageIcon("src/main/resources/toilet.png");
                case "Shower":
                    return new ImageIcon("src/main/resources/shower.png");
                case "Wash Basin":
                    return new ImageIcon("src/main/resources/sink.png");
                case "Stove": 
                    return new ImageIcon("src/main/resources/stove.png");
                case "Kitchen Sink": 
                    return new ImageIcon("src/main/resources/kitchen-sink.png");    
            }
            return null;
        }
    }

    private class DragResizeHandler extends MouseAdapter {
        private final JComponent component;
        private Point offset;
        private boolean isResizing = false;
        private final int RESIZE_MARGIN = 8;

        public DragResizeHandler(JComponent component) {
            this.component = component;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Point point = e.getPoint();
            int width = component.getWidth();
            int height = component.getHeight();

            if (point.x >= width - RESIZE_MARGIN && point.y >= height - RESIZE_MARGIN) {
                isResizing = true;
            } else {
                isResizing = false;
                offset = e.getPoint(); // records initial click position for dragging
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (isResizing) {
                int newWidth = Math.min(getWidth() - component.getX(), e.getX());
                int newHeight = Math.min(getHeight() - component.getY(), e.getY());

                newWidth = Math.max(newWidth, RESIZE_MARGIN);
                newHeight = Math.max(newHeight, RESIZE_MARGIN);
                component.setSize(newWidth, newHeight);
            } else if (offset != null) {
                Point newLocation = component.getLocation();
                newLocation.translate(e.getX() - offset.x, e.getY() - offset.y);
                newLocation.x = Math.max(newLocation.x, 0);
                newLocation.y = Math.max(newLocation.y, 0);
                newLocation.x = Math.min(newLocation.x, getWidth() - component.getWidth());
                newLocation.y = Math.min(newLocation.y, getHeight() - component.getHeight());

                component.setLocation(newLocation);
                MiddlePanel.this.repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            offset = null;
            isResizing = false;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int width = component.getWidth();
            int height = component.getHeight();
            if (e.getX() >= width - RESIZE_MARGIN && e.getY() >= height - RESIZE_MARGIN) {
                component.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
            } else {
                component.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }
        }
    }
}