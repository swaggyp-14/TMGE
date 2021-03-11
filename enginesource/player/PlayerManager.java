package player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PlayerManager implements Serializable {
    private Map<String, Player> playerMap;
    private Queue<Player> playerQueue;

    public PlayerManager() {
        this.playerMap = new HashMap<>();
        this.playerQueue = new LinkedList<>();
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
    public void addPlayerQueue(Player player) {
        this.playerQueue.add(player);
    }
    public Player getActivePlayer() {
        return this.playerQueue.peek();
    }
    public void switchPlayers() {
        if (this.playerQueue.size() != 0) {
            this.playerQueue.poll();
        }
    }
    public void setQueue(LinkedList<Player> q) {
        this.playerQueue = q;
    }
    public Queue<Player> getQueue() {
        return this.playerQueue;
    }
    public void printAllPlayers() {
        for (Map.Entry<String, Player> entry : playerMap.entrySet())
            System.out.println("!!" + entry.getValue());
    }
}
