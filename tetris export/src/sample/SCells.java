package sample;

import javafx.scene.paint.Color;

public class SCells extends Cells {

    public SCells(TetrisGame tetrisGame){
        super(tetrisGame,3,Color.GREEN);

        cells[0][1].setStatus(true);
        cells[0][2].setStatus(true);
        cells[1][0].setStatus(true);
        cells[1][1].setStatus(true);

    }


}
