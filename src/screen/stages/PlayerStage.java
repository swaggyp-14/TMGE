package screen.stages;

import engine.EngineAPI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import player.PlayerManager;
import util.FileUtility;

import java.io.IOException;

public class PlayerStage implements IStage{
    private PlayerManager pm;
    private FileUtility util;
    public PlayerStage(PlayerManager pm) {
        this.pm = pm;
        util = new FileUtility();
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
        Stage stage = new Stage();
        stage.setTitle("Player Screen");
        Label playerName = new Label("Player name: " + this.pm.getActivePlayer().getUsername());
        Label playerWins = new Label("Total Wins: " + this.pm.getActivePlayer().getWins());
        Label playerLose = new Label("Total Losses: " + this.pm.getActivePlayer().getLoses());
        Button launchBejeweled = new Button("Launch Bejeweled");
        launchBejeweled.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Implement game. Testing code for now
                EngineAPI api = new EngineAPI();
                api.setTileDimensions(5,5);
                try {
                    api.initGame("Bejeweled");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                api.setGameScreenSize(800, 500);
                api.setGamePanels();
                api.setScorePerClear(150);
                api.launchGame();
                System.out.println("Launching Bejeweled");
                save("Bejeweled");
            }
        });
        Button launchCandyCrush = new Button("Launch Candy Crush");
        launchCandyCrush.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Implement game
                System.out.println("Launching Candy Crush");
                save("Candy Crush");
            }
        });
        // Set Pane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(playerName, 0, 0);
        grid.add(playerWins, 1, 0);
        grid.add(playerLose, 2, 0);
        grid.add(launchBejeweled, 0, 1);
        grid.add(launchCandyCrush, 1, 1);

        stage.setScene(new Scene(grid, 500, 350));
        return stage;
    }
}
