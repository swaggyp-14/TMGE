package screen.stages;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import player.PlayerManager;

public class PlayerStage implements IStage{
    private PlayerManager pm;
    public PlayerStage(PlayerManager pm) {
        this.pm = pm;
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
                // TODO Implement game
                System.out.println("Launching Bejeweled");
            }
        });
        Button launchCandyCrush = new Button("Launch Candy Crush");
        launchCandyCrush.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Implement game
                System.out.println("Launching Candy Crush");
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
