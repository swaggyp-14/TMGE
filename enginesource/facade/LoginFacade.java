package facade;

import javafx.scene.control.TextField;
import player.Player;
import player.PlayerManager;
import screen.stages.PlayerStage;
import util.FileUtility;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class LoginFacade {
    public PlayerManager pm;
    FileUtility util;

    public LoginFacade() {
        util = new FileUtility();
        pm = util.loadPlayers();
    }

    public void createAccount(String username) {
        pm.addPlayer(username, new Player(username));
        util.savePlayers(pm);
        pm.printAllPlayers();
    }

    private boolean verifyLogins(List<TextField> inputs) {
        for (TextField temp : inputs) {
            if(temp.getText().equals("")) {
                continue;
            }
            if (!pm.isValidPlayer(temp.getText())) {
                return false;
            }
        }
        return true;
    }
    public void handleLogin(List<TextField> inputs) throws IOException {
        if(verifyLogins(inputs)) {
            for (TextField temp : inputs) {
                if(temp.getText().equals("")) {
                    continue;
                } else {
                    pm.addPlayerQueue(pm.getPlayer(temp.getText()));
                    System.out.println(pm.getPlayer(temp.getText()));
                }
            }
        } else {
            System.out.println("One or more of the logins you supplied are not valid");
        }
        util.savePlayers(pm);
    }

    public void goPlayerScreen(JFrame s1, JFrame s2) {
        new PlayerStage(this.pm, s1, s2).getStage().show();
    }
}
