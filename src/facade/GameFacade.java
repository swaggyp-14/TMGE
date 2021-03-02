package facade;

import player.Player;
import player.PlayerManager;
import util.FileUtility;

import java.io.IOException;
import java.util.LinkedList;

public class GameFacade {
    PlayerManager pm;
    FileUtility util;
    public GameFacade() throws Exception {
        util = new FileUtility();
        pm = util.loadPlayers();
    }
    public String getUserID(){
        // null here
        return pm.getActivePlayer().getUsername();
    }
    public void updateScore(int score) {
        int currScore = pm.getActivePlayer().getCurrScore();
        this.pm.getActivePlayer().setCurrScore(currScore + score);
    }
    public String getScore() {
        return Integer.toString(this.pm.getActivePlayer().getCurrScore());
    }
    public void handleExit(String gameName) throws IOException {
        this.pm.getActivePlayer().updateScore(gameName, this.pm.getActivePlayer().getCurrScore());
        this.pm.getActivePlayer().setCurrScore(0);
        this.pm.setQueue(new LinkedList<Player>());
        util.savePlayers(pm);
        System.exit(0);
    }
    public boolean hasNextPlayer() {
        System.out.println(pm.getQueue().size());
        if (pm.getQueue().size() == 1) {
            return false;
        } else {
            return true;
        }
    }
    public void handleSwitch(String gameName) throws IOException {
        this.pm.getActivePlayer().updateScore(gameName, this.pm.getActivePlayer().getCurrScore());
        this.pm.getActivePlayer().setCurrScore(0);
        this.pm.switchPlayers();
        this.pm.getActivePlayer().setCurrScore(0);
        util.savePlayers(pm);
        System.out.println(this.pm.getActivePlayer());
    }
}
