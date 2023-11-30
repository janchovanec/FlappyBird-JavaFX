package java1.cho0252;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/**
 * Manages the overall game scene and game loop.
 */
public interface IGameScene {
    public void initialize(double scrollSpeed);

    public void setBirdSkin(int birdSkin);

    public void simulate(double deltaT);

    public void setupKeyboardInput(Scene scene);

    public void render(GraphicsContext gc);

    public void setGameListener(GameListener gameListener);
}