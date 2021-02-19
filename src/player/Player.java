package player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private String username;
    private int maxScore;
    private int wins;
    private int loses;
    private List<String> gamesPlayed;

    public Player(String username) {
        this.username = username;
        this.maxScore = 0;
        this.wins = 0;
        this.loses = 0;
        this.gamesPlayed = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
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

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", maxScore=" + maxScore +
                ", wins=" + wins +
                ", loses=" + loses +
                ", gamesPlayed=" + gamesPlayed +
                '}';
    }
}
