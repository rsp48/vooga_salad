package engine;

import gameplayer.IGraphicFactory;
import graphics.IGraphic;
import javafx.scene.Node;


/**
 * This interface represents the component of a sprite that will handle its visual properties
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface IGraphicModule {

    Node getVisualRepresentation (IGraphicFactory factory);
    
    IGraphic getGraphic ();

}
