package maps;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/*
 * A 26 * 26 map with a big rectangle near the middle.
 * 
 */

public class GameMapLevel02 extends GameMap {

	public GameMapLevel02() {
		super("Level 2 - 'Something weird near the middle'", 26, 26, createOccupiedPoints());
	}

	private static List<Point> createOccupiedPoints() {
		// occupied rectangle near the middle - a wall?
		ArrayList<Point> occupiedPoints = new ArrayList<Point>();
		for(int xPos = 11; xPos <= 15; xPos++){
			for(int yPos = 5; yPos <= 21; yPos++){
				occupiedPoints.add(new Point(xPos, yPos));
			}
		}
		return occupiedPoints;
	}

}