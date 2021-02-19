package driver;

import player.Player;
import player.PlayerManager;
import util.FileUtility;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        FileUtility util = new FileUtility();
        Player p1 = new Player("A1");
        p1.setWins(5);
        p1.setLoses(2);
        p1.setMaxScore(400);
        Player p2 = new Player("A2");
        p2.setWins(9);
        p2.setLoses(10);
        p2.setMaxScore(900);
        PlayerManager pm = new PlayerManager();
        pm.addPlayer(p1.getUsername(), p1);
        pm.addPlayer(p2.getUsername(), p2);
    }
}
