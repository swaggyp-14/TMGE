package screen;
import javafx.application.Application;
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
import player.Player;
import player.PlayerManager;
import util.FileUtility;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Login {
    private HBox loginBox;
    private HBox registerBox;

    public void createAccount(String username) throws IOException, ClassNotFoundException {
        FileUtility util = new FileUtility();
        PlayerManager pm;
        try {
            pm = util.loadPlayers();
        } catch (FileNotFoundException e) {
            pm = new PlayerManager();
            util.savePlayers(pm);
        }
        pm.addPlayer(username, new Player(username));
        util.savePlayers(pm);
        pm.print();
    }
    public Stage getStage() {
        // Start Login Element
        Stage stage = new Stage();
        stage.setTitle("Login Screen");
        loginBox = new HBox();
        Label loginLabel = new Label("Login ID:");
        TextField loginField = new TextField();
        Button loginBtn = new Button();
        loginBtn.setText("Login");
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Serialize the active user, the next screen reads it and displays the info
                // Also make sure the login ID is in PlayerManager
                stage.close();
                System.out.println(loginField.getText());
                new Login().getStage().show(); // Replace this
            }
        });

        loginBox.getChildren().addAll(loginLabel, loginField, loginBtn);
        loginBox.setSpacing(10);
        // End Login Elements

        // Start Register Elements
        registerBox = new HBox();
        Label regLabel = new Label("New? Register Login ID:");
        TextField regField = new TextField();
        Button regBtn = new Button();
        regBtn.setText("Register");
        regBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    createAccount(regField.getText());
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
        grid.add(registerBox, 0, 2);
        stage.setScene(new Scene(grid, 500, 350));
        return stage;
    }
}
