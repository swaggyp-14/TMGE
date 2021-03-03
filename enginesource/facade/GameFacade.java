package facade;

import player.Player;
import player.PlayerManager;
import util.FileUtility;

import java.io.IOException;
import java.util.LinkedList;

public class GameFacade {

    FileUtility util = new FileUtility();
    PlayerManager pm;

    public String getUserID() throws IOException, ClassNotFoundException {
        load();
        return pm.getActivePlayer().getUsername();
    }
    public void updateScore(int score) throws IOException, ClassNotFoundException {
        load();
        int currScore = pm.getActivePlayer().getCurrScore();
        this.pm.getActivePlayer().setCurrScore(currScore + score);
        save();
    }
    public int getPreviousHigh(String gameName){
        load();
        try {
            return this.pm.getActivePlayer().getHighScoreMap().get(gameName);
        } catch (Exception e){
            return 0;
        }
    }
    public String getScore() {
        load();
        return Integer.toString(this.pm.getActivePlayer().getCurrScore());
    }
    public void handleExit(String gameName) throws IOException {
        load();
        this.pm.getActivePlayer().updateScore(gameName, this.pm.getActivePlayer().getCurrScore());
        this.pm.getActivePlayer().setCurrScore(0);
        this.pm.setQueue(new LinkedList<Player>());
        save();
        System.exit(0);
    }
    public boolean hasNextPlayer() {
        load();
        if (pm.getQueue().size() == 1) {
            return false;
        } else {
            return true;
        }
    }
    public void handleSwitch(String gameName) throws IOException {
        load();
        this.pm.getActivePlayer().updateScore(gameName, this.pm.getActivePlayer().getCurrScore());
        this.pm.getActivePlayer().setCurrScore(0);
        this.pm.switchPlayers();
        this.pm.getActivePlayer().setCurrScore(0);
        save();
        System.out.println(this.pm.getActivePlayer());
    }
    private void load() {
        pm = util.loadPlayers();
    }
    private void save() {
        util.savePlayers(pm);
    }
}
