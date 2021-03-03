package util;

import player.PlayerManager;

import java.io.*;

public class FileUtility {
    public void savePlayers(Object ob){
        FileOutputStream file = null;
        try {
            file = new FileOutputStream("players");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(file);
            out.writeObject(ob);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PlayerManager loadPlayers(){
        //If file not exist, make
        FileInputStream file = null;
        PlayerManager pm = null;
        try {
            file = new FileInputStream("players");
        } catch (Exception e) {
            savePlayers(new PlayerManager());
        }
        try {
            file = new FileInputStream("players");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(file);
            pm = (PlayerManager)in.readObject();
            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pm;
    }
}
