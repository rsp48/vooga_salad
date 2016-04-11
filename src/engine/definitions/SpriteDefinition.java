package engine.definitions;

import java.util.List;
import engine.modules.IModule;
import engine.sprite.IProfile;
import engine.sprite.ISprite;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class SpriteDefinition implements IDefinition {

    private String myType;
    private MovementDefinition myMovementDefinition;
    private List<ModuleDefinition> myModuleDefinitions;
    private LocationDefinition myLocation;
    private List<AttributeDefinition> myAttributes;
    private ProfileDefinition myProfileDefinition;

    public ISprite create () {
        ISprite sprite = new Sprite();
        makeProfile(sprite);
        sprite.getType().set(new SpriteType(myType));
        sprite.getMovementStrategyProperty().set(myMovementDefinition.create(sprite));
        initModules(sprite);
        initAttributes(sprite);
        sprite.getLocation().set(myLocation.create());
        return sprite;
    }

    private void makeProfile (ISprite sprite) {
        IProfile profile = getProfileDefinition().makeProfile();
        sprite.getProfile().set(profile);

    }

    private void initAttributes (ISprite sprite) {
        myAttributes
                .forEach(a -> sprite.getAttributes().add(new SimpleObjectProperty<>(a.create())));

    }

    private void initModules (ISprite sprite) {
        ObservableList<ObjectProperty<IModule>> modules = FXCollections.observableArrayList();
        myModuleDefinitions.forEach(mod -> modules.add(new SimpleObjectProperty<>(mod.create())));
        sprite.getModulesProperty().addAll(modules);
    }

    public void addModule (ModuleDefinition definition) {
        myModuleDefinitions.add(definition);
    }

    public void addAttribute (AttributeDefinition attribute) {
        myAttributes.add(attribute);
    }

    public void removeAttribute (AttributeDefinition attribute) {
        myAttributes.remove(attribute);
    }

    public void setLocation (LocationDefinition location) {
        myLocation = location;
    }

    public void remove (ModuleDefinition definition) {
        myModuleDefinitions.remove(definition);
    }

    public void setMovementDefinition (MovementDefinition definition) {
        myMovementDefinition = definition;
    }

    public void setType (String type) {
        myType = type;
    }

    @Override
    public ProfileDefinition getProfileDefinition () {
        return myProfileDefinition;
    }

    @Override
    public void setProfileDefinition (ProfileDefinition profileDef) {
        this.myProfileDefinition = profileDef;
        
    }
}
