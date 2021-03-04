import engine.*;
import gamedata.Tile;
import gamedata.TileMap;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Bejeweled implements SpecialTileGame {
    private EngineAPI e;
    private TileMap map;
    private String gameName;
    private String[] colors = {"COLORMATCH", "CROSS"};
    private int rows, cols, specialTileCount;
    private int width, height;
    private Random random;

    public void createEngineInstance() {
        e = new EngineAPI();
    }

    public void setGameName() {
        this.gameName = "Bejeweled";

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
    public void generateSpecialTiles(int count) {
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
        e.setScorePerClear(150);
    }
    public JFrame getFrame() throws Exception {
        createEngineInstance();
        setGameName();
        setTileMap();
        setGameInstance();
        setupTileGen();
        // Add special tiles
        addSpecialTiles("COLORMATCH", Color.cyan);
        addSpecialTiles("CROSS", Color.white);

        generateSpecialTiles(15);
        setScreenDimensions();
        initGamePanels();
        setScorePerClear();
        return e.getFrame();
    }
}
