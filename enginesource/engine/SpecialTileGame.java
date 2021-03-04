package engine;
import javax.swing.*;
import java.awt.*;

public interface SpecialTileGame {
    void createEngineInstance();

    void setGameName();

    void setGameInstance();

    void setScreenDimensions();

    void setTileMap();

    void setupTileGen();

    void initGamePanels();

    void setScorePerClear();

    void generateSpecialTiles(int count);

    void addSpecialTiles(String name, Color color);

    JFrame getFrame() throws Exception;
}
