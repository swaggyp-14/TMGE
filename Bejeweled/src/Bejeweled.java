import engine.*;
import gamedata.Tile;
import gamedata.TileMap;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bejeweled {
    private String gameName = "Bejeweled";
    private EngineAPI e;
    private TileMap map;
    private String[] colors = {"COLORMATCH", "CROSS"};
    // Randomly place special tiles before game begins
    public void generateRandomSpecialTiles() {
        int specialTiles = 2;
        int rows = map.getRow();
        int cols = map.getColumn();
        Random random = new Random();
        int randX = random.nextInt(rows);
        int randY = random.nextInt(cols);
        Tile t = new Tile(randX, randY, colors[random.nextInt(specialTiles)]);
        map.placeTile(randX, randY, t);
    }
    public JFrame getStage() throws Exception {
        // TODO - Add more customizations etc later
        e = new EngineAPI();
        e.setTileDimensions(8, 8);
        e.initGame(gameName);
        map = e.getTileMap();
        // Add special tiles
        e.addColor("COLORMATCH", Color.cyan);
        e.addColor("CROSS", Color.white);
        for(int i = 0; i < 15; i++) {
            generateRandomSpecialTiles();
        }
        e.setGameScreenSize(1000, 800);
        e.setGamePanels();
        e.setScorePerClear(150);
        return e.getFrame();
    }
}
