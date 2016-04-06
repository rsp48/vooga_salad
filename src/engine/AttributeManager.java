package engine;

import java.util.function.Consumer;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


public class AttributeManager implements IAttributeManager {

    private ObservableList<ObjectProperty<IAttribute>> myAttributes;
    private ObservableList<ObjectProperty<IResource>> myResources;

    public AttributeManager () {
        myAttributes = FXCollections.observableArrayList();
        myResources = FXCollections.observableArrayList();
    }

    @Override
    public void update (TimeDuration duration) {
        updateAttributeLoop(attribute -> attribute.update(duration));
    }

    private void updateAttributeLoop (Consumer<IAttribute> consume) {
        getAttributes().forEach(attribute -> consume.accept(attribute.get()));
    }

    @Override
    public void applyEffect (IEffect effect) {
        updateAttributeLoop(attribute -> attribute.applyEffect(effect));
    }

    @Override
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {
        return myAttributes;
    }

    @Override
    public ObservableList<ObjectProperty<IResource>> getResourceList () {
        return myResources;
    }

    @Override
    public void addResource (IResource resource) {
        resource.getAttributes().forEach(attribute -> myAttributes.add(attribute));
        myResources.add(new SimpleObjectProperty<>(resource));
    }

    @Override
    public void registerKeyEvent (KeyIOEvent event) {
       myAttributes.forEach(attribute -> attribute.get().registerKeyEvent(event));
        
    }

    @Override
    public void registerMouseEvent (MouseIOEvent event) {
        myAttributes.forEach(attribute -> attribute.get().registerMouseEvent(event));
        
    }

}