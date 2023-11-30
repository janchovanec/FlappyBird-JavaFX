package java1.cho0252;

import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Background;
import javafx.util.Pair;

/**
 * Controller for the menu screen.
 */
public class MenuController {

    @FXML
    private Button startButton;
    @FXML
    private Pane menuPane;
    @FXML
    private Button highscoresButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Pane settingsPane;
    @FXML
    private Button settingsMenuButton;
    @FXML
    private Slider scrollSpeedSlider;
    @FXML
    private Button highScoresMenuButton;
    @FXML
    private Pane highscoresPane;
    @FXML
    private TableView<Pair<String, Integer>> highscoresTable;
    @FXML
    private TableColumn<Pair<String, Integer>, String> nameColumn;
    @FXML
    private TableColumn<Pair<String, Integer>, Integer> scoreColumn;

    @FXML
    private ImageView birdSkinDefault;
    @FXML
    private ImageView birdSkinCowboy;
    @FXML
    private ImageView birdSkinJapan;
    @FXML
    private ImageView birdSkinRobot;

    private MenuListener menuListener;

    private int birdSkin;
    private double scrollSpeed;

    public MenuController() {
        birdSkin = 0;
        scrollSpeed = 100;
        menuListener = new EmptyMenuListener();
    }

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResourceAsStream("background1.png"), 0, 800, false, false);
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true); // Set crop to true
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        menuPane.setBackground(background);
        highscoresPane.setBackground(background);
        settingsPane.setBackground(background);
    }

    public void setScrollSpeed(double scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    public void setBirdSkin(int birdSkin) {
        this.birdSkin = birdSkin;
    }

    public void setMenuListener(MenuListener menuListener) {
        this.menuListener = menuListener;
    }

    @FXML
    public void onStartButtonClicked() {
        menuListener.onStartButtonClicked(birdSkin, scrollSpeed);
    }

    private void initializeHighscoresTable() {
        highscoresTable.setItems(FXCollections.observableList(new LinkedList<>()));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Pair<String, Integer>, String>("Key"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Pair<String, Integer>, Integer>("Value"));
        highscoresTable.getItems().addAll(FileManager.loadScores());
    }

    @FXML
    public void onHighScoresButtonClicked() {
        initializeHighscoresTable();
        menuPane.setVisible(false);
        highscoresPane.setVisible(true);
    }

    @FXML
    public void onHighScoresMenuButtonClicked() {
        menuPane.setVisible(true);
        highscoresPane.setVisible(false);
    }

    @FXML
    public void onSettingsButtonClicked() {
        scrollSpeedSlider.setValue(scrollSpeed);
        menuPane.setVisible(false);
        birdInitializeMenuOppacity();
        settingsPane.setVisible(true);
    }

    @FXML
    public void onSettingsMenuButtonClicked() {
        scrollSpeed = scrollSpeedSlider.getValue();
        menuPane.setVisible(true);
        settingsPane.setVisible(false);
    }

    private void birdInitializeMenuOppacity() {
        switch (birdSkin) {
            case 1:
                birdSkinCowboy.setOpacity(1);
                break;
            case 2:
                birdSkinJapan.setOpacity(1);
                break;
            case 3:
                birdSkinRobot.setOpacity(1);
                break;
            default:
                birdSkinDefault.setOpacity(1);
                break;
        }
    }

    @FXML
    public void onBirdSkinDefaultClicked() {
        birdSkin = 0;
        birdSkinDefault.setOpacity(1);
        birdSkinCowboy.setOpacity(0.5);
        birdSkinJapan.setOpacity(0.5);
        birdSkinRobot.setOpacity(0.5);
    }

    @FXML
    public void onBirdSkinCowboyClicked() {
        birdSkin = 1;
        birdSkinDefault.setOpacity(0.5);
        birdSkinCowboy.setOpacity(1);
        birdSkinJapan.setOpacity(0.5);
        birdSkinRobot.setOpacity(0.5);
    }

    @FXML
    public void onBirdSkinJapanClicked() {
        birdSkin = 2;
        birdSkinDefault.setOpacity(0.5);
        birdSkinCowboy.setOpacity(0.5);
        birdSkinJapan.setOpacity(1);
        birdSkinRobot.setOpacity(0.5);
    }

    @FXML
    public void onBirdSkinRobotClicked() {
        birdSkin = 3;
        birdSkinDefault.setOpacity(0.5);
        birdSkinCowboy.setOpacity(0.5);
        birdSkinJapan.setOpacity(0.5);
        birdSkinRobot.setOpacity(1);
    }
}
