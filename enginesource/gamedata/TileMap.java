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

    public void checkMatches() {
        for (int x = 0; x < row - 2; x++) {
            for (int y = 0; y < column; y++) {
                if (this.table[x][y].getColor().equals(this.table[x + 1][y].getColor()) && (this.table[x][y].getColor().equals(this.table[x + 2][y].getColor()))) {
                    this.table[x][y].setMatch(true);
                    this.table[x + 1][y].setMatch(true);
                    this.table[x + 2][y].setMatch(true);
//					this.table[x][y].setColor("Match");
//					this.table[x+1][y].setColor("Match");
//					this.table[x+2][y].setColor("Match");
                }
            }
        }

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column - 2; y++) {
                if (this.table[x][y].getColor().equals(this.table[x][y + 1].getColor()) && (this.table[x][y].getColor().equals(this.table[x][y + 2].getColor()))) {
                    this.table[x][y].setMatch(true);
                    this.table[x][y + 1].setMatch(true);
                    this.table[x][y + 2].setMatch(true);
//					this.table[x][y].setColor("Match");
//					this.table[x][y+1].setColor("Match");
//					this.table[x][y+2].setColor("Match");
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


        else if ( specialType == "BOX" )
        {
            int selected_row = newX;

            int selected_column = newY;

            if (selected_column == 0) // edge case, first column
            {
                if (selected_row == 0) // first row, first column
                {
                    this.table[0][0].setMatch(true);
                    this.table[0][1].setMatch(true);
                    this.table[1][0].setMatch(true);
                    this.table[1][1].setMatch(true);
                }

                else if (selected_row == this.row-1)  // last row, first column
                {
                    this.table[selected_row][selected_column].setMatch(true);
                    this.table[selected_row-1][selected_column].setMatch(true);
                    this.table[selected_row-1][selected_column+1].setMatch(true);
                    this.table[selected_row][selected_column + 1].setMatch(true);
                }

                else // first column, any other row
                {
                    this.table[selected_row][selected_column].setMatch(true);
                    this.table[selected_row][selected_column+1].setMatch(true);

                    this.table[selected_row-1][selected_column].setMatch(true);
                    this.table[selected_row-1][selected_column+1].setMatch(true);

                    this.table[selected_row+1][selected_column+1].setMatch(true);
                    this.table[selected_row+1][selected_column].setMatch(true);
                }
            }

            else if (selected_column == this.column-1) // last column
            {
                if (selected_row == 0) // last column, first row
                {
                    this.table[selected_row][selected_column].setMatch(true);
                    this.table[selected_row][selected_column-1].setMatch(true);
                    this.table[selected_row+1][selected_column].setMatch(true);
                    this.table[selected_row+1][selected_column-1].setMatch(true);
                }

                else if (selected_row == this.row-1) // last column, last row
                {
                    this.table[selected_row][selected_column].setMatch(true);
                    this.table[selected_row-1][selected_column].setMatch(true);
                    this.table[selected_row-1][selected_column-1].setMatch(true);
                    this.table[selected_row][selected_column-1].setMatch(true);
                }

                else // last column, any other row
                {
                    this.table[selected_row][selected_column].setMatch(true);
                    this.table[selected_row][selected_column-1].setMatch(true);

                    this.table[selected_row-1][selected_column].setMatch(true);
                    this.table[selected_row-1][selected_column-1].setMatch(true);

                    this.table[selected_row+1][selected_column-1].setMatch(true);
                    this.table[selected_row+1][selected_column].setMatch(true);
                }
            }

            else // any non-border case
            {
                this.table[selected_row-1][selected_column-1].setMatch(true);
                this.table[selected_row-1][selected_column].setMatch(true);
                this.table[selected_row-1][selected_column+1].setMatch(true);

                this.table[selected_row][selected_column-1].setMatch(true);
                this.table[selected_row][selected_column].setMatch(true);
                this.table[selected_row][selected_column+1].setMatch(true);

                this.table[selected_row+1][selected_column-1].setMatch(true);
                this.table[selected_row+1][selected_column].setMatch(true);
                this.table[selected_row+1][selected_column+1].setMatch(true);
            }
        }



    }


//	public static void main(String[] args)
//	{
//		TileMap t = new TileMap(7,7);
//
//		t.fillBoard();
//		//System.out.println(t.toString());
//
//		t.table[4][0].setColor("B");
//		t.table[1][0].setColor("B");
//		t.table[2][0].setColor("B");
//		t.table[3][0].setColor("B");
//		System.out.println(t.toString());
//		t.checkMatches();
//		t.updateBoard();
//
//		System.out.println("Updated Board");
//		System.out.println(t.toString());
//
//	}

}
