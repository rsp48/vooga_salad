package graphics;

import engine.rendering.IGraphicFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;


public class ImageGraphic extends SizeableGraphic {

    StringProperty myUrl;
    
    public ImageGraphic (double width, double height, String url) {
        super(width, height);
        myUrl = new SimpleStringProperty(url);
    }

    public StringProperty getUrlProperty () { 
        return myUrl;
    }
    
    @Override
    public Node getVisualRepresentation (IGraphicFactory factory) {
        return factory.getVisual(this);
    }

}
