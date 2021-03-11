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
    private int rows, cols, specialTileCount, inactiveTileCount;
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
    public void deactivateTiles() {
        inactiveTileCount = 10;
        map.deactivateRandomTiles(inactiveTileCount);
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

    public void addCustomAssets() {
        map = e.getTileMap();
        e.addAsset("R" , Color.RED, "CandyCrush/resources/candyCane.jpeg");
        e.addAsset("B" , Color.BLUE, "CandyCrush/resources/redSwirlly.jpg");
        e.addAsset("P" , Color.PINK, "CandyCrush/resources/Green.png");
        e.addAsset("Y" , Color.YELLOW, "CandyCrush/resources/Orange.png");
        e.addAsset("G" , Color.GREEN, "CandyCrush/resources/pinkSwirl.jpg");
        e.addAsset("O" , Color.ORANGE, "CandyCrush/resources/redStripes.jpg");
        e.addAsset("COLORMATCH", Color.cyan, "CandyCrush/resources/choco.png");
        e.addAsset("CROSS", Color.white, "CandyCrush/resources/holiday.png");
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


        addSpecialTiles("COLORMATCH", Color.cyan);
        addSpecialTiles("XLINE", Color.white);
        addSpecialTiles("YLINE", Color.gray);

        deactivateTiles();

//        addSpecialTiles("YLINE", Color.black);
//        addSpecialTiles("XLINE",Color.gray);
        addCustomAssets();
        setScreenDimensions();
        initGamePanels();
        setScorePerClear();
        return e.getFrame();
    }
}
