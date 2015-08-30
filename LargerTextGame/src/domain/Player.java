package domain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import helper.GameHelperClass;

/*
 * Basic player. The ability to move is inherrited from MoveableObject
 */

public class Player extends MoveableObject implements Damageble {
	
	private String name;
	private Weapon weapon;
	private int health;
	private boolean alive;
	
	private List<Point> surroundingClosePoints;
	
	public Player(String name, Weapon weapon, int handicap) {
		super(new Point(0,0), 1);
		this.name = name;
		this.weapon = weapon;
		health = 100;
		alive = true;
		// set handicap
		this.weapon.setHandicap(handicap);
		createSurroundingClosePoints();

	}
	
	public void useWeapon(EnemyBaseClass enemy) {
		enemy.updateHealth(weapon.getDamage());
	}
	
	public String getName(){
		return name;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	
	public int getHealth() {
		return health;
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
	
	
	@Override
	public void updateHealth(int damage) {
		health -= damage;
		if(health <= 0){
			alive = false;
		}
	}

	@Override
	public boolean isAlive() {
		return alive;
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

