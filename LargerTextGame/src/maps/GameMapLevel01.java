package maps;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/*
 * A 20 * 20 map with a 5 * 5 occupied square in top right corner
 * 
 */

public class GameMapLevel01 extends GameMap {

	public GameMapLevel01() {
		super("Level 1 - 'Something weird in the corner'", 20, 20, createOccupiedPoints());
	}

	private static List<Point> createOccupiedPoints() {
		// occupied square in top right corner of map
		ArrayList<Point> occupiedPoints = new ArrayList<Point>();
		for(int xPos = 15; xPos <= 20; xPos++){
			for(int yPos = 0; yPos <= 5; yPos++){
				occupiedPoints.add(new Point(xPos, yPos));
			}
		}
		return occupiedPoints;
	}

}
