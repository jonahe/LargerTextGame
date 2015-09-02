package domain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import helper.GameHelperClass;

/**
 * Basic player.
 */

public class Player extends GameAgent implements Damageable {
	
	private int handicap;
	
	private List<Point> surroundingClosePoints;
	
	public Player(String name, Weapon weapon, int handicap) {
		super(	new Point(0,0), 
				1,
				name,
				100,
				weapon
				);
		
		// set handicap
		this.handicap = handicap;

	}
		
	// need special implementation because of potential player handicap
	@Override
	public void useWeapon(GameAgent victim){
		weapon.makeSound(name);
		int damage = weapon.getDamage() + handicap;
		victim.updateHealth(damage);
	}
	
	
	public List<Point> getSurroundingClosePoints(){
		return surroundingClosePoints;
	}
	
	/**
	 * Same as parent class implementation, but also updates surrounding points
	 */
	@Override
	public void move(int direction){
		super.move(direction);
		// update surroundingClosePoints
		moveSurroundingClosePoints(direction);
	}
	
	/**
	 * Same as parent class implementation, but also updates surrounding points
	 */
	@Override
	public void setPosition(Point position){
		super.setPosition(position);
		// the surroundingClosePoints might be invalid now, so create them again
		createSurroundingClosePoints();
	}
	
	
	public void createSurroundingClosePoints(){
		// clone current position
		Point currentPos = (Point) getPosition().clone();
		// create four points to manipulate
		Point nearNorth = (Point) currentPos.clone();
		Point nearSouth = (Point) currentPos.clone();
		Point nearWest = (Point) currentPos.clone();
		Point nearEast = (Point) currentPos.clone();
		// manipulate them to the nearest point in their direction
		nearNorth.translate(0, -1);
		nearSouth.translate(0, 1);
		nearWest.translate(-1, 0);
		nearEast.translate(1, 0);
		
		// add to list
		surroundingClosePoints = new ArrayList<Point>();
		surroundingClosePoints.addAll(Arrays.asList(	nearNorth,
														nearSouth,
														nearWest,
														nearEast));
		
	}
	
	private void moveSurroundingClosePoints(int direction){
		for(Point point : surroundingClosePoints){
			GameHelperClass.movePoint(point, direction);
		}
	}
	
	
}

