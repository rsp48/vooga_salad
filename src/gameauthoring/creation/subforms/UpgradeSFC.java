package gameauthoring.creation.subforms;

import engine.AttributeType;
import engine.AuthorshipData;
import engine.IGame;
import engine.definitions.SpriteDefinition;
import engine.definitions.upgrades.GlobalUpgradeDefinition;
import engine.definitions.upgrades.SpriteUpgradeDefinition;
import engine.definitions.upgrades.UpgradeDefinition;
import gameauthoring.shareddata.IDefinitionCollection;


public class UpgradeSFC implements ISubFormControllerSprite {

    private UpgradeSFV mySFV;
    private IGame myGame;
    private UpgradeDefinition myGlobalUpgrade;
    private UpgradeDefinition mySpriteUpgrade;

    public UpgradeSFC (IGame game) {
        mySFV = new UpgradeSFV(game.getAuthorshipData());
        this.myGame = game;
        this.myGlobalUpgrade = new GlobalUpgradeDefinition();
        this.mySpriteUpgrade = new SpriteUpgradeDefinition();
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        if (mySFV.isUpgradableProperty().get()) {
            UpgradeDefinition upgrade;
            double cost =
                    Double.parseDouble(mySFV.getData().getValueProperty(mySFV.getMyCostKey())
                            .get());
            AttributeType type = new AttributeType(mySFV.getDepeltedAttribute().getType());
            SpriteDefinition nextUpgrade = mySFV.getNextUpgrade();
            if (mySFV.isGlobalProperty().get()) {
                upgrade = new GlobalUpgradeDefinition(myGame, nextUpgrade, type, cost);
            }
            else {
                upgrade = new SpriteUpgradeDefinition(myGame, nextUpgrade, type, cost);

            }
            item.addModule(upgrade);
        }
    }


    @Override
    public ISubFormView getSubFormView () {
        return mySFV;
    }

    @Override
    public void initializeFields () {
        mySFV.getData().set(mySFV.getMyCostKey(), "0.0"); //TODO: resource file
    }

}
