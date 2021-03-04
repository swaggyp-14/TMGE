import facade.LoginFacade;
import javafx.application.Application;
import javafx.stage.Stage;
import screen.stages.LoginStage;


public class PlayerScreen extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginFacade loginFacade = new LoginFacade();
        /**
         * Handles the sub-routine of logging in and selecting active player.
         */
        LoginStage loginStage = new LoginStage(loginFacade, new Bejeweled().getFrame(), new Bejeweled().getFrame());
        loginStage.getStage().show();
    }
}