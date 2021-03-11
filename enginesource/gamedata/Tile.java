package gamedata;

import java.awt.*;

public class Tile {

	private int x;
	private int y;
	private String color;
	private boolean isSpecial = false;
	private boolean isMatched;
	private boolean isActive = true;

	public Tile(int newX, int newY, String newColor) {
		this.x = newX;
		this.y = newY;
		this.color = newColor; 
		this.isMatched = false;
	}

	public boolean getSpecial(){
		return this.isSpecial; 
	}

	public void setSpecial(){
		this.isSpecial = true; 
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

	public void setIsActiveToFalse() {
		this.isActive = false;
	}

	public boolean getIsActive(){
		return this.isActive;
	}
}