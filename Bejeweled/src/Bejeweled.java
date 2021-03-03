import engine.*;

import javax.swing.*;

public class Bejeweled {
    private String gameName = "Bejeweled";
    private EngineAPI e;
    public JFrame getStage() throws Exception {
        // TODO - Add more customizations etc later
        e = new EngineAPI();
        e.setTileDimensions(8, 8);
        e.initGame(gameName);
        e.setGameScreenSize(1000, 800);
        e.setGamePanels();
        e.setScorePerClear(150);
        return e.getFrame();
    }
}
