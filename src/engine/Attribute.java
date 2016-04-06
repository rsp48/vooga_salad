package engine;

import java.util.stream.Collectors;
import engine.effects.IEffect;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.TimeDuration;


public class Attribute implements IAttribute {

    private DoubleProperty myValue;
    private AttributeType myType;
    private ObservableList<ObjectProperty<IEffect>> myEffects;

    public Attribute (AttributeType type) {
        myValue = new SimpleDoubleProperty(0);
        myEffects = FXCollections.observableArrayList();
    }

    public Attribute (double value, AttributeType type) {
        myValue = new SimpleDoubleProperty(value);
        myType = type;
        myEffects = FXCollections.observableArrayList();
    }

    @Override
    public void applyEffect (IEffect effect) {
        myEffects.add(new SimpleObjectProperty<>(effect));
    }

    @Override
    public AttributeType getType () {
        return myType;
    }

    @Override
    public DoubleProperty getValueProperty () {
        return myValue;
    }

    @Override
    public void setValue (double valueToSet) {
        myValue.set(valueToSet);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent event) {
        // do nothing
    }

    @Override
    public void registerMouseEvent (MouseIOEvent event) {
        // do nothing
    }

    @Override
    public ObservableList<ObjectProperty<IAttribute>> getAttributes () {

        ObservableList<ObjectProperty<IAttribute>> attributes =
                FXCollections.observableArrayList();
        attributes.add(new SimpleObjectProperty<>(this));
        return attributes;
    }

    @Override
    public void update (TimeDuration duration) {
        myEffects.forEach(e -> e.get().applyToAttribute(this));
        myEffects.forEach(e -> e.get().update(duration));
        removeCompletedEffects(duration);
    }

    private void removeCompletedEffects (TimeDuration duration) {

        myEffects.stream().filter(e -> !e.get().hasCompleted())
                .collect(Collectors.toList());
        
    }

    @Override
    public ObservableList<ObjectProperty<IEffect>> getEffects () {
        return myEffects;
    }

}