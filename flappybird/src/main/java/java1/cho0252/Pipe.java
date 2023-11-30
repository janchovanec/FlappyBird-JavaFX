package java1.cho0252;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;

/**
 * Represents a pipe in the game.
 */
public class Pipe implements IPipe {
    private Point2D position1;
    private Point2D position2;
    private Image pipeCap;
    private Image pipeCenter1;
    private Image pipeCenter2;
    private Rectangle hitbox1;
    private Rectangle hitbox2;
    private double pipeHeight1;
    private double pipeHeight2;
    private boolean passed;
    private PipeListener pipeListener;

    private double scrollSpeed;
    private final double WINDOW_HEIGHT = 800;
    private final double PIPEWIDTH = 100;

    public Pipe(double pipeHeight1, double pipeHeight2, double xPos, double scrollSpeed, PipeListener pipeListener) {
        this.scrollSpeed = scrollSpeed;
        this.pipeListener = pipeListener;
        this.passed = false;
        this.pipeHeight2 = pipeHeight2;
        this.pipeHeight1 = pipeHeight1;
        this.position2 = new Point2D(xPos, 0);
        this.position1 = new Point2D(xPos, WINDOW_HEIGHT - pipeHeight1);

        this.pipeCenter1 = new Image(getClass().getResourceAsStream("centerPipe.png"), PIPEWIDTH, pipeHeight1, false,
                true);
        this.pipeCenter2 = new Image(getClass().getResourceAsStream("centerPipe.png"), PIPEWIDTH, pipeHeight2, false,
                true);
        this.pipeCap = new Image(getClass().getResourceAsStream("capPipe.png"), PIPEWIDTH, 0, true, true);
        this.hitbox1 = new Rectangle(position1.getX() - (pipeCenter2.getWidth() * 0.1),
                position1.getY() + pipeCap.getHeight() / 2,
                pipeCenter1.getWidth() - (pipeCenter2.getWidth() * 0.1),
                pipeCenter1.getHeight() - pipeCap.getHeight() / 2.5);
        this.hitbox2 = new Rectangle(position2.getX() + (pipeCenter2.getWidth() * 0.1), position2.getY(),
                pipeCenter2.getWidth() - (pipeCenter2.getWidth() * 0.1),
                pipeCenter2.getHeight() - pipeCap.getHeight() / 2.5);
    }

    public Pipe(double pipeHeight1, double pipeHeight2, double scrollSpeed, PipeListener pipeListener) {
        this(pipeHeight1, pipeHeight2, 4 * 250 + 100, scrollSpeed, pipeListener);
    }

    public Pipe() {
        this(0, 0, 100, new EmptyPipeListener());
    }

    @Override
    public void update(double time) {
        scroll(time);
        hitbox1.setX(position1.getX() + (pipeCenter2.getWidth() * 0.05));
        hitbox2.setX(position2.getX() + (pipeCenter2.getWidth() * 0.05));
        checkPassed();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(pipeCenter1, position1.getX(), position1.getY());
        gc.drawImage(pipeCenter2, position2.getX(), position2.getY());
        gc.drawImage(pipeCap, position2.getX(), pipeHeight2 - 15 - pipeCap.getHeight() / 2);

        gc.translate(position1.getX() + pipeCap.getWidth() / 2, 15 + pipeCap.getHeight() / 2);
        gc.rotate(180);
        gc.scale(-1, 1);
        gc.drawImage(pipeCap, -pipeCap.getWidth() / 2, +pipeHeight1 - WINDOW_HEIGHT);
        gc.setTransform(new Affine());
    }

    private void scroll(double deltaT) {
        position1 = position1.add(-scrollSpeed * deltaT, 0);
        position2 = position2.add(-scrollSpeed * deltaT, 0);
    }

    @Override
    public boolean isOffScreen() {
        return position1.getX() < -PIPEWIDTH - 50;
    }

    @Override
    public Rectangle getHitboxTop() {
        return hitbox2;
    }

    @Override
    public Rectangle getHitboxBot() {
        return hitbox1;
    }

    private void checkPassed() {
        if (position1.getX() < 0 && !passed) {
            passed = true;
            pipeListener.onPipePassed();
        }
    }
}
