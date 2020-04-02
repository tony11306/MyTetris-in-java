package sample;

import javafx.scene.paint.Color;

public class ICells extends Cells{

    public ICells(TetrisGame tetrisGame){
        super(tetrisGame,4, Color.BLUE);
        for (int i = 0; i < 4; i++) {
            cells[1][i].setStatus(true);
        }
    }

    /*
        Rotating rule is according to SRS table.
        Only right rotate.

                  I Tetromino Wall Kick Data
            Test 1	Test 2	Test 3	Test 4	Test 5
    0->R	( 0, 0)	(-2, 0)	(+1, 0)	(-2,-1)	(+1,+2)
    R->0	( 0, 0)	(+2, 0)	(-1, 0)	(+2,+1)	(-1,-2)
    R->2	( 0, 0)	(-1, 0)	(+2, 0)	(-1,+2)	(+2,-1)
    2->R	( 0, 0)	(+1, 0)	(-2, 0)	(+1,-2)	(-2,+1)
    2->L	( 0, 0)	(+2, 0)	(-1, 0)	(+2,+1)	(-1,-2)
    L->2	( 0, 0)	(-2, 0)	(+1, 0)	(-2,-1)	(+1,+2)
    L->0	( 0, 0)	(+1, 0)	(-2, 0)	(+1,-2)	(-2,+1)
    0->L	( 0, 0)	(-1, 0)	(+2, 0)	(-1,+2)	(+2,-1)

    * */
    @Override
    public void rotate() {
        int currentX;
        int currentY;
        boolean[][] tempStatus=new boolean[CELLS_SIZE][CELLS_SIZE];

        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0; j < CELLS_SIZE; j++) {
                currentX = cells[i][j].getCurrentX();
                currentY = cells[i][j].getCurrentY();
                tempStatus[i][j]=cells[i][j].getStatus();
                tetrisGame.cellRemove(cells[i][j],currentX,currentY);
                cells[i][j].setCurrentX(currentX);
                cells[i][j].setCurrentY(currentY);
            }
        }
        switch (currentRotateState) {
            case "0":
                if(isRotatable(0,0)){
                    System.out.println("0:Test1成功");
                    rotateAndFillIn(0,0,tempStatus);
                    return;

                }
                else if(isRotatable(-2,0)){
                    System.out.println("0:Test2成功");
                    rotateAndFillIn(-2,0,tempStatus);
                    return;

                }
                else if(isRotatable(1,0)){
                    System.out.println("0:Test3成功");
                    rotateAndFillIn(1,0,tempStatus);
                    return;

                }
                else if(isRotatable(-2,1)){
                    System.out.println("0:Test4成功");
                    rotateAndFillIn(-2,1,tempStatus);
                    return;

                }
                else if(isRotatable(1,-2)){
                    System.out.println("0:Test5成功");
                    rotateAndFillIn(1,-2,tempStatus);
                    return;
                }

                break;
            case "R":
                if (isRotatable(0,0)){
                    System.out.println("R:Test1成功");
                    rotateAndFillIn(0,0,tempStatus);
                    return;

                }
                else if(isRotatable(-1,0)){
                    System.out.println("R:Test2成功");
                    rotateAndFillIn(-1,0,tempStatus);
                    return;

                }
                else if(isRotatable(2,0)){
                    System.out.println("R:Test3成功");
                    rotateAndFillIn(2,0,tempStatus);
                    return;

                }
                else if(isRotatable(-1,-2)){
                    System.out.println("R:Test4成功");
                    rotateAndFillIn(-1,-2,tempStatus);
                    return;

                }
                else if(isRotatable(2,1)){
                    System.out.println("R:Test5成功");
                    rotateAndFillIn(2,1,tempStatus);
                    return;

                }

                break;
            case "2":
                if (isRotatable(0,0)){
                    System.out.println("2:Test1成功");
                    rotateAndFillIn(0,0,tempStatus);
                    return;

                }
                else if(isRotatable(2,0)){
                    System.out.println("2:Test2成功");
                    rotateAndFillIn(2,0,tempStatus);
                    return;

                }
                else if(isRotatable(-1,0)){
                    System.out.println("2:Test3成功");
                    rotateAndFillIn(-1,0,tempStatus);
                    return;

                }
                else if(isRotatable(2,-1)){
                    System.out.println("2:Test4成功");
                    rotateAndFillIn(2,-1,tempStatus);
                    return;

                }
                else if(isRotatable(-1,2)){
                    System.out.println("2:Test5成功");
                    rotateAndFillIn(-1,2,tempStatus);
                    return;

                }

                break;
            case "L":
                if (isRotatable(0,0)){
                    System.out.println("L:Test1成功");
                    rotateAndFillIn(0,0,tempStatus);
                    return;

                }
                else if(isRotatable(1,0)){
                    System.out.println("L:Test2成功");
                    rotateAndFillIn(1,0,tempStatus);
                    return;

                }
                else if(isRotatable(-2,0)){
                    System.out.println("L:Test3成功");
                    rotateAndFillIn(-2,0,tempStatus);
                    return;

                }
                else if(isRotatable(1,2)){
                    System.out.println("L:Test4成功");
                    rotateAndFillIn(1,2,tempStatus);
                    return;

                }
                else if(isRotatable(-2,-1)){
                    System.out.println("L:Test5成功");
                    rotateAndFillIn(-2,-1,tempStatus);
                    return;

                }

                break;
        }
        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0; j < CELLS_SIZE; j++) {
                cells[i][j].setStatus(tempStatus[i][j]);
                tetrisGame.cellFillIn(cells[i][j],cells[i][j].getCurrentX(),cells[i][j].getCurrentY());
            }
        }

    }

    @Override
    public void spawn() {
        for (int i = 0; i < CELLS_SIZE; i++) {
        for (int j = 0, k = CELLS_SIZE-1; j < CELLS_SIZE; j++, k++) {
            tetrisGame.cellFillIn(cells[i][j], k, i+1);
        }

    }

}

}