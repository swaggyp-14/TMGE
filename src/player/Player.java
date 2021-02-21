package player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Serializable {
    private String username;
    private Map<String, Integer> highScore;
    private int wins;
    private int loses;
    private List<String> gamesPlayed;

    public Player(String username) {
        this.username = username;
        this.highScore = new HashMap<>();
        this.wins = 0;
        this.loses = 0;
        this.gamesPlayed = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public void updateScore(String gameName, int score) {
        if (highScore.get(gameName) < score) {
            highScore.put(gameName, score);
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", wins=" + wins +
                ", loses=" + loses +
                ", gamesPlayed=" + gamesPlayed +
                '}';
    }
}
