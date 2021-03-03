package engine;

import gamedata.TileMap;
import game.Game;

import javax.swing.*;
import java.io.IOException;

public class EngineAPI {
    private TileMap tileMap;
    private Game game;

    public EngineAPI() {
    }

    public void setTileMap(TileMap tm) {
        this.tileMap = tm;
    }

    public TileMap getTileMap() {
        return this.tileMap;
    }

    public void setTileDimensions(int rows, int cols) {
        this.tileMap = new TileMap(rows, cols);
    }

    public void initGame(String gameName) throws Exception {
        this.game = new Game(gameName, tileMap);
        this.game.setGameName();
    }

    public void setGameScreenSize(int width, int height) {
        game.setGameDimensions(width, height);
    }

    public void setScorePerClear(int score) {
        this.game.setScorePerClear(score);
    }

    public void setGamePanels() throws IOException, ClassNotFoundException {
        game.addAllComponents();
    }
    public JFrame getFrame() {
        return game.getFrame();
    }
    // TODO - Add more interfaces that the game needs to implement
}
