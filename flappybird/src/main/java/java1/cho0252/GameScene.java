package java1.cho0252;

import java.util.LinkedList;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/**
 * Manages the overall game scene and game loop.
 */
public class GameScene implements IGameScene {

    private IBird bird;
    private LinkedList<IPipe> pipes;
    private IGameBackground background;
    private GameListener gameListener;
    private int score;
    private double scrollSpeed;

    public GameScene() {
        score = 0;
        this.scrollSpeed = 100;
        gameListener = new EmptyGameListener();
        bird = new Bird(0);
        pipes = new LinkedList<>();
        background = new GameBackground();
    }

    @Override
    public void initialize(double scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
        for (int x = 1; x < 6; x++) {
            Point2D pipeHeights = generatePipeHeight();
            pipes.add(new Pipe(pipeHeights.getX(), pipeHeights.getY(), 250 * x, scrollSpeed, new EmptyPipeListener() {
                @Override
                public void onPipePassed() {
                    GameScene.this.score++;
                    GameScene.this.gameListener.onScoreUpdate(GameScene.this.score);
                }
            }));
        }
        ;
        background.setScrollSpeed(scrollSpeed);
    }

    @Override
    public void setBirdSkin(int birdSkin) {
        bird = new Bird(birdSkin);
    }

    @Override
    public void simulate(double deltaT) {
        background.update(deltaT);
        if (bird.isOutOfBounds()) {
            endGame();
        }
        placePipe();
        for (IPipe pipe : pipes) {
            pipe.update(deltaT);
            if (bird.collidesWith(pipe.getHitboxTop()) ||
                    bird.collidesWith(pipe.getHitboxBot())) {
                endGame();
            }
        }
        bird.update(deltaT);
    }

    @Override
    public void setupKeyboardInput(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    bird.jump();
                    break;
                case SPACE:
                    bird.jump();
                    break;
                case ESCAPE:
                    endGame();
                    break;
            }
        });
    }

    @Override
    public void render(GraphicsContext gc) {
        background.render(gc);
        bird.render(gc);
        for (IPipe pipe : pipes) {
            pipe.render(gc);
        }
    }

    @Override
    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }

    private void endGame() {
        gameListener.onGameEnd();
    }

    private void placePipe() {
        if (pipes.getFirst().isOffScreen()) {
            Point2D pipeHeights = generatePipeHeight();
            pipes.removeFirst();
            pipes.add(new Pipe(pipeHeights.getX(), pipeHeights.getY(), scrollSpeed, new EmptyPipeListener() {
                @Override
                public void onPipePassed() {
                    GameScene.this.score++;
                    GameScene.this.gameListener.onScoreUpdate(GameScene.this.score);
                }
            }));
        }
    }

    private Point2D generatePipeHeight() {
        double topPipeHeight = Math.random() * 400 + 100;
        double bottomPipeHeight = 800 - topPipeHeight - 200;

        return new Point2D(topPipeHeight, bottomPipeHeight);
    }
}
