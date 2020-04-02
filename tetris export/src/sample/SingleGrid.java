package sample;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.*;

public class SingleGrid {
    private boolean gridStatus;
    private Rectangle rectangle = new Rectangle();
    public final Color initColor;
    private Color currentColor;
    private Color currentStrokeColor;

    public SingleGrid(Color initialColor){
        initColor = initialColor;
        currentColor = initialColor;
        currentStrokeColor = initialColor;
        gridStatus = false;

        rectangle.setHeight(40);
        rectangle.setWidth(40);
        rectangle.setFill(initColor);
        rectangle.setStrokeWidth(1);
        rectangle.setStroke(Color.GREY);
        rectangle.setStrokeType(StrokeType.CENTERED);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(1);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean getGridStatus() {
        return gridStatus;
    }

    public void setGridStatus(boolean gridStatus) {
        this.gridStatus = gridStatus;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
        rectangle.setFill(currentColor);
    }

    public Color getCurrentStrokeColor() {

        return currentStrokeColor;

    }

    public void setCurrentStrokeColor(Color currentStrokeColor) {
        this.currentStrokeColor = currentStrokeColor;
        rectangle.setStroke(currentStrokeColor);
    }
}
