package player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PlayerManager implements Serializable {
    private Map<String, Player> playerMap;
    private Player activePlayer;

    public PlayerManager() {
        this.playerMap = new HashMap<>();
    }

    public void addPlayer(String key, Player value) {
        this.playerMap.put(key, value);
    }

    public Player getPlayer(String key) {
        return this.playerMap.get(key);
    }

    public boolean isValidPlayer(String username) {
        try {
            this.playerMap.get(username);
        } catch (Exception e) {
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
    public void printAllPlayers() {
        for (Map.Entry<String, Player> entry : playerMap.entrySet())
            System.out.println(entry.getValue());
    }
}
