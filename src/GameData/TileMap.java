package GameData;

import GameData.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class TileMap {

    public Tile[][] table;

    private int row, column;

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

    public void checkMatches() {
        for (int x = 0; x < row - 2; x++) {
            for (int y = 0; y < column; y++) {
                if (this.table[x][y].getColor().equals(this.table[x + 1][y].getColor()) && (this.table[x][y].getColor().equals(this.table[x + 2][y].getColor()))) {
                    this.table[x][y].setColor("Match");
                    this.table[x + 1][y].setColor("Match");
                    this.table[x + 2][y].setColor("Match");
                }
            }
        }

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column - 2; y++) {
                if (this.table[x][y].getColor().equals(this.table[x][y + 1].getColor()) && (this.table[x][y].getColor().equals(this.table[x][y + 2].getColor()))) {
                    this.table[x][y].setColor("Match");
                    this.table[x][y + 1].setColor("Match");
                    this.table[x][y + 2].setColor("Match");
                }
            }
        }
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
        this.table[prevX][prevY] = this.table[nextX][nextY];
        this.table[nextX][nextY] = temp;
    }

    public void updateBoard() {
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                if (this.table[x][y].getColor().equals("Match")) {
                    int current_row = x;
                    for (int z = current_row; z > 0; z--) {
                        this.table[z][y].setColor(this.table[z - 1][y].getColor());
                    }
                    this.table[0][y].setColor(chooseRandomTile());
                }
            }
        }
    }

}
