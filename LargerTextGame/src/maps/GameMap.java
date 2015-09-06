package maps;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.EnemyBaseClass;
import domain.MovableObject;
import domain.Player;
import domain.PowerUp;

/*
 * Basic functionality that all maps should have
 * 
 */

public abstract class GameMap {
	
	private final String name;

	// map dimensions
	private int MIN_X_POSITION = 0;
	private int MIN_Y_POSITION = 0;
	private int MAX_X_POSITION;
	private int MAX_Y_POSITION;
	// map "inhabitants"
	private Player player;
	private List<OccupiedArea> occupiedAreaList; // for forests/buildings etc
	private List<EnemyBaseClass> enemyList;
	private List<PowerUp> powerUps;
	private List<Point> takenPositions = new ArrayList<Point>();// for power-ups and Enemies
	//TODO: add list with "equipment" / power-ups. Maybe weapons -> meny (switch weapon. leaves old weapon). Need "medicine" to heal player
	
	/**
	 * Constructor for GameMap
	 * @param player 
	 * @param name
	 * @param mapDimensionX eg. for a 4 * 10 map, x would be 4-1 (because 0 is a position to)
	 * @param mapDimensionY eg. for a 4 * 10 map, y would be 10-1 (because 0 is a position to)
	 * @param occupiedAreaList
	 * @param enemyList
	 * @param powerUps
	 */
	
	GameMap(	Player player, 
				String name, 
				int mapDimensionX, 
				int mapDimensionY, 
				List<OccupiedArea> occupiedAreaList, 
				List<EnemyBaseClass> enemyList, 
				List<PowerUp> powerUps){
		this.player = player;
		this.name = name;
		MAX_X_POSITION = mapDimensionX;
		MAX_Y_POSITION = mapDimensionY;
		this.occupiedAreaList = occupiedAreaList;
		this.enemyList = enemyList;
		this.powerUps = powerUps;
		
		// change the positions of enemies. originally all (0,0)
		setUniqueRandomPositions(getEnemyList());
		// change the positions of powerups . originally all (0,0)
		setUniqueRandomPositions(getPowerUps());
		
	}


	public List<PowerUp> getPowerUps() {
		return powerUps;
	}


	public void setPowerUps(List<PowerUp> powerUps) {
		this.powerUps = powerUps;
	}


	public int getMIN_X_POSITION() {
		return MIN_X_POSITION;
	}


	public int getMIN_Y_POSITION() {
		return MIN_Y_POSITION;
	}


	public int getMAX_X_POSITION() {
		return MAX_X_POSITION;
	}


	public int getMAX_Y_POSITION() {
		return MAX_Y_POSITION;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public List<OccupiedArea> getOccupiedAreaList(){
		return occupiedAreaList;
	}
	
	public List<EnemyBaseClass> getEnemyList(){
		return enemyList;
	}
	
	/**
	 * Is this map position inside an OccupiedArea, like a forest 
	 * @param positionToCheck
	 * @return
	 */
	public boolean mapPositionOccupied(Point positionToCheck){
		
		boolean occupied = false;
		
		for(OccupiedArea occupiedArea : occupiedAreaList){
			if(occupiedArea.pointIsInsideArea(positionToCheck)){
				occupied = true;
				break;
			} // else continue with loop
		}
		return occupied;
	}
	
	
	/**
	 * 
	 * @param objectsToPlace Like Enemy, PowerUp or other object that inherits from MovableObject
	 */
	public void setUniqueRandomPositions(List<? extends MovableObject> objectsToPlace ){
		
		// for each enemy, generate a random position. if it's OK, make that point the enemy position ELSE: try again
		for(MovableObject object : objectsToPlace){
			while(true){
				Point position = getRandomOccupiableMapPoint();
				// if point HASN'T already been taken: set it as enemy position
				// add it to the list with taken positions
				// and continue to next enemy in the for-loop
				if(!takenPositions.contains(position)){ 
					object.setPosition(position);
					takenPositions.add(position);
					break; // break the while-loop and continue with next enemy
				}
			}
		}
	}
	
	public Point getRandomMapPoint(){
		Random randGen = new Random();
		// generate possible position - from 0 to max possible x and y on this map
		int x = randGen.nextInt(MAX_X_POSITION + 1); // +1 because bound is exclusive
		int y = randGen.nextInt(MAX_Y_POSITION + 1);
		return new Point(x, y);
	}
	
	
	public Point getRandomOccupiableMapPoint(){
		Point randPoint = new Point();
		while(true){
			randPoint = getRandomMapPoint();
			// NOTE: some "occupied spaces" (OccupiedArea), like those in a forest, are OK.
			// Check if OccupiedArea implements IEnterable, if so, those points are occupiable.. else..
			// else check if randPoint is in the list of occupied, NOT OK, points
			// if so ->  mark the boolean so that we know if we should continue the while-loop 
			boolean pointOK = true;
			for(OccupiedArea area : occupiedAreaList){
				if(!(area instanceof IEnterable)){ // NOT enterable
					if(area.pointIsInsideArea(randPoint)){
						pointOK = false;
						break; 	// break the for-loop if a non-enterable area is already occupying that point. 
								// We need to run the while loop again
					} // else continue for-loop
				} // else ignore area and move on to next area
			} // end of for-loop
			if(pointOK){ // no "matches" detected in for-loop
				break; // break out of while loop
			} else {
				continue; // generate another point and try again
			}
			
		} // end of while-loop
		return randPoint;
	}
	
	
	// eg. for generating a safe starting point for player, and outside enterable areas like forests
	public Point getRandomNonOccupiedPoint(){
		while(true){
			Point randPoint = getRandomMapPoint();
			if(mapPositionOccupied(randPoint)){
				continue; // return is not reached, we try with another number
			}
			return randPoint;
		}

	}
	
	public List<Point> getTakenPositions(){
		return takenPositions;
	}
	
	/**
	 * Check if a point is in the takenPositions list. (Containing enemies, powerUps and other MovableObject, excluding Player and Areas like forests)
	 * @param point point to check
	 * @return boolean taken or not
	 */
	public boolean pointTaken(Point point){
		return takenPositions.contains(point);
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){ // plus 1 on the x and y because a 5 * 5 map would have 4.4 as the end position
		return String.format("GameMap [name: %s, dimensions: %d * %d]", getName(), getMAX_X_POSITION() +1, getMAX_Y_POSITION() +1);
	}
	

}
