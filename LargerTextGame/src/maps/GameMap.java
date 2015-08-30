package maps;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import domain.EnemyBaseClass;

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
	private List<OccupiedArea> occupiedAreaList; // for forests/buildings etc
	private List<EnemyBaseClass> enemyList;
	
	
	GameMap(String name, int mapDimensionX, int mapDimensionY, List<OccupiedArea> occupiedAreaList, List<EnemyBaseClass> enemyList){
		this.name = name;
		MAX_X_POSITION = mapDimensionX;
		MAX_Y_POSITION = mapDimensionY;
		this.occupiedAreaList = occupiedAreaList;
		this.enemyList = enemyList;
		
		// change the positions of enemies. originally all (0,0)
		setUniqueRandomEnemyPositions();
		
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
	
	public List<OccupiedArea> getOccupiedAreaList(){
		return occupiedAreaList;
	}
	
	public List<EnemyBaseClass> getEnemyList(){
		return enemyList;
	}
	
	
	public boolean mapPositionOccupied(Point positionToCheck){
		
		boolean occupied = false;
		
		for(OccupiedArea occupiedArea : occupiedAreaList){
			if(occupiedArea.mapPositionOccupied(positionToCheck)){
				occupied = true;
				break;
			} // else continue with loop
		}
		return occupied;
	}
	
	
	public void setUniqueRandomEnemyPositions(){
	
		ArrayList<Point> positionsAlreadyTaken = new ArrayList<Point>();
		
		// for each enemy, generate a random position. if it's OK, make that point the enemy position ELSE: try again
		for(EnemyBaseClass enemy : enemyList){
			while(true){
				Point position = getRandomOccupiableMapPoint();
				// if point HASN'T already been taken: set it as enemy position
				// add it to the list with taken positions
				// and continue to next enemy in the for-loop
				if(!positionsAlreadyTaken.contains(position)){ 
					enemy.setPosition(position);
					positionsAlreadyTaken.add(position);
					break; // break the while-loop and continue with next enemy
				}
			}
		}
		System.out.println(positionsAlreadyTaken);
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
					if(area.mapPositionOccupied(randPoint)){
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
	
	
	// eg. for generating a safe starting point for player
	public Point getRandomNonOccupiedPoint(){
		while(true){
			Point randPoint = getRandomMapPoint();
			if(mapPositionOccupied(randPoint)){
				continue; // return is not reached, we try with another number
			}
			return randPoint;
		}

	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return String.format("GameMap [name: %s, dimensions: %d * %d]", getName(), getMAX_X_POSITION(), getMAX_Y_POSITION());
	}
	

}
