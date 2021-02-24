package screen.stages;
import facade.LoginFacade;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;

public class LoginStage implements IStage{
    private HBox loginBox;
    private HBox registerBox;
    private LoginFacade loginFacade;
    public LoginStage(LoginFacade loginFacade) throws IOException, ClassNotFoundException {
        this.loginFacade = loginFacade;
    }
    public Stage getStage() {
        // Start Login Element
        Stage stage = new Stage();
        stage.setTitle("Login Screen");
        loginBox = new HBox();
        Label loginLabel = new Label("Login ID Player 1:");
        TextField loginField = new TextField();
        Label loginLabelPlayer2 = new Label("(Not Required) Login ID Player 2:");
        TextField loginFieldPlayer2 = new TextField();
        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginFacade.handleLogin(loginField.getText(), loginFieldPlayer2.getText());
                stage.close();
                loginFacade.goPlayerScreen();
            }
        });

        loginBox.getChildren().addAll(loginLabel, loginField, loginLabelPlayer2, loginFieldPlayer2);
        loginBox.setSpacing(10);
        // End Login Elements

        // Start Register Elements
        registerBox = new HBox();
        Label regLabel = new Label("New? Register Login ID:");
        TextField regField = new TextField();
        Button regBtn = new Button("Register");
        regBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    loginFacade.createAccount(regField.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        registerBox.getChildren().addAll(regLabel, regField, regBtn);
        registerBox.setSpacing(10);
        // End Register Elements

        // Set Pane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(loginBox, 0, 0);
        grid.add(loginBtn, 0, 1);
        grid.add(registerBox, 0, 2);
        stage.setScene(new Scene(grid, 700, 350));
        return stage;
    }
}
