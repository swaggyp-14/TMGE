package player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PlayerManager implements Serializable {
    private Map<String, Player> playerMap;
    public PlayerManager() {
        this.playerMap = new HashMap<>();
    }

    public void addPlayer(String key, Player value) {
        this.playerMap.put(key, value);
    }
    public Player getPlayer(String key) {
       return this.playerMap.get(key);
    }
}
