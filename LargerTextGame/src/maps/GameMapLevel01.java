package maps;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/*
 * A 20 * 20 map with a 5 * 5 occupied square (forest) in top right corner
 * 
 */

public class GameMapLevel01 extends GameMap {

	public GameMapLevel01() {
		super("Level 1 - 'Something weird in the corner'", 20, 20, createOccupiedAreaList());
	}

	private static List<OccupiedArea> createOccupiedAreaList() {
		List<OccupiedArea> occupiedAreaList = new ArrayList<OccupiedArea>();
		ForrestBasic forest5x5 = 
				new ForrestBasic( 	"The dark forrest", 
									"Oh, do I smell rotting leafs and dirt?", 
									"Yeah, a it's a forest. Trees, trees, trees. Forests can be pretty boring..", 
									"Phew, I thought the forest would never end. But now we're out in the open again!",  
									new Point(15,0), 
									5, 
									5
									);
		occupiedAreaList.add(forest5x5);
		return occupiedAreaList;
	}

//	private static List<Point> createOccupiedPoints() {
//		// occupied square in top right corner of map
//		ArrayList<Point> occupiedPoints = new ArrayList<Point>();
//		for(int xPos = 15; xPos <= 20; xPos++){
//			for(int yPos = 0; yPos <= 5; yPos++){
//				occupiedPoints.add(new Point(xPos, yPos));
//			}
//		}
//		return occupiedPoints;
//	}
	
//	private static List<List<Point>> createOccupiedPointsList(){
//		List<List<Point>> occupiedPointsList = new ArrayList<List<Point>>();
//		List<>
//	}

}
