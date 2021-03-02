package player;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    private String username;
    private int currScore;
    private Map<String, Integer> highScore;
    private int wins;
    private int loses;
    private Set<String> gamesPlayed;

    public Player(String username) {
        this.username = username;
        this.highScore = new HashMap<>();
        this.currScore = 0;
        this.wins = 0;
        this.loses = 0;
        this.gamesPlayed = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public int getCurrScore() {
        return currScore;
    }

    public void setCurrScore(int currScore) {
        this.currScore = currScore;
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
    public Map<String, Integer> getHighScoreMap() {
        return this.highScore;
    }
    public void updateScore(String gameName, int score) {
        try {
            if (highScore.get(gameName) < score) {
                highScore.put(gameName, score);
            }
        } catch (Exception e) {
            highScore.put(gameName, 0);
            updateScore(gameName, score);
        }
    }

    public void addGamesPlayed(String gameName) {
        this.gamesPlayed.add(gameName);
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", currScore=" + currScore +
                ", highScore=" + highScore +
                ", wins=" + wins +
                ", loses=" + loses +
                ", gamesPlayed=" + gamesPlayed +
                '}';
    }
}
