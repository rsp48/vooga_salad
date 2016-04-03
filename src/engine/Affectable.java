package engine;



import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;


/**
 * This interface imposes the ability to respond to an incoming effect and handle it as necessary.
 * The implementing class may or may not change a result of an applied effect.
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface Affectable extends Updateable {

    /**
     * Apply a given effect to this object
     *
     * @param effect the effect to apply
     */
    void applyEffect (IEffect effect);

    /**
     * Respond appropriately to a global key interaction event
     * 
     * @param keyEvent to respond to
     */
    void registerKeyEvent (KeyIOEvent keyEvent);
    
    /**
     * Respond appropriately to a global mouse interaction event
     * 
     * @param mouseEvent to respond to
     */
    void registerMouseEvent (MouseIOEvent mouseEvent);

    /**
     * Any object that is affectable must have attributes that can be affected.
     * We are thus combining these two notions in this one interface.
     * 
     * @return observable list of attribute properties
     */
    ObservableList<ObjectProperty<IAttribute>> getAttributes ();

}
