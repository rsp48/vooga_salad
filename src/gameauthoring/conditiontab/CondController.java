package gameauthoring.conditiontab;

import engine.IGame;
import javafx.scene.layout.Pane;


/**
 * Class name condition controller taken
 * 
 * @author RyanStPierre
 *
 */
public class CondController {

    private ConditionView myView;
    private ConditionPopUpFactory myFactory;

    public CondController (ConditionView conditionView, IGame game) {
        myView = conditionView;
        myFactory = new ConditionPopUpFactory(game);
        setActions();

    }

    private void setActions () {
       
        myView.getButton().setOnAction(e-> myView.setRight(createPopUp(myView.getBox().getSelectionModel().getSelectedItem())));
       
        
    }

    private Pane createPopUp (String selection) {
        return myFactory.get(selection, myView.getList()).show();
    }

}