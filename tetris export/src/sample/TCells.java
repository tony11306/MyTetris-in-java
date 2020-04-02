package sample;

import javafx.scene.paint.Color;

public class TCells extends Cells{

    /*
        initial
        □■□     □■□     □□□     □■□
        ■■■ ->  □■■ ->  ■■■ ->  ■■□
        □□□     □■□     □■□     □■□

        0->R	( 0, 0)	(-1, 0)	(-1,+1)	( 0,-2)	(-1,-2)
        R->2	( 0, 0)	(+1, 0)	(+1,-1)	( 0,+2)	(+1,+2)
        2->L	( 0, 0)	(+1, 0)	(+1,+1)	( 0,-2)	(+1,-2)
        L->0	( 0, 0)	(-1, 0)	(-1,-1)	( 0,+2)	(-1,+2)
    */

    public TCells(TetrisGame tetrisGame){
        super(tetrisGame,3,Color.PURPLE);

        cells[0][1].setStatus(true);
        cells[1][0].setStatus(true);
        cells[1][1].setStatus(true);
        cells[1][2].setStatus(true);

    }




}









