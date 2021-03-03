package engine;

import java.awt.*;

public interface SpecialTileGame {
    void createGameInstance();

    void setGameName();

    void setGameInstance();

    void setScreenDimensions();

    void setTileMap();

    void setupTileGen();

    void setScorePerClear();

    void generateSpecialTiles(int count);

    void addSpecialTiles(String name, Color color);
}
