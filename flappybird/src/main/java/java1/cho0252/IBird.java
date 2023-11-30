package java1.cho0252;

import javafx.scene.shape.Rectangle;

/**
 * Extends RenderableUpdateable to define common behavior for bird.
 */
public interface IBird extends RenderableUpdateable {
    public void jump();

    public boolean collidesWith(Rectangle hitbox);

    public boolean isOutOfBounds();
}
