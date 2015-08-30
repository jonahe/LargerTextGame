package maps;

import java.awt.Point;
import java.util.List;
import java.util.Random;

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
	//TODO: change to List<OccupiedArea> because we could have several "inhabitants"
	private List<OccupiedArea> occupiedAreaList; 
//	private List<Point> occupiedPoints; // spaces "occupied" by forests, buildings, rocks etc.
//	private List<List<Point>> occupiedPointsList;
	
	
	GameMap(String name, int mapDimensionX, int mapDimensionY, List<OccupiedArea> occupiedAreaList){
		this.name = name;
		MAX_X_POSITION = mapDimensionX;
		MAX_Y_POSITION = mapDimensionY;
		this.occupiedAreaList = occupiedAreaList;
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
	

//	public boolean mapPositionOccupied(int xPos, int yPos){
//		Point point = new Point(xPos, yPos);
//		return occupiedPoints.contains(point);
//	}
	
//	public boolean mapPositionOccupied(Point positionToCheck){
//		
//		boolean occupied = false;
//		
//		for(List<Point> pointList : occupiedPointsList){
//			if(pointList.contains(positionToCheck)){
//				occupied = true;
//				break;
//			} // else continue with loop
//		}
//		return occupied;
//	}
	
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
	
//	// eg. for generating a safe starting point for player
//	public Point getRandomNonOccupiedPoint(){
//		Random randGen = new Random();
//		while(true){
//			int x = randGen.nextInt(MAX_X_POSITION + 1);
//			int y = randGen.nextInt(MAX_Y_POSITION + 1);
//			Point randPoint = new Point(x, y);
//			if(occupiedPoints.contains(randPoint)){
//				continue;
//			}
//			return randPoint;
//		}
//
//	}
	
	
	// eg. for generating a safe starting point for player
	public Point getRandomNonOccupiedPoint(){
		Random randGen = new Random();
		while(true){
			// generate possible position
			int x = randGen.nextInt(MAX_X_POSITION + 1);
			int y = randGen.nextInt(MAX_Y_POSITION + 1);
			Point randPoint = new Point(x, y);
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
