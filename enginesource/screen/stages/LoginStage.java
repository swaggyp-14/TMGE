package screen.stages;
import facade.LoginFacade;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javax.swing.*;
import java.io.IOException;

public class LoginStage implements IStage{
    private LoginFacade loginFacade;
    private JFrame s1, s2;
    public LoginStage(LoginFacade loginFacade, JFrame s1, JFrame s2){
        this.loginFacade = loginFacade;
        this.s1 = s1;
        this.s2 = s2;
    }

    public Stage getStage() {
        // Start Login Element
        Stage stage = new Stage();
        stage.setTitle("Login Screen");
        HBox loginBox = new HBox();
        Label loginLabel = new Label("Login ID Player 1:");
        TextField loginField = new TextField();
        Label loginLabelPlayer2 = new Label("(Not Required) Login ID Player 2:");
        TextField loginFieldPlayer2 = new TextField();
        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(event -> {
            try {
                loginFacade.handleLogin(loginField.getText(), loginFieldPlayer2.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.close();
            loginFacade.goPlayerScreen(s1, s2);
        });

        loginBox.getChildren().addAll(loginLabel, loginField, loginLabelPlayer2, loginFieldPlayer2);
        loginBox.setSpacing(10);
        // End Login Elements

        // Start Register Elements
        HBox registerBox = new HBox();
        Label regLabel = new Label("New? Register Login ID:");
        TextField regField = new TextField();
        Button regBtn = new Button("Register");
        regBtn.setOnAction(event -> {
            try {
                loginFacade.createAccount(regField.getText());
            } catch (Exception e) {
                e.printStackTrace();
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

        Label title = new Label("TMGE");
        title.setFont(new Font("Arial", 30));

        grid.add(title, 0, 0);
        grid.add(loginBox, 0, 1);
        grid.add(loginBtn, 0, 2);
        grid.add(registerBox, 0, 3);
        stage.setScene(new Scene(grid, 700, 350));
        return stage;
    }
}
