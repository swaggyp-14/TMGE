import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TileMap {
	
	public Tile[][] table;
	
	private int row, column;
	
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
