package engine.rendering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import engine.Drawable;
import engine.IGamePlayable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


/**
 * Used to render the back-end components into JavaFX responsive objects for the screen
 * 
 * @author RyanStPierre
 *
 */
public class InGameRenderer extends LevelRenderer {

    private IGraphicFactory myFactory;
    private IGamePlayable myGame;
    private Map<Drawable, Node> myDrawNodeMap;
    private boolean firstTime;

    public InGameRenderer (IGamePlayable game, Pane pane) {
        super(pane);
        myFactory = new UnscaledFactory();
        myGame = game;
        myDrawNodeMap = new HashMap<>();
        firstTime = true;
    }


    @Override
    void drawSprites () {
        List<Node> currentEngineConvertedNodeList = getAndUpdateEngineNodeList();
        removeScreenNodesNotInEngine(currentEngineConvertedNodeList);
        updateExistingNodeLocations();
    }
    
    @Override
    public void render () {
        if (firstTime) {
            drawBackground(getBackgroundURL());
            firstTime = false;
        }
        drawSprites();
    }

    @Override
    String getBackgroundURL () {
        return myGame.getBackroundImage().getUrlProperty().get();
    }
    


    private void updateExistingNodeLocations () {
        myDrawNodeMap.keySet().stream().forEach(drawable -> myDrawNodeMap.get(drawable)
                .relocate(drawable.getLocation().getX(), drawable.getLocation().getY()));
    }

    /**
     * This method will remove nodes from JavaFX render tree if the does not report them as listing
     * in its list of drawables
     */
    private void removeScreenNodesNotInEngine (List<Node> engineNodes) {
        getCurrentDrawnNodes()
                .removeIf(node -> checkEngineContainsAndUpdateDrawNodeMapEntry(engineNodes, node));
    }

    private boolean checkEngineContainsAndUpdateDrawNodeMapEntry (List<Node> engineNodes,
                                                                  Node node) {
        boolean shouldRemove = !engineNodes.contains(node);
        if (shouldRemove) {
            for (Drawable draw : getKeysForNode(myDrawNodeMap, node)) {
                myDrawNodeMap.remove(draw);
            }
        }
        return shouldRemove;
    }

    private List<Drawable> getKeysForNode (Map<Drawable, Node> map, Node node) {
        return map.keySet().stream().filter(draw -> map.get(draw) == node)
                .collect(Collectors.toList());
    }

    private List<Node> getCurrentDrawnNodes () {
        return this.getPane().getChildren();
    }

    /**
     * This method will check the engine drawable lists, add new ones to the
     * internal list of maps and add them to the
     * 
     * @return List<Node> of node objects that are currently represented in the engine
     */
    private List<Node> getAndUpdateEngineNodeList () {
        return myGame.getDrawables().stream().map(drawable -> getNodeForDrawableAddNew(drawable))
                .collect(Collectors.toList());
    }

    private Node getNodeForDrawableAddNew (Drawable drawn) {
        if (myDrawNodeMap.containsKey(drawn)) {
            return myDrawNodeMap.get(drawn);
        }
        else {
            Node node = drawn.getDrawer().getVisualRepresentation(myFactory);
            myDrawNodeMap.put(drawn, node);
            add(node);
            return node;
        }
    }



    private void add (Node node) {
        this.getPane().getChildren().add(node);
    }

}
