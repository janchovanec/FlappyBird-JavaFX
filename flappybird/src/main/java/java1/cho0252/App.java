package java1.cho0252;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Flappy Bird
 */
public class App extends Application {
    private MenuController menuController;
    private GameController gameController;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            this.primaryStage = primaryStage;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(loader.load(), 600, 800);
            menuController = loader.getController();
            menuController.setMenuListener(new MenuListener() {
                @Override
                public void onStartButtonClicked(int birdSkin, double scrollSpeed) {
                    App.this.startGame(birdSkin, scrollSpeed);
                }
            });
            primaryStage.setScene(scene);
            primaryStage.resizableProperty().set(false);
            primaryStage.setTitle("Flappy Bird");
            primaryStage.show();
            primaryStage.setOnCloseRequest(e -> exitProgram());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openMenu(int birdSkin, double scrollSpeed) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(loader.load(), 600, 800);
            menuController = loader.getController();
            menuController.setBirdSkin(birdSkin);
            menuController.setScrollSpeed(scrollSpeed);
            menuController.setMenuListener(new MenuListener() {
                @Override
                public void onStartButtonClicked(int birdSkin, double scrollSpeed) {
                    App.this.startGame(birdSkin, scrollSpeed);
                }
            });
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startGame(int birdSkin, double scrollSpeed) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGame.fxml"));
            Scene scene = new Scene(loader.load(), 600, 800);
            gameController = loader.getController();
            gameController.setGameControllerListener(new GameControllerListener() {
                @Override
                public void onGameEnd(int birdSkin, double scrollSpeed) {
                    App.this.openMenu(birdSkin, scrollSpeed);
                }
            });

            primaryStage.setScene(scene);
            primaryStage.show();
            gameController.startGame(birdSkin, scrollSpeed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private void exitProgram() {
        try {
            gameController.terminate();
        } catch (Exception e) {
            // Do nothing
        }
        System.exit(0);
    }

}