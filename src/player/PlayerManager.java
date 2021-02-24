package player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PlayerManager implements Serializable {
    private Map<String, Player> playerMap;
    private Player activePlayer;
    private Player playerTwo;

    public PlayerManager() {
        this.playerMap = new HashMap<>();
        this.activePlayer = null;
        this.playerTwo = null;
    }
    public void addPlayer(String key, Player value) {
        this.playerMap.put(key, value);
    }
    public Player getPlayer(String key) {
        return this.playerMap.get(key);
    }
    public boolean isValidPlayer(String username) {
        try {
            if (!this.playerMap.get(username).equals(null)) {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
    public void setActivePlayer(Player p){
        this.activePlayer = p;
    }
    public Player getActivePlayer() {
        return this.activePlayer;
    }
    public Player getPlayerTwo() {
        return playerTwo;
    }
    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }
    public void switchPlayers() {
        Player temp = activePlayer;
        activePlayer = playerTwo;
        playerTwo = temp;
    }
    public boolean isSinglePlayer() {
        if(playerTwo.equals(null)){
            return true;
        }
        return false;
    }
    public void printAllPlayers() {
        for (Map.Entry<String, Player> entry : playerMap.entrySet())
            System.out.println(entry.getValue());
    }
}
