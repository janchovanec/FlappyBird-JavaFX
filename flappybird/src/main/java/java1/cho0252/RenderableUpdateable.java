package java1.cho0252;

import javafx.scene.canvas.GraphicsContext;

/**
 * Defines common behavior for all game elements.
 */
public interface RenderableUpdateable {
    public void update(double time);

    public void render(GraphicsContext gc);
}
