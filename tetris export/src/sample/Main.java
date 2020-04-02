package sample;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import java.util.Timer;
import java.util.TimerTask;
import  javafx.scene.layout.BorderPane;

public class Main extends Application
{
    TetrisGame tetrisGame = new TetrisGame();
    Cells currentCells = createRandomCells();
    Cells nextCells = createRandomCells();
    MenuBar menuBar = new MenuBar();
    Menu option = new Menu("Option");
    MenuItem menuItem_NewGame =new MenuItem("New Game");

    @Override
    public void start(Stage stage){

        menuBar.getMenus().addAll(option);
        option.getItems().add(menuItem_NewGame);
        menuItem_NewGame.setOnAction(e -> createGame(stage));

        stage.setTitle("俄羅斯方塊-tony11306");
        stage.show();
        createGame(stage);

    }

    private Cells createRandomCells(){
        int randomInt = (int)(Math.random()*7+1);

        switch (randomInt){
            case 1:
                return new TCells(tetrisGame);
            case 2:
                return new OCells(tetrisGame);
            case 3:
                return new LCells(tetrisGame);
            case 4:
                return new JCells(tetrisGame);
            case 5:
                return new SCells(tetrisGame);
            case 6:
                return new ZCells(tetrisGame);
            default:
                return new ICells(tetrisGame);
        }

    }


    public void createGame(Stage stage){
        BorderPane borderPane = new BorderPane();
        tetrisGame = new TetrisGame();
        currentCells = createRandomCells();
        nextCells = createRandomCells();
        borderPane.setTop(menuBar);
        borderPane.setCenter(tetrisGame.getGridPane());
        Scene newGameScene = new Scene(borderPane,650,900);


        EventHandler<KeyEvent> keyEventEventHandler = e -> {
            switch (e.getCode()){
                case DOWN:
                case S:
                    currentCells.goDown();break;
                case LEFT:
                case A:
                    currentCells.goLeft();break;
                case RIGHT:
                case D:
                    currentCells.goRight();break;
                case UP:
                case W:
                    currentCells.rotate();break;
                case SPACE:
                    while(currentCells.isNextStepWorkable("down")){
                        currentCells.goDown();
                    }
                    currentCells = nextCells;
                    detectAndEliminateRows(tetrisGame);
                    currentCells.spawn();
                    nextCells = createRandomCells();
                    nextCells.fillInWaitingGrids();

                    break;

            }
        };



        currentCells.spawn();
        nextCells.fillInWaitingGrids();

        Timer fallDown = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    if(currentCells.isNextStepWorkable("down")){
                        currentCells.goDown();
                    }
                    else{
                        if(!tetrisGame.isGameOver(currentCells)){
                            fallDown.cancel();
                            newGameScene.removeEventFilter(KeyEvent.KEY_PRESSED,keyEventEventHandler);
                            for (int i = 0; i < tetrisGame.xRange; i++) {
                                for (int j = 0; j < tetrisGame.yRange; j++) {
                                    if(tetrisGame.getGridStatus(i,j)){
                                        tetrisGame.getTetrisGrid()[i][j].setCurrentColor(Color.BLACK);
                                        tetrisGame.getTetrisGrid()[i][j].setCurrentStrokeColor(Color.BLACK);


                                    }
                                }
                            }
                            Text gameOver = new Text("Game Over");
                            gameOver.setFill(Color.DARKRED);
                            gameOver.setScaleX(5);
                            gameOver.setScaleY(5);
                            gameOver.setX(200);
                            gameOver.setY(450);
                            borderPane.getChildren().add(gameOver);

                            return;
                        }
                        detectAndEliminateRows(tetrisGame);

                        currentCells = nextCells;
                        currentCells.spawn();
                        nextCells = createRandomCells();
                        nextCells.fillInWaitingGrids();
                    }
                });

            }
        };
        fallDown.schedule(task,1000,400);

        newGameScene.addEventFilter(KeyEvent.KEY_PRESSED,keyEventEventHandler);
        menuItem_NewGame.setOnAction(e ->{
            fallDown.cancel();
            newGameScene.removeEventFilter(KeyEvent.KEY_PRESSED,keyEventEventHandler);
            createGame(stage);
        });
        stage.setScene(newGameScene);



    }

    private void detectAndEliminateRows(TetrisGame tetrisGame){
        for (int rowIndex = 2; rowIndex < tetrisGame.yRange; rowIndex++) {
            if(tetrisGame.checkIfRowCanBeEliminated(rowIndex))  {
                tetrisGame.eliminateRow(rowIndex);
            }
        }
        tetrisGame.scorePlus();

    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
