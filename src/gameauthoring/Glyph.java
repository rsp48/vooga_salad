package gameauthoring;

import javafx.scene.Node;

/**
 * General UI element interface. Each part of the view
 * should have some way of drawing and updating, so that
 * they can be placed anywhere and are modular
 * @author Tommy
 *
 */
public interface Glyph {
    
    /**
     * Creates the actual JavaFX node to display
     * @return Node
     */
    Node draw();
    
    /**
     * Updates the view's screen. Not sure if this
     * is necessary but it might be. Later on this may be
     * view-specific
     */
    void update();
}
