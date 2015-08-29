package maps;

import java.awt.Point;
import java.util.List;

import domain.Player;

/*
 * Basic of map , like forrests, buildings, hill
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
	
	public boolean playerIsNear(Player player){
		// player position copy
		
		//TODO: implement using players move method.
		Point position = new Point(player.getPosition().x, player.getPosition().y);
		
		
		return false;
	}
}
