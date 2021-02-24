package screen;

import facade.LoginFacade;
import javafx.application.Application;
import javafx.stage.Stage;
import screen.stages.LoginStage;

import java.io.IOException;

public class PlayerScreen extends Application {
    private LoginFacade loginFacade;
    public PlayerScreen() throws IOException, ClassNotFoundException {
        loginFacade = new LoginFacade();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
         * Handles the sub-routine of logging in and selecting active player.
         */
        LoginStage loginStage = new LoginStage(this.loginFacade);
        loginStage.getStage().show();
    }
}
