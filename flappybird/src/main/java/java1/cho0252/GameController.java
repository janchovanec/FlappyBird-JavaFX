package java1.cho0252;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Controller for the game screen.
 */
public class GameController {

    private IGameScene gameScene;
    private AnimationTimer timer;
    private GameControllerListener gameControllerListener;

    @FXML
    private Canvas canvas;
    @FXML
    private Text scoreField;
    @FXML
    private Button saveButton;
    @FXML
    private Button menuButton;
    @FXML
    private TextField nameField;
    @FXML
    private Pane gameOverPane;

    private int birdSkin;
    private double scrollSpeed;

    public GameController() {
        birdSkin = 0;
        scrollSpeed = 100;
        this.gameControllerListener = new EmptyGameControllerListener();
        this.gameScene = new GameScene();
    }

    public void startGame(int birdSkin, double scrollSpeed) {
        this.birdSkin = birdSkin;
        this.scrollSpeed = scrollSpeed;
        gameScene.initialize(scrollSpeed);
        gameScene.setBirdSkin(birdSkin);
        gameScene.setupKeyboardInput(canvas.getScene());
        gameScene.setGameListener(new EmptyGameListener() {
            @Override
            public void onGameEnd() {
                GameController.this.pauseGame();
            }

            @Override
            public void onScoreUpdate(int score) {
                scoreField.setText(Integer.toString(score));
            }
        });
        timer = new DrawingThread(canvas, gameScene);
        timer.start();
    }

    public void setGameControllerListener(GameControllerListener gameControllerListener) {
        this.gameControllerListener = gameControllerListener;
    }

    private void pauseGame() {
        timer.stop();
        gameOverPane.setVisible(true);
    }

    @FXML
    public void onSaveButtonClicked() {
        String name = nameField.getText();
        name = name.replace(";", "");
        int score = Integer.parseInt(scoreField.getText());
        FileManager.saveScore(name, score);
        gameControllerListener.onGameEnd(birdSkin, scrollSpeed);
    }

    public void terminate() {
        timer.stop();
    }

    @FXML
    public void onMenuButtonClicked() {
        gameControllerListener.onGameEnd(birdSkin, scrollSpeed);
    }
}
