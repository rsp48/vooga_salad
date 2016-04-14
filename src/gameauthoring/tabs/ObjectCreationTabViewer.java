package gameauthoring.tabs;

import gameauthoring.creation.forms.CreationController;
import gameauthoring.creation.forms.CreationControllerFactory;
import gameauthoring.creation.forms.IObjectCreationView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.AuthorshipData;
import engine.Game;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


/**
 * Character(Sprite) tab view class which allows users to create all weapons, enemies, defenders,
 * obstacles, and interactions. Handles selection between these sub-tabs. Serves to make creation
 * controller and subform controller using factory design.
 * 
 * @TODO: Resourcebundle for unprotectedString
 * @TODO: Interaction, Obstacle subtabs.
 * @author Jin An
 *
 */
public class ObjectCreationTabViewer implements ITabViewer {

    private TabPane myTabPane;

    private AuthorshipData myAuthorshipData;

    private List<CreationController<?>> myCCs;
    private List<IObjectCreationView<?>> myCVs;

    public ObjectCreationTabViewer () {
        initializeLists();
        init();
    }

    public ObjectCreationTabViewer (Game game) {
        myAuthorshipData = game.getAuthorshipData();
        initializeLists();
        init();
    }

    private void initializeLists () {

        List<String> myAttributeSFCs = new ArrayList<String>(Arrays.asList("Attribute"));
        List<String> myMissileSFCs = new ArrayList<String>();
        List<String> myEnemySFCs =
                new ArrayList<String>(Arrays.asList("SelectAttribute", "Movement"));
        List<String> myDefenderSFCs =
                new ArrayList<String>(Arrays.asList("SelectAttribute", "Movement", "Firing"));

        List<String> myEventSFCs = new ArrayList<String>(Arrays.asList("Events"));

        List<String> myGroupSFCs = new ArrayList<>(Arrays.asList("SelectSprite"));

        CreationControllerFactory ccFactory = new CreationControllerFactory();
        // TODO: take sfcs out of cc constructors

        CreationController<?> ccAttributes =
                ccFactory.createAttributeCreationController("Attribute", myAttributeSFCs,
                                                            myAuthorshipData);
        CreationController<?> ccMissiles =
                ccFactory.createSpriteCreationController("Missiles", myMissileSFCs,
                                                         myAuthorshipData);
        CreationController<?> ccEnemies =
                ccFactory.createSpriteCreationController("Enemies", myEnemySFCs,
                                                         myAuthorshipData);
        CreationController<?> ccDefenders =
                ccFactory.createSpriteCreationController("Defender", myDefenderSFCs,
                                                         myAuthorshipData);
        CreationController<?> ccEvents =
                ccFactory.createEventCreationController("Events", myEventSFCs, myAuthorshipData);

        CreationController<?> ccGroups =
                ccFactory.createGroupCC("Groups", myGroupSFCs, myAuthorshipData);

        myCCs = new ArrayList<CreationController<?>>();

        myCCs.add(ccAttributes);
        myCCs.add(ccEnemies);
        myCCs.add(ccDefenders);

        myCCs.add(ccEvents);

        myCCs.add(ccMissiles);
        myCCs.add(ccGroups);

        ccAttributes.init(myAttributeSFCs);
        ccEnemies.init(myEnemySFCs);
        ccDefenders.init(myDefenderSFCs);

        ccEvents.init(myEventSFCs);

        ccMissiles.init(myMissileSFCs);
        ccGroups.init(myGroupSFCs);

    }

    @Override
    public void init () {
        myTabPane = new TabPane();
        generateCreationViewList();
        generateAllSubTabs();
    }

    private void generateCreationViewList () {
        myCVs = new ArrayList<IObjectCreationView<?>>();
        for (CreationController<?> cc : myCCs) {
            myCVs.add(cc.getMyObjectCreationView());
        }
    }

    private void generateAllSubTabs () {
        for (int i = 0; i < myCCs.size(); i++) {
            Tab tab = new Tab(myCCs.get(i).getMyTitle());
            tab.setClosable(false);
            tab.setContent(myCVs.get(i).draw());
            myTabPane.getTabs().add(tab);
        }
    }

    @Override
    public Node draw () {
        return myTabPane;
    }
}
