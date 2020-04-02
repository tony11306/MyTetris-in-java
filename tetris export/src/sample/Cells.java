package sample;

import javafx.scene.paint.Color;

public abstract class Cells{

    protected TetrisGame tetrisGame;
    protected Color color;
    protected Cell[][] cells;
    protected final int CELLS_SIZE;
    protected final String[] rotateState = {"0", "R", "2", "L"};
    protected String currentRotateState;

    protected Cells(TetrisGame tetrisGame, int CELLS_SIZE, Color color){
        cells = new Cell[CELLS_SIZE][CELLS_SIZE];
        currentRotateState = rotateState[0];

        this.CELLS_SIZE = CELLS_SIZE;
        this.tetrisGame = tetrisGame;

        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0; j < CELLS_SIZE; j++) {
                cells[i][j] = new Cell(color);
            }
        }
    }


    /*
        Rotating rule is according to SRS table.
        Only right rotate.

        J, L, S, T, Z Tetromino Wall Kick Data
        ----------------------------------------
        	Test 1	Test 2	Test 3	Test 4	Test 5
    0->R	( 0, 0)	(-1, 0)	(-1,+1)	( 0,-2)	(-1,-2)
    R->0	( 0, 0)	(+1, 0)	(+1,-1)	( 0,+2)	(+1,+2)
    R->2	( 0, 0)	(+1, 0)	(+1,-1)	( 0,+2)	(+1,+2)
    2->R	( 0, 0)	(-1, 0)	(-1,+1)	( 0,-2)	(-1,-2)
    2->L	( 0, 0)	(+1, 0)	(+1,+1)	( 0,-2)	(+1,-2)
    L->2	( 0, 0)	(-1, 0)	(-1,-1)	( 0,+2)	(-1,+2)
    L->0	( 0, 0)	(-1, 0)	(-1,-1)	( 0,+2)	(-1,+2)
    0->L	( 0, 0)	(+1, 0)	(+1,+1)	( 0,-2)	(+1,-2)
    * */
    public void rotate() {
        System.out.println(currentRotateState);

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
                else if(isRotatable(-1,0)){
                    System.out.println("0:Test2成功");
                    rotateAndFillIn(-1,0,tempStatus);
                    return;

                }
                else if(isRotatable(-1,-1)){
                    System.out.println("0:Test3成功");
                    rotateAndFillIn(-1,-1,tempStatus);
                    return;

                }
                else if(isRotatable(0,2)){
                    System.out.println("0:Test4成功");
                    rotateAndFillIn(0,2,tempStatus);
                    return;

                }
                else if(isRotatable(-1,2)){
                    System.out.println("0:Test5成功");
                    rotateAndFillIn(-1,2,tempStatus);
                    return;
                }

                break;
            case "R":
                if (isRotatable(0,0)){
                    System.out.println("R:Test1成功");
                    rotateAndFillIn(0,0,tempStatus);
                    return;

                }
                else if(isRotatable(1,0)){
                    System.out.println("R:Test2成功");
                    rotateAndFillIn(1,0,tempStatus);
                    return;

                }
                else if(isRotatable(1,1)){
                    System.out.println("R:Test3成功");
                    rotateAndFillIn(1,1,tempStatus);
                    return;

                }
                else if(isRotatable(0,-2)){
                    System.out.println("R:Test4成功");
                    rotateAndFillIn(0,-2,tempStatus);
                    return;

                }
                else if(isRotatable(1,-2)){
                    System.out.println("R:Test5成功");
                    rotateAndFillIn(1,-2,tempStatus);
                    return;

                }

                break;
            case "2":
                if (isRotatable(0,0)){
                    System.out.println("2:Test1成功");
                    rotateAndFillIn(0,0,tempStatus);
                    return;

                }
                else if(isRotatable(1,0)){
                    System.out.println("2:Test2成功");
                    rotateAndFillIn(1,0,tempStatus);
                    return;

                }
                else if(isRotatable(1,-1)){
                    System.out.println("2:Test3成功");
                    rotateAndFillIn(1,-1,tempStatus);
                    return;

                }
                else if(isRotatable(0,2)){
                    System.out.println("2:Test4成功");
                    rotateAndFillIn(0,2,tempStatus);
                    return;

                }
                else if(isRotatable(1,2)){
                    System.out.println("2:Test5成功");
                    rotateAndFillIn(1,2,tempStatus);
                    return;

                }

                break;
            case "L":
                if (isRotatable(0,0)){
                    System.out.println("L:Test1成功");
                    rotateAndFillIn(0,0,tempStatus);
                    return;

                }
                else if(isRotatable(-1,0)){
                    System.out.println("L:Test2成功");
                    rotateAndFillIn(-1,0,tempStatus);
                    return;

                }
                else if(isRotatable(-1,1)){
                    System.out.println("L:Test3成功");
                    rotateAndFillIn(-1,1,tempStatus);
                    return;

                }
                else if(isRotatable(0,-2)){
                    System.out.println("L:Test4成功");
                    rotateAndFillIn(0,-2,tempStatus);
                    return;

                }
                else if(isRotatable(-1,-2)){
                    System.out.println("L:Test5成功");
                    rotateAndFillIn(-1,-2,tempStatus);
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

    protected boolean isRotatable(int deltaX, int deltaY){
        boolean[][] judgeArea = new boolean[CELLS_SIZE][CELLS_SIZE];
        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0; j < CELLS_SIZE; j++) {
                judgeArea[i][j]= tetrisGame.getGridStatus(cells[i][j].getCurrentX()+deltaX,cells[i][j].getCurrentY()+deltaY);
            }
        }

        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0,k=CELLS_SIZE-1; j < CELLS_SIZE; j++,k--) {
                if(cells[i][j].getCurrentX()+deltaX<0||cells[i][j].getCurrentX()+deltaX>= tetrisGame.xRange||cells[i][j].getCurrentY()+deltaY>= tetrisGame.yRange){
                    return false;
                }
                if(cells[k][i].getStatus()&&judgeArea[i][j]){
                    System.out.println("non-rotatable");
                    return false;
                }

            }
        }
        return true;
    }

    protected void rotateAndFillIn(int deltaX, int deltaY, boolean[][] tempStatus){
        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0,k = CELLS_SIZE-1; j < CELLS_SIZE; j++,k--) {
                cells[i][j].setStatus(tempStatus[k][i]);
                tetrisGame.cellFillIn(cells[i][j],cells[i][j].getCurrentX()+deltaX,cells[i][j].getCurrentY()+deltaY);
            }
        }
        changeCurrentRotateState();
    }



    /*
        Each cells have four rotate states, which are "0", "R", "2", "L".
        This function can change the rotate state to next .
    * */
    protected void changeCurrentRotateState() {
        for (int i = 0; i < rotateState.length; i++) {
            if(rotateState[i].equals(currentRotateState)){
                if(i==rotateState.length-1){
                    currentRotateState = rotateState[0];
                    return;
                }
                currentRotateState=rotateState[i+1];
                return;
            }
        }
    }

    public void goDown() {
        if(!isNextStepWorkable("down")){
            return;
        }
        for (int j = CELLS_SIZE-1; j >= 0; j--) {
            for (int i = 0; i < CELLS_SIZE; i++) {
                cells[j][i].goDown(cells[j][i], tetrisGame);
            }
        }
    }

    public void goLeft() {
        if(!isNextStepWorkable("left")){
            return;
        }
        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0; j < CELLS_SIZE; j++) {
                cells[i][j].goLeft(cells[i][j], tetrisGame);
            }
        }

    }

    public void goRight() {
        if(!isNextStepWorkable("right")){
            return;
        }
        for (int i = CELLS_SIZE-1; i >= 0; i--) {
            for (int j = 0; j < CELLS_SIZE; j++) {
                cells[j][i].goRight(cells[j][i], tetrisGame);
            }
        }
    }

    /*
        Makes the cells spawn in the TetrisGrid.
    * */
    public void spawn() {
        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0, k = CELLS_SIZE; j < CELLS_SIZE; j++, k++) {
                tetrisGame.cellFillIn(cells[i][j], k, i+2);
            }
        }
    }

    private boolean isOutside(String nextDirection, Cell cell){
        switch(nextDirection){
            case "down":
                return cell.getCurrentY() >= tetrisGame.yRange-1 && cell.getStatus();
            case "left":
                return cell.getCurrentX()-1 < 0 && cell.getStatus();
            case "right":
                return cell.getCurrentX() >= tetrisGame.xRange-1 && cell.getStatus();
        }
        return true;
    }

    private boolean isGoingToBumpIntoOtherCell(TetrisGame tetrisGame, String nextDirection, Cell cell){

        if(!cell.getStatus()){
            return false;
        }
        switch (nextDirection) {
            case "down":
                return tetrisGame.getGridStatus(cell.getCurrentX(), cell.getCurrentY() + 1);
            case "left":
                return tetrisGame.getGridStatus(cell.getCurrentX() - 1, cell.getCurrentY());
            case "right":
                return tetrisGame.getGridStatus(cell.getCurrentX() + 1, cell.getCurrentY());
        }
        return true;

    }

    protected boolean isNextStepWorkable(String nextDirection){
        int currentX;
        int currentY;
        for (int i = 0; i < cells.length; i++) {
            for(int j = 0; j < cells.length ; j++){
                currentX = cells[i][j].getCurrentX();
                currentY = cells[i][j].getCurrentY();
                tetrisGame.cellRemove(cells[i][j],currentX,currentY);
                cells[i][j].setCurrentX(currentX);
                cells[i][j].setCurrentY(currentY);
            }
        }

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {

                if(isGoingToBumpIntoOtherCell(tetrisGame,nextDirection,cells[i][j])||isOutside(nextDirection,cells[i][j])){

                    for (int k = 0; k < cells.length ; k++) {
                        for (int l = 0; l < cells.length; l++) {
                            tetrisGame.cellFillIn(cells[k][l],cells[k][l].getCurrentX(),cells[k][l].getCurrentY());
                        }
                    }
                    System.out.println("next step is not workable");
                    return false;
                }
            }
        }
        return true;

    }

    protected boolean checkIfCellsCanSpawn(){
        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0, k = CELLS_SIZE; j < CELLS_SIZE; j++, k++) {
                if(cells[i][j].getStatus()&& tetrisGame.getGridStatus(k,i+2)){
                    return false;
                }
            }

        }
        return true;
    }

    protected void fillInWaitingGrids(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tetrisGame.getWaitingGrids()[i][j].getRectangle().setFill(tetrisGame.getWaitingGrids()[i][j].initColor);
            }

        }

        for (int i = 0; i < CELLS_SIZE; i++) {
            for (int j = 0; j < CELLS_SIZE; j++) {
                if(!cells[i][j].getStatus()){
                    continue;
                }
                tetrisGame.getWaitingGrids()[i][j].getRectangle().setFill(cells[i][j].color);
            }
        }
    }

}

