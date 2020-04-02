package sample;

import javafx.scene.paint.Color;

public class JCells extends Cells {

    public JCells(TetrisGame tetrisGame){
        super(tetrisGame,3, Color.LIGHTBLUE);
        cells[0][2].setStatus(true);
        cells[1][1].setStatus(true);
        cells[1][2].setStatus(true);
        cells[1][0].setStatus(true);
    }

}
