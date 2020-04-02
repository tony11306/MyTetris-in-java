package sample;

import javafx.scene.paint.Color;

public class OCells extends Cells {


    public OCells(TetrisGame tetrisGame){
        super(tetrisGame,2,Color.DARKSALMON);
        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0; j < CELLS_SIZE; j++) {
                cells[i][j].setStatus(true);
            }
        }
    }
    @Override
    public void rotate() {

    }

    @Override
    public void spawn() {
        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0, k = CELLS_SIZE+2; j < CELLS_SIZE; j++, k++) {
                tetrisGame.cellFillIn(cells[i][j], k, i+2);
            }

        }

    }
}
