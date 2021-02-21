package util;

import player.PlayerManager;

import java.io.*;

public class FileUtility {
    public void savePlayers(Object ob) throws IOException {
        FileOutputStream file = new FileOutputStream("players");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(ob);
        out.close();
        file.close();
    }
    public PlayerManager loadPlayers() throws IOException, ClassNotFoundException {
        //If file not exist, make
        FileInputStream file;
        try {
            file = new FileInputStream("players");
        } catch (Exception e) {
            savePlayers(new PlayerManager());
            file = new FileInputStream("players");
        }
        ObjectInputStream in = new ObjectInputStream(file);
        PlayerManager pm = (PlayerManager)in.readObject();
        in.close();
        file.close();
        return pm;
    }
}
