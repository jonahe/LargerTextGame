package maps;

import java.awt.Point;
import java.util.List;

import domain.Player;

/*
 * Basic of map , like forests, buildings, hill
 * 
 */
public abstract class OccupiedArea {
	
	private String name;
	private String messageOnNear;
	private List<Point> occupiedPoints; // Space that object occupies
	
	
	public OccupiedArea(String name, String messageOnNear, List<Point> occupiedPoints) {
		this.name = name;
		this.messageOnNear = messageOnNear;
		this.occupiedPoints = occupiedPoints;
	}
	
	public boolean mapPositionOccupied(Point positionToCheck){
		return occupiedPoints.contains(positionToCheck);
	}
	
	/**
	 * Checks if player is near the occupiedArea
	 * 
	 * @param player Player to check
	 * @return Near or not
	 */
	public boolean playerIsNear(Player player){
		
		boolean near = false;
		// check if the player's surrounding points contain any of the occupied ones.
		for(Point nearPoint : player.getSurroundingClosePoints()){
			if(occupiedPoints.contains(nearPoint)){
				near = true;
				break;
			} else {
				continue;
			}
		}
		return near;
	}
}
