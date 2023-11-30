package java1.cho0252;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Represents the bird in the game.
 */
public class Bird implements IBird {

    private Point2D position;
    private Point2D velocity;
    private Point2D acceleration;
    private Image image;
    private Circle hitbox;

    private final double WINDOW_HEIGHT = 800;
    private final double GRAVITY = 1000;
    private final double JUMP_STRENGTH = 300;
    private final double BIRD_SIZE = 75;

    public Bird() {
        this(0);
    }

    public Bird(int birdSkin) {
        position = new Point2D(100, WINDOW_HEIGHT / 2);
        velocity = new Point2D(0, 0);
        acceleration = new Point2D(0, GRAVITY);
        switch (birdSkin) {
            case 1:
                image = new Image(getClass().getResourceAsStream("cowboy.png"), BIRD_SIZE, BIRD_SIZE, false, true);
                break;
            case 2:
                image = new Image(getClass().getResourceAsStream("japan.png"), BIRD_SIZE, BIRD_SIZE, false, true);
                break;
            case 3:
                image = new Image(getClass().getResourceAsStream("robot.png"), BIRD_SIZE, BIRD_SIZE, false, true);
                break;
            default:
                image = new Image(getClass().getResourceAsStream("flappybird.png"), BIRD_SIZE, BIRD_SIZE, false, true);
                break;
        }
        hitbox = new Circle(position.getX() + image.getWidth() / 1.75, position.getY() + image.getHeight() / 1.8,
                image.getWidth() / 2.45);
    }

    @Override
    public void update(double time) {
        velocity = velocity.add(acceleration.multiply(time));
        position = position.add(velocity.multiply(time));
        hitbox.setCenterX(position.getX());
        hitbox.setCenterY(position.getY());
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.translate(position.getX(), position.getY());

        double angle = Math.atan2(velocity.getY(), 60);
        gc.rotate(Math.toDegrees(angle));
        gc.drawImage(image, -image.getWidth() / 2, -image.getHeight() / 2);
        gc.rotate(-Math.toDegrees(angle));
        gc.translate(-position.getX(), -position.getY());
    }

    @Override
    public void jump() {
        velocity = new Point2D(0, -JUMP_STRENGTH);
    }

    @Override
    public boolean collidesWith(Rectangle hitbox) {
        return this.hitbox.intersects(hitbox.getBoundsInLocal());
    }

    @Override
    public boolean isOutOfBounds() {
        return position.getY() > WINDOW_HEIGHT || position.getY() < 0;
    }
}