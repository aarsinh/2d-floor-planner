package src.main.java.com.floorplanner;

class Furniture extends CanvasElement {
    int x, y, width, height;
    String type;


    public Furniture(int x1, int y1, int h, int w, String type) {
        super(x1, y1, h, w, type);
    }
}
