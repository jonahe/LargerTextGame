package helper;

import java.awt.Point;
import java.util.Random;

import domain.MovableObject;

/**
 * Functions that are helpful and widely used in the game, but not specific to a certain object / class
 * 
 */

public final class GameHelperClass {
	
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
	
	// for example generating random "option choices".
	// note: pretty crude. OK for uses where lowest allowed is pretty low.
	/**
	 * Generate random number between (and including) two numbers
	 * 
	 * @param lowestAllowed Generated number will not be lower than this
	 * @param highestAllowed Generated number will not be higher than this
	 * @return
	 */
	
	public static int generateRandomNumberWithinSpan(int lowestAllowed, int highestAllowed){
		Random randGen = new Random();
		int random = 0;
		while(true){
			random = randGen.nextInt(highestAllowed++);
			if(random >= lowestAllowed){
				break; // else continue
			}
		}
		return random;
	}
	
	/**
	 * Make a MovableObject move in random direction
	 * 
	 * @param objectToMove The object that should move
	 */
	public static void randomMove(MovableObject objectToMove){
		int direction = generateRandomNumberWithinSpan(1, 4);
		objectToMove.move(direction);
	}

	/**
	 * Same as randomMove(), but with possibility of no move
	 * 
	 * @param objectToMove Object to MAYBE move
	 */
	public static void randomMoveIncludingNoMove(MovableObject objectToMove){
		Random randGen = new Random();
		if(randGen.nextBoolean()){
			randomMove(objectToMove);
		}
	}
	
	
}
