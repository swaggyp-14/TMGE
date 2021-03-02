package GameData;

public class Tile {

	private int x;
	private int y;
	private String color;
	
	private boolean isMatched;

	public Tile(int newX, int newY, String newColor) {
		this.x = newX;
		this.y = newY;
<<<<<<< HEAD
		this.color = newColor; 
		this.isMatched = false;
=======
		this.color = newColor;
>>>>>>> 9440484be90dea309dbebdfbed2c4772a4fbc343
	}

	public boolean getMatch()
	{
		return this.isMatched;
	}
	
	public void setMatch(boolean b)
	{
		this.isMatched = b;
	}
	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public void setColor(String color){
		this.color = color;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public String getColor(){
		return this.color;
	}



}