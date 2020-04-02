package sample;

import javafx.scene.paint.Color;

public class ZCells extends Cells{

    public ZCells(TetrisGame tetrisGame){
        super(tetrisGame,3,Color.RED);

        cells[0][0].setStatus(true);
        cells[0][1].setStatus(true);
        cells[1][1].setStatus(true);
        cells[1][2].setStatus(true);
    }

}
