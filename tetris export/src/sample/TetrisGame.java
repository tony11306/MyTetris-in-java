package sample;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;


public class TetrisGame{
    public final int xRange = 10;
    public final int yRange = 22;
    private GridPane gridPane = new GridPane();
    private SingleGrid[][] tetrisGrid = new SingleGrid[xRange][yRange];
    private SingleGrid[][] waitingGrids = new SingleGrid[4][4];
    private int lineCount = 0;
    private Label lineCountLabel = new Label("Line:\n"+lineCount);
    private int score = 0;
    private Label scoreLabel = new Label("Score:\n"+score);
    private int lastLineCount =0;

    public TetrisGame(){
        for (int i = 0; i < xRange; i++) {
            for (int j = 0; j < yRange; j++) {
                if(j <= 1){
                    tetrisGrid[i][j] = new SingleGrid(Color.BLUE);
                }
                else{
                    tetrisGrid[i][j] = new SingleGrid(Color.GREY);
                }

                if (j>1){
                    gridPane.add(tetrisGrid[i][j].getRectangle(), i, j);
                }

            }
        }

        for (int x = xRange, j = 0; x < xRange+4; x++,j++) {
            for (int y = 3, i = 0; y < 7; y++,i++) {
                waitingGrids[i][j] = new SingleGrid(Color.BLANCHEDALMOND);
                waitingGrids[i][j].getRectangle().setWidth(40);
                gridPane.add(waitingGrids[i][j].getRectangle(),x,y);
            }
            
        }
        gridPane.add(scoreLabel,xRange+1,yRange/3+1);
        gridPane.add(lineCountLabel,xRange+1,yRange/2);
        scoreLabel.setScaleX(3);
        scoreLabel.setScaleY(3);
        scoreLabel.setAlignment(Pos.CENTER_LEFT);
        lineCountLabel.setScaleX(3);
        lineCountLabel.setScaleY(3);
        lineCountLabel.setAlignment(Pos.CENTER_LEFT);
        gridPane.setPadding(new Insets(0,10,10,10));
        gridPane.setHgap(1);
        gridPane.setVgap(1);
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public boolean getGridStatus(int x,int y){
        if(x<0||y<0||x>=xRange||y>=yRange){
            return false;
        }
        return tetrisGrid[x][y].getGridStatus();
    }

    public SingleGrid[][] getTetrisGrid() {
        return tetrisGrid;
    }

    public SingleGrid[][] getWaitingGrids() {
        return waitingGrids;
    }



    public void cellFillIn(Cell cell, int x, int y){
        cell.setCurrentX(x);
        cell.setCurrentY(y);
        if(!cell.getStatus()){
            //System.out.println(cell.getCurrentX() + " " + cell.getCurrentY() + " is blank cell");
            return;
        }

        //System.out.println(cell.getCurrentX()+""+cell.getCurrentY()+" "+cell.getStatus());
        tetrisGrid[x][y].setCurrentStrokeColor(Color.BLACK);
        tetrisGrid[x][y].setGridStatus(true);
        tetrisGrid[x][y].setCurrentColor(cell.color);

        tetrisGrid[x][y].getRectangle().setFill(cell.color);
        tetrisGrid[x][y].getRectangle().setStroke(Color.BLACK);


    }

    public void cellRemove(Cell cell, int x, int y){
        if(x<0||y<0||x>=xRange||y>=yRange){
            return;
        }
        else if(!cell.getStatus() && tetrisGrid[cell.getCurrentX()][cell.getCurrentY()].getGridStatus()){
            return;
        }
        tetrisGrid[x][y].setGridStatus(false);
        tetrisGrid[x][y].getRectangle().setFill(tetrisGrid[x][y].initColor);
        tetrisGrid[x][y].getRectangle().setStroke(tetrisGrid[x][y].initColor);
        tetrisGrid[x][y].setCurrentColor(tetrisGrid[x][y].initColor);
        tetrisGrid[x][y].setCurrentStrokeColor(tetrisGrid[x][y].initColor);
        cell.setCurrentX(-1);
        cell.setCurrentY(-1);

    }


    public boolean checkIfRowCanBeEliminated(int rowIndex){
        for (int i = 0; i < xRange; i++) {
            if(!tetrisGrid[i][rowIndex].getGridStatus()){
                return false;
            }
        }
        return true;
    }

    public void eliminateRow(int rowIndex){
        for (int i = 0; i < xRange; i++) {
            tetrisGrid[i][rowIndex].setGridStatus(false);
            tetrisGrid[i][rowIndex].getRectangle().setFill(tetrisGrid[i][rowIndex].initColor);
            tetrisGrid[i][rowIndex].getRectangle().setStroke(tetrisGrid[i][rowIndex].initColor);
        }
        lineCountPlus();
        if(rowIndex==3){
            return;
        }
        for (int i = 0; i < xRange; i++) {
            for (int j = rowIndex-1; j >= 2; j--) {
                tetrisGrid[i][j+1].setGridStatus(tetrisGrid[i][j].getGridStatus());
                tetrisGrid[i][j+1].setCurrentColor(tetrisGrid[i][j].getCurrentColor());
                tetrisGrid[i][j+1].setCurrentStrokeColor(tetrisGrid[i][j].getCurrentStrokeColor());
                tetrisGrid[i][j+1].getRectangle().setFill(tetrisGrid[i][j].getCurrentColor());
                tetrisGrid[i][j+1].getRectangle().setStroke(tetrisGrid[i][j].getCurrentStrokeColor());
            }

        }
        if(checkIfRowCanBeEliminated(rowIndex)){
            eliminateRow(rowIndex);
        }
    }

    public boolean isGameOver(Cells cells){
        return cells.checkIfCellsCanSpawn();
    }

    private void lineCountPlus(){
        lineCount++;
        lineCountLabel.setText("Lines:\n"+lineCount);
    }

    public void scorePlus(){
        if(lineCount- lastLineCount ==1){
            score+=40;
        }
        else if(lineCount- lastLineCount ==2){
            score+=100;
        }
        else if(lineCount- lastLineCount ==3){
            score+=300;
        }
        else if(lineCount- lastLineCount ==4){
            score+=1200;
        }
        scoreLabel.setText("Score:\n"+score);
        lastLineCount = lineCount;
    }


}



