package helper;

import java.awt.Point;

/**
 * Functions that are helpful and widely used in the game, but not specific to a certain object / class
 * 
 * 
 */

public class GameHelperClass {
	
	private static final int NORTH = 1;
	private static final int SOUTH = 2;
	private static final int WEST = 3;
	private static final int EAST = 4;

	/**
	 * 
	 * @param point Point you want to move
	 * @param direction Direction you want to move
	 * @return The point with translated x, y
	 */
	
	public static Point movePoint(Point point, int direction){
		if(direction == NORTH){
			point.translate(0, -1);
		} else if (direction == SOUTH){
			point.translate(0, 1);
		} else if (direction == WEST){
			point.translate(-1, 0);
		} else if (direction == EAST){
			point.translate(1, 0);
		}
		return point;
	}
}
