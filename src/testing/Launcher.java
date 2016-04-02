package testing;

import gameplayer.GamePlayer;
import javafx.application.Application;
import javafx.stage.Stage;


public class Launcher extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        GamePlayer player = new GamePlayer(null);
        player.play();
    }

}
