import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TileMap {
	
	public Tile[][] table;
	
	private int row, column;
	
	private String switched_color;
	
	TileMap(int r, int c)
	{
		this.row = r;
		
		this.column = c;
		
		this.table = new Tile[r][c];
		
	}
	
	public String chooseRandomTile()
	{
		ArrayList<String> givenTiles = new ArrayList<String> (Arrays.asList("B", "R", "G", "Y", "O", "P"));
		
		Random rand = new Random();
		
		return givenTiles.get(rand.nextInt(givenTiles.size()));
	}
	
	public void fillBoard()
	{
		for (int x = 0; x < row; x++)
		{
			for (int y = 0; y < column; y++)
			{
				String tile_selected = chooseRandomTile();
				
				this.table[x][y] = new Tile(x, y, tile_selected);
			}
		}
	}
	
	public String toString()
	{
		String currentBoard = "";
		
		for (int x = 0; x < row; x++)
		{
			for (int y = 0; y < column; y++)
			{
				currentBoard = currentBoard + this.table[x][y].getColor();
			}
			
			currentBoard = currentBoard + "\n";
		}
		
		return currentBoard;
	}
	
	public void checkMatches()
	{
		for (int x = 0; x < row-2; x++)
		{
			for (int y = 0; y < column; y++)
			{
				if (this.table[x][y].getColor().equals(this.table[x+1][y].getColor()) && (this.table[x][y].getColor().equals(this.table[x+2][y].getColor())))
				{
					this.table[x][y].setMatch(true);
					this.table[x+1][y].setMatch(true);
					this.table[x+2][y].setMatch(true);
//					this.table[x][y].setColor("Match");
//					this.table[x+1][y].setColor("Match");
//					this.table[x+2][y].setColor("Match");
				}
			}
		}
		
		for (int x = 0; x < row; x++)
		{
			for (int y = 0; y < column-2; y++)
			{
				if (this.table[x][y].getColor().equals(this.table[x][y+1].getColor()) && (this.table[x][y].getColor().equals(this.table[x][y+2].getColor())))
				{
					this.table[x][y].setMatch(true);
					this.table[x][y+1].setMatch(true);
					this.table[x][y+2].setMatch(true);
//					this.table[x][y].setColor("Match");
//					this.table[x][y+1].setColor("Match");
//					this.table[x][y+2].setColor("Match");
				}
			}
		}
	}
	
	public void updateBoard()
	{
		for (int x = 0; x< row; x++)
		{
			for (int y = 0; y < column; y++)
			{
				if (this.table[x][y].getMatch())
				{
					int current_row = x;

					for (int z = current_row; z > 0; z--)
					{

						this.table[z][y].setColor(this.table[z-1][y].getColor());
						this.table[z][y].setMatch(this.table[z-1][y].getMatch());
						
					}
					
					this.table[0][y].setColor(chooseRandomTile());
					this.table[0][y].setMatch(false);
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
        
        if (temp.getColor().equals("COLORMATCH"))
        {
        	this.switched_color = this.table[nextX][nextY].getColor();
        }
        
        else if (this.table[nextX][nextY].getColor().equals("COLORMATCH"))
        {
        	this.switched_color = temp.getColor();
        }
        
        this.table[prevX][prevY] = this.table[nextX][nextY];
        this.table[nextX][nextY] = temp;
    }
	
    public void handleSpecial(Tile t)
    {
    	if (t.getSpecial())
    	{
    		if (t.getColor().equals("COLORMATCH"))
    		{
    			for (int x =0; x < row; x++)
    			{
    				for (int y =0; y < column; y++)
    				{
    					if (this.table[x][y].getColor().equals(this.switched_color))
    					{
    						this.table[x][y].setMatch(true);
    					}
    				}
    			}
    		}
    		
    		else if (t.getColor().equals("CROSS"))
    		{
    			int selected_row = t.getX();
    			
    			int selected_column = t.getY();
    			
    			for (int y = 0; y < column; y++)
    			{
    				this.table[selected_row][y].setMatch(true);
    			}
    			
    			for (int x = 0; x < row; x++)
    			{
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
