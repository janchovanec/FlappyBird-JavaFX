package java1.cho0252;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Thread that handles the drawing of the game.
 */
public class RenderThread extends AnimationTimer {
    	
    private final GraphicsContext gc;
	private final Canvas canvas;
	private final GameScene gameScene;
	private long lastTime;

	public RenderThread(Canvas canvas, GameScene gameScene) {
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();
		this.gameScene = gameScene;
	}

	@Override
	public void handle(long now) {
		if (lastTime > 0) {
			double deltaT = (now - lastTime) / 1e9;
			gameScene.simulate(deltaT);
		}
		gameScene.render(gc);
		lastTime= now;

	}
    
}
