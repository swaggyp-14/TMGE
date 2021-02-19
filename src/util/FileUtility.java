package util;

import player.PlayerManager;

import java.io.*;

public class FileUtility {
    public boolean savePlayers(Object ob) throws IOException {
        FileOutputStream file = new FileOutputStream("players");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(ob);
        out.close();
        file.close();
        return true;
    }
    public PlayerManager loadPlayers() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("players");
        ObjectInputStream in = new ObjectInputStream(file);
        PlayerManager pm = (PlayerManager)in.readObject();
        in.close();
        file.close();
        return pm;
    }
}
