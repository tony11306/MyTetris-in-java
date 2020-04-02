package sample;
import javafx.scene.paint.Color;

public class Cell{
    private boolean status;
    private int currentX;
    private int currentY;
    public final Color color;

    public Cell(Color color) {
        this.color = color;
        status = false;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void goDown(Cell cell , TetrisGame tetrisGame){
        int afterX = currentX;
        int afterY = currentY;
        afterY++;
        tetrisGame.cellRemove(cell, currentX,currentY);
        tetrisGame.cellFillIn(cell, afterX, afterY);

    }

    public void goLeft(Cell cell , TetrisGame tetrisGame){
        int afterX = currentX;
        int afterY = currentY;
        afterX--;
        tetrisGame.cellRemove(cell, currentX,currentY);
        tetrisGame.cellFillIn(cell, afterX, afterY);

    }

    public void goRight(Cell cell , TetrisGame tetrisGame){
        int afterX = currentX;
        int afterY = currentY;
        afterX++;
        tetrisGame.cellRemove(cell, currentX,currentY);
        tetrisGame.cellFillIn(cell, afterX, afterY);
    }


}
