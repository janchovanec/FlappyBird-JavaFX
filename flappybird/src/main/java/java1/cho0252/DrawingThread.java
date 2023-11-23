package java1.cho0252;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Thread that handles the drawing of the game.
 */
public class DrawingThread extends AnimationTimer {

	private final Canvas canvas;
	
	private final GraphicsContext gc;

	private final GameScene gameScene;
	
	private long lasttime = -1;

	public DrawingThread(Canvas canvas, GameScene gameScene) {
		this.canvas = canvas;
		this.gc = canvas.getGraphicsContext2D();
		this.gameScene = gameScene;
	}

	@Override
	public void handle(long now) {
		gameScene.render(gc);
		if (lasttime > 0) {
			//time are in nanoseconds and method simulate expects seconds
			gameScene.simulate((now - lasttime) / 1e9);
		}
		lasttime = now;
	}

}
