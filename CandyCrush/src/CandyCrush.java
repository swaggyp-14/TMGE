import engine.*;
import gamedata.Tile;
import gamedata.TileMap;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class CandyCrush implements SpecialTileGame {
    private EngineAPI e;
    private TileMap map;
    private String gameName;
    private String[] colors = {"COLORMATCH", "XLINE", "YLINE"};
    private int rows, cols, specialTileCount;
    private int width, height;
    private Random random;

    public void createEngineInstance() {
        e = new EngineAPI();
    }

    public void setGameName() {
        this.gameName = "Candy Crush";

    }

    public void setGameInstance() {
        try {
            e.initGame(this.gameName);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setScreenDimensions() {
        width = 1000;
        height = 800;
        e.setGameScreenSize(width, height);
    }

    public void setTileMap() {
        rows = 8;
        cols = 8;
        e.setTileDimensions(rows, cols);
    }
    public void setupTileGen() {
        specialTileCount = this.colors.length;
        random = new Random();
    }
    public void generateSpecialTiles(int count) { // randomly generates special tiles at random locations
        for(int i = 0; i < count; i++) {
            int randX = random.nextInt(rows);
            int randY = random.nextInt(cols);
            Tile t = new Tile(randX, randY, colors[random.nextInt(specialTileCount)]);
            map.placeTile(randX, randY, t);
        }
    }

    public void addSpecialTiles(String name, Color color) {
        map = e.getTileMap();
        e.addColor(name, color);
    }
    public void initGamePanels() {
        try {
            e.setGamePanels();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
    public void setScorePerClear() {
        e.setScorePerClear(100);
    }

    public JFrame getFrame() throws Exception {
        createEngineInstance();
        setGameName();
        setTileMap();
        setGameInstance();
        setupTileGen();
        // Add special tiles
        addSpecialTiles("COLORMATCH", Color.white);
        addSpecialTiles("YLINE", Color.black);
        addSpecialTiles("XLINE",Color.gray);

        setScreenDimensions();
        initGamePanels();
        setScorePerClear();
        return e.getFrame();
    }
}
