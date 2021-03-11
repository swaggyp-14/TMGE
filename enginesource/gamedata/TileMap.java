package gamedata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TileMap {

    public Tile[][] table;

    private int row, column;

    private String switched_color;

    public TileMap(int r, int c) {
        this.row = r;

        this.column = c;

        this.table = new Tile[r][c];

    }

    public Tile[][] getTable() {
        return table;
    }

    public Tile getTile(int i, int j) {
        return this.table[i][j];
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void placeTile(int x, int y, Tile t) {
        this.table[x][y] = t;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String chooseRandomTile() {
        ArrayList<String> givenTiles = new ArrayList<String>(Arrays.asList("B", "R", "G", "Y", "O", "P"));

        Random rand = new Random();

        return givenTiles.get(rand.nextInt(givenTiles.size()));
    }

    public void fillBoard() {
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                String tile_selected = chooseRandomTile();

                this.table[x][y] = new Tile(x, y, tile_selected);
            }
        }
    }

    public String toString() {

        String currentBoard = "";

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                currentBoard = currentBoard + this.table[x][y].getColor();
            }

            currentBoard = currentBoard + "\n";
        }

        return currentBoard;

    }

    public void spawnSpecialTile(String type, int count, int x, int y) {
        if(count == 5)
        {
            this.table[x][y].setColor("COLORMATCH");
            this.table[x][y].setMatch(false);
        }
        if(count == 4 && type == "X")
        {
            this.table[x][y].setColor("XLINE");
            this.table[x][y].setMatch(false);
        }

        if(count == 4 && type == "Y")
        {
            this.table[x][y].setColor("YLINE");
            this.table[x][y].setMatch(false);
        }
    }

    public void checkMatches() {
        int count = 0;
        for (int x = 0; x < row - 2; x++) {
            for (int y = 0; y < column; y++) {
                if (this.table[x][y].getColor().equals(this.table[x + 1][y].getColor()) && (this.table[x][y].getColor().equals(this.table[x + 2][y].getColor()))) {
                    this.table[x][y].setMatch(true);
                    count+=1;
                    this.table[x + 1][y].setMatch(true);
                    count+=1;
                    this.table[x + 2][y].setMatch(true);
                    count+=1;
//					this.table[x][y].setColor("Match");
//					this.table[x+1][y].setColor("Match");
//					this.table[x+2][y].setColor("Match");
                }
                if (count >= 4)
                {
                    spawnSpecialTile("X", count, x, y);
                    count = 0;

                }
            }
        }

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column - 2; y++) {
                if (this.table[x][y].getColor().equals(this.table[x][y + 1].getColor()) && (this.table[x][y].getColor().equals(this.table[x][y + 2].getColor()))) {
                    this.table[x][y].setMatch(true);
                    count+=1;
                    this.table[x][y + 1].setMatch(true);
                    count+=1;
                    this.table[x][y + 2].setMatch(true);
                    count+=1;
//					this.table[x][y].setColor("Match");
//					this.table[x][y+1].setColor("Match");
//					this.table[x][y+2].setColor("Match");
                }
                if (count >= 4)
                {
                    spawnSpecialTile("Y", count, x, y);
                    count = 0;
                }
            }
        }
    }

    public void updateBoard() {
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                if (this.table[x][y].getMatch()) {
                    int current_row = x;

                    for (int z = current_row; z > 0; z--) {

                        this.table[z][y].setColor(this.table[z - 1][y].getColor());
                        this.table[z][y].setMatch(this.table[z - 1][y].getMatch());
                    }

                    this.table[0][y].setColor(chooseRandomTile());
                    this.table[0][y].setMatch(false);
                }

            }
        }
    }


    public boolean inbounds(int prevX, int prevY, int afterX, int afterY) {
        if (prevX + 1 == afterX || prevX - 1 == afterX || prevY + 1 == afterY || prevY - 1 == afterY) {
            return true;
        }
        return false;
    }

    public void switchTiles(String prev, String next) {
        String[] prevArr = prev.split(",");
        String[] nextArr = next.split(",");
        int prevX, prevY, nextX, nextY;
        prevX = Integer.parseInt(prevArr[0]);
        prevY = Integer.parseInt(prevArr[1]);
        nextX = Integer.parseInt(nextArr[0]);
        nextY = Integer.parseInt(nextArr[1]);
        Tile temp = this.table[prevX][prevY];

        if (Math.abs(prevX - nextX) > 1 || Math.abs(prevY - nextY) > 1) {
            return;
        }

        if (temp.getColor().equals("COLORMATCH")) {
            if (inbounds(prevX, prevY, nextX, nextY)) {
                this.switched_color = this.table[nextX][nextY].getColor();
                temp.setMatch(true);
                special(temp.getColor(), nextX, nextY);
            }
        } else if (this.table[nextX][nextY].getColor().equals("COLORMATCH")) {
            if (inbounds(prevX, prevY, nextX, nextY)) {
                this.switched_color = temp.getColor();
                this.table[nextX][nextY].setMatch(true);
                special(this.table[nextX][nextY].getColor(), prevX, prevY);
            }
        }

        if (temp.getColor().equals("XLINE")) {
            if (inbounds(prevX, prevY, nextX, nextY)) {
                temp.setMatch(true);
                special(temp.getColor(), nextX, nextY);
            }
        } else if (this.table[nextX][nextY].getColor().equals("XLINE")) {
            if (inbounds(prevX, prevY, nextX, nextY)) {
                this.switched_color = temp.getColor();
                this.table[nextX][nextY].setMatch(true);
                special(this.table[nextX][nextY].getColor(), prevX, prevY);
            }
        }
        if (temp.getColor().equals("YLINE")) {
            if (inbounds(prevX, prevY, nextX, nextY)) {
                temp.setMatch(true);
                special(temp.getColor(), nextX, nextY);
            }
        } else if (this.table[nextX][nextY].getColor().equals("YLINE")) {
            if (inbounds(prevX, prevY, nextX, nextY)) {
                this.switched_color = temp.getColor();
                this.table[nextX][nextY].setMatch(true);
                special(this.table[nextX][nextY].getColor(), prevX, prevY);
            }
        }

        if (temp.getColor().equals("CROSS")) {
            if (inbounds(prevX, prevY, nextX, nextY)) {
                temp.setMatch(true);
                special(temp.getColor(), nextX, nextY);
            }
        } else if (this.table[nextX][nextY].getColor().equals("CROSS")) {
            if (inbounds(prevX, prevY, nextX, nextY)) {
                this.switched_color = temp.getColor();
                this.table[nextX][nextY].setMatch(true);
                special(this.table[nextX][nextY].getColor(), prevX, prevY);
            }
        }

        if (inbounds(prevX, prevY, nextX, nextY)) {
            this.table[prevX][prevY] = this.table[nextX][nextY];
            this.table[nextX][nextY] = temp;

        }

    }

    public void special(String specialType, int newX, int newY) {
        if (specialType == "COLORMATCH") {
            for (int x = 0; x < row; x++) {
                for (int y = 0; y < column; y++) {
                    if (this.table[x][y].getColor().equals(this.switched_color)) {
                        this.table[x][y].setMatch(true);
                    }
                }
            }
        } else if (specialType == "CROSS") {
            int selected_row = newX;

            int selected_column = newY;

            for (int y = 0; y < column; y++) {
                this.table[selected_row][y].setMatch(true);
            }

            for (int x = 0; x < row; x++) {
                this.table[x][selected_column].setMatch(true);
            }
        } else if (specialType == "XLINE") {
            int selected_row = newX;

            for (int y = 0; y < column; y++) {
                this.table[selected_row][y].setMatch(true);
            }
        } else if (specialType == "YLINE") {
            int selected_column = newY;

            for (int x = 0; x < row; x++) {
                this.table[x][selected_column].setMatch(true);
            }
        }

//    		else if (t.getColor().equals("BOX"))
//    		{
//    			int selected_row = t.getX();
//
//    			int selected_column = t.getY();
//
//    			if (selected_column == 0)
//    			{
//    				if (selected_row == 0)
//    				{
//    					this.table[0][0].setMatch(true);
//    					this.table[0][1].setMatch(true);
//    					this.table[1][0].setMatch(true);
//    					this.table[1][1].setMatch(true);
//    				}
//
//    				if (selected_row == this.row-1)
//    				{
//    					this.table[selected_row][selected_column].setMatch(true);
//    					this.table[selected_row-1][selected_column].setMatch(true);
//    					this.table[selected_row-1][selected_column-1].setMatch(true);
//    					this.table[selected_row][selected_column].setMatch(true);
//    				}
//    			}
//
//    			if (selected_column == this.column-1)
//    			{
//    				if (selected_row == 0)
//    				{
//    					this.table[selected_row][selected_column].setMatch(true);
//    					this.table[selected_row][selected_column-1].setMatch(true);
//    					this.table[selected_row+][selected_column].setMatch(true);
//    					this.table[selected_row-1][selected_column-1].setMatch(true);
//    				}
//
//    				if (selected_row)
//    			}
//    		}
    }
}
