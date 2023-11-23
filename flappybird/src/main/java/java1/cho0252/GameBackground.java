package java1.cho0252;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents the background in the game.
 */
public class GameBackground implements IGameBackground {
    private Point2D positionA;
    private Point2D positionB;
    private Image image1;
    private Image image2;
    private double scrollSpeed;

    private final double SCROLL_SPEED_MODIFIER = 0.7;
    private final double WINDOW_HEIGHT = 800;

    public GameBackground() {
        this.scrollSpeed = 100;
        this.image1 = new Image(getClass().getResourceAsStream("background1.png"), 0, WINDOW_HEIGHT, true, false);
        this.image2 = new Image(getClass().getResourceAsStream("background2.png"), 0, WINDOW_HEIGHT, true, false);
        this.positionA = new Point2D(0, 0);
        this.positionB = new Point2D(image1.getWidth(), 0);
    }
    
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image1, positionA.getX(), positionA.getY());
        gc.drawImage(image2, positionB.getX(), positionB.getY());
    }

    @Override
    public void update(double time) {
        scroll(time);
    }

    public void setScrollSpeed(double scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    private void scroll(double time) {
        positionA = positionA.add(-scrollSpeed*SCROLL_SPEED_MODIFIER*time,0);
        positionB = positionB.add(-scrollSpeed*SCROLL_SPEED_MODIFIER*time,0);
        if (positionA.getX() < -image1.getWidth()) {
            positionA = new Point2D(image1.getWidth(), 0);
        }
        if (positionB.getX() < -image2.getWidth()) {
            positionB = new Point2D(image2.getWidth(), 0);
        }
    }
}
