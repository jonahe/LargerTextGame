package maps;

import java.awt.Point;
import java.util.List;

import domain.Player;

/*
 * Basic "inhabitants" of a map , like forests, buildings, hill
 * 
 */
public abstract class OccupiedArea {
	
	private String name;
	private String messageOnNear;
	private List<Point> occupiedPoints; // Space that object occupies
	private boolean visitedLastRound; // changes on enter. can be used, together with mapPositionOccupied, to see if player EXITED
	
	
	public OccupiedArea(String name, String messageOnNear, List<Point> occupiedPoints) {
		this.name = name;
		this.messageOnNear = messageOnNear;
		this.occupiedPoints = occupiedPoints;
		visitedLastRound = false;
	}
	
	public boolean visitedLastRound(){
		return visitedLastRound;
	}
	
	public void setVisitedLastRound(boolean visitedLastRound){
		this.visitedLastRound = visitedLastRound;
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
	
	public void showOnNearMessage(){
		System.out.println(messageOnNear);
	}
	
	/**
	 * Shifts the positions points of the occupied area (through translate).
	 * 
	 * @param dx position change on the x-axis
	 * @param dy position change on the y-axis
	 */
	
	public void shiftOccupiedPoints(int dx, int dy){
		for(Point point : occupiedPoints){
			point.translate(dx, dy);
		}
	}
	
	
}
