package engine.modules;

import java.util.ArrayList;
import java.util.List;
import engine.IAttribute;
import engine.Positionable;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Coordinate;
import util.TimeDuration;


/**
 * This class creates a module that moves in based on velocity in a linear direction
 * 
 * @author Timko
 * 
 */
public class ConstantMover extends Mover {

    
    /*
     * as of now, I am keeping the Positionable in here, so  there are other potential
     * bits of functionality like angle that can be built it here
     */

    public ConstantMover (double speed, double angle, Positionable parent) {
        super(parent);
        setSpeed(speed);
        setOrientation(angle);

    }

    @Override
    public void setPath (List<Coordinate> newPath) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Coordinate> getPath () {
        // TODO deal with this when paths are done?
        return null;
    }

    @Override
    public ObservableList<IAttribute> getAttributes () {
        // TODO NOT SURE WHAT TO DO HERE
    	ObservableList<IAttribute> attributeList = FXCollections.observableArrayList();
//        attributeList.add(mySpeed);
        return attributeList;
    }

    @Override
    public void update (TimeDuration duration) {
        super.move(duration);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // TODO Auto-generated method stub

    }

    @Override
    protected List<IAttribute> getSpecificAttributes () {
        // TODO What is this method supposed to do, how is it different from the other a
    	//attributes method 
    	 List<IAttribute> specialAttributes = new ArrayList<>();
//         specialAttributes.add(mySpeed);
         return specialAttributes;
    }


}
