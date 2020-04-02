package sample;

import javafx.scene.paint.Color;

public class LCells extends Cells {

    public LCells(TetrisGame tetrisGame){
        super(tetrisGame,3, Color.ORANGE);
        cells[0][0].setStatus(true);
        cells[1][0].setStatus(true);
        cells[1][1].setStatus(true);
        cells[1][2].setStatus(true);

    }

}
