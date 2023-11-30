package java1.cho0252;

import javafx.scene.shape.Rectangle;

/**
 * Extends RenderableUpdateable to define common behavior for all pipes.
 */
public interface IPipe extends RenderableUpdateable {

    public boolean isOffScreen();

    public Rectangle getHitboxTop();

    public Rectangle getHitboxBot();

}
