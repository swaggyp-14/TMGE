package util;

import player.PlayerManager;

import java.io.*;

public class FileUtility {
    public boolean savePlayers(Object ob, String filename) throws IOException {
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(ob);
        out.close();
        file.close();
        return true;
    }
    public PlayerManager loadPlayers(String filename) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(file);
        PlayerManager pm = (PlayerManager)in.readObject();
        in.close();
        file.close();
        return pm;
    }
}
