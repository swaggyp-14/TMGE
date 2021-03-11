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
import java.util.ArrayList;
import java.util.List;

public class LoginStage implements IStage{
    private LoginFacade loginFacade;
    private List<TextField> loginInputs;
    private List<Label> loginLabels;
    private int numOfUsers;
    private JFrame s1, s2;
    public LoginStage(LoginFacade loginFacade, JFrame s1, JFrame s2, int numOfUsers){
        this.loginFacade = loginFacade;
        this.s1 = s1;
        this.s2 = s2;
        this.loginInputs = new ArrayList<>();
        this.loginLabels = new ArrayList<>();
        this.numOfUsers = numOfUsers;
    }

    public Stage getStage() {
        // Start Login Element
        Stage stage = new Stage();
        stage.setTitle("Login Screen");
        // Generate login UI
        HBox loginBox = new HBox();
        for(int i = 0; i < this.numOfUsers; i++) {
            this.loginLabels.add(new Label("Login ID Player " + (i + 1) + ": "));
            this.loginInputs.add(new TextField());
        }
        Button loginBtn = new Button("Login");
        loginBtn.setOnAction(event -> {
            try {
                loginFacade.handleLogin(this.loginInputs);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.close();
            loginFacade.goPlayerScreen(s1, s2);
        });
        for(int i = 0; i < this.numOfUsers; i++){
            loginBox.getChildren().add(this.loginLabels.get(i));
            loginBox.getChildren().add(this.loginInputs.get(i));
        }
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
