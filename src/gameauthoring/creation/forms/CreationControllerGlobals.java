package gameauthoring.creation.forms;

import java.util.List;
import engine.AuthorshipData;
import engine.Game;
import engine.IGame;
import engine.definitions.AttributeDefinition;


/**
 * Stores created attributes in global resources instead of character specific attributes collection
 * in authorship data
 * 
 * @author Joe Lilien
 *
 */
public class CreationControllerGlobals extends CreationController<AttributeDefinition> {

    public CreationControllerGlobals (String title, List<String> subFormStrings, IGame myGame) {
        super(title, subFormStrings, myGame);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void addToAuthorshipData (AuthorshipData authorshipData) {
        System.out.println(getMyDefinitionCollection());
        authorshipData.setMyCreatedGlobals(getMyDefinitionCollection());
    }

    @Override
    protected AttributeDefinition createBlankItem () {
        return new AttributeDefinition();
    }

}
