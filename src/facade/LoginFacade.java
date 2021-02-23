package facade;

import player.Player;
import player.PlayerManager;
import screen.stages.PlayerStage;
import util.FileUtility;

import java.io.IOException;

public class LoginFacade {
    PlayerManager pm;
    FileUtility util;
    public LoginFacade() throws IOException, ClassNotFoundException {
        util = new FileUtility();
        pm = util.loadPlayers();
    }
    public void createAccount(String username) throws IOException {
        pm.addPlayer(username, new Player(username));
        util.savePlayers(pm);
        pm.printAllPlayers();
    }
    public void handleLogin(String username) {
        if (pm.isValidPlayer(username)) { //Login is valid
            pm.setActivePlayer(pm.getPlayer(username));
            System.out.println(pm.getActivePlayer());
        } else {
            System.out.println("User does not exist. Register an account");
        }
    }
    public PlayerManager getPlayerManager() {
        return this.pm;
    }
    public void goPlayerScreen() {
        new PlayerStage(this.pm).getStage().show();
    }
}
