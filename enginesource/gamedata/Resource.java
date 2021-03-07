package gamedata;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Resource {
    private Map<Color, String> resourceDir;

    public Resource(Map<String, Color> map) {
        resourceDir = new HashMap<>();
        for (Map.Entry<String, Color> entry : map.entrySet()) {
         this.resourceDir.put(entry.getValue(), "");
        }
    }
    public void addResource(Color col, String dir) {
        this.resourceDir.put(col, dir);
    }
    public String getDir(Color col){
        return this.resourceDir.get(col);
    }
}
