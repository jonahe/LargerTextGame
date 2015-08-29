package domain;

import java.awt.Point;

public class MoveableObject {
	
	private Point position;
	private int speed;
	
	// just for easier reading of code
	protected static final int NORTH = 1;
	protected static final int SOUTH = 2;
	protected static final int WEST = 3;
	protected static final int EAST = 4;
	
	public MoveableObject(Point startPosition, int speedOfmovement) {
		this.position = startPosition;
		this.speed = speedOfmovement;	
	}
	
	public Point getPosition(){
		return position;
	}
	
	public void setPosition(Point position){
		this.position = position;
	}
	
	
	public void move(int direction){
		if(direction == NORTH){
			translatePlayerPosition(0, -speed);

		} else if(direction == SOUTH){
			translatePlayerPosition(0, speed);
		
		} else if(direction == WEST){
			translatePlayerPosition(-speed, 0);
		
		} else if(direction == EAST){
			translatePlayerPosition(speed, 0);
		}
	}
	
	public void translatePlayerPosition(int dx, int dy){
		position.translate(dx, dy);
	}
	
	
	
}
