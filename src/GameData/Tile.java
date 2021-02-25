import java.util.*;

class Tile {

	private int x;
	private int y;
	private String color;

	Tile(int newX, int newY, String newColor) {
		this.x = newX;
		this.y = newY;
		this.color = newColor; 
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