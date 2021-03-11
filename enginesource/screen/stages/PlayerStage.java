package screen.stages;

import engine.EngineAPI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import player.Player;
import player.PlayerManager;
import util.FileUtility;

import javax.swing.*;
import java.io.IOException;
import java.util.Queue;

public class PlayerStage implements IStage{
    private PlayerManager pm;
    private FileUtility util;
    private JFrame gameOneStage;
    private JFrame gameTwoStage;
    public PlayerStage(PlayerManager pm) {
        this.pm = pm;
        util = new FileUtility();
    }
    public PlayerStage(PlayerManager pm, JFrame s1, JFrame s2) {
        this.pm = pm;
        util = new FileUtility();
        this.gameOneStage = s1;
        this.gameTwoStage = s2;
    }
    public void save(String game) {
        try {
            pm.getActivePlayer().addGamesPlayed(game);
            util.savePlayers(pm);
        } catch (Exception e) {
            System.out.println("Unable to save");
        }
    }
    public Stage getStage() {
        Font font = new Font("Arial", 14);
        Stage stage = new Stage();
        stage.setTitle("Player Screen");
        Label playerName = new Label("Player name: " + this.pm.getActivePlayer().getUsername());
        playerName.setFont(font);
        Label playerWins = new Label("Total Wins: " + this.pm.getActivePlayer().getWins());
        playerWins.setFont(font);
        Label playerLose = new Label("Total Losses: " + this.pm.getActivePlayer().getLoses());
        playerLose.setFont(font);
        Button gameOne = new Button("Launch Bejeweled");
        gameOne.setOnAction(event -> {
            gameOneStage.setVisible(true);
            gameOneStage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            save("Bejeweled");
        });
        Button gameTwo = new Button("Launch Candy Crush");
        gameTwo.setOnAction(event -> {
            gameTwoStage.setVisible(true);
            gameTwoStage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            save("Candy Crush");
        });
        // Set Pane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(playerName, 0, 0);
        grid.add(playerWins, 1, 0);
        grid.add(playerLose, 2, 0);
        grid.add(gameOne, 0, 1);
        grid.add(gameTwo, 1, 1);

        stage.setScene(new Scene(grid, 500, 350));
        return stage;
    }
}
