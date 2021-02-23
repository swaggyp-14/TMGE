package screen;

import javafx.application.Application;
import javafx.stage.Stage;
import screen.stages.Login;

public class PlayerScreen extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
         * Handles the sub-routine of logging in and selecting active player.
         */
        Login loginStage = new Login();
        loginStage.getStage().show();

    }
}
