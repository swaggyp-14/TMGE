package facade;

import javafx.stage.Stage;
import player.Player;
import player.PlayerManager;
import screen.stages.PlayerStage;
import util.FileUtility;

import javax.swing.*;
import java.io.IOException;

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

    public void handleLogin(String username, String usernamePlayer2) throws IOException {
        if (usernamePlayer2.equals("")) {
            handleSingleLogin(username);
        } else {
            handleDualLogin(username, usernamePlayer2);
        }
    }

    private void handleSingleLogin(String username) {
        System.out.println("One login");
        if (pm.isValidPlayer(username)) { //Login is valid
            pm.addPlayerQueue(pm.getPlayer(username));
            System.out.println(pm.getPlayer(username));
        } else {
            System.out.println("User does not exist. Register an account");
        }
        util.savePlayers(pm);
    }

    private void handleDualLogin(String u1, String u2){
        System.out.println("Two logins");
        if (pm.isValidPlayer(u1) && pm.isValidPlayer(u2)) {
            pm.addPlayerQueue(pm.getPlayer(u1));
            pm.addPlayerQueue(pm.getPlayer(u2));
        } else {
            System.out.println("One of the users are not valid. Register an account(s)");
        }
        util.savePlayers(pm);
    }

    public PlayerManager getPlayerManager() {
        return this.pm;
    }

    public void goPlayerScreen() {
        new PlayerStage(this.pm).getStage().show();
    }

    public void goPlayerScreen(JFrame s1, JFrame s2) {
        new PlayerStage(this.pm, s1, s2).getStage().show();
    }
}
