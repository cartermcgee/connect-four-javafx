import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disk {

    //creates a default disk
    public Disk(int row, int col) {
        this.row = row;
        this.col = col;
        circle = new Circle(30);
        color = Color.WHITE;
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(3);
    }

    public Circle getCircle() {
        return circle;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    private Circle circle;
    private final int col;
    private final int row;
    private Color color;
}
