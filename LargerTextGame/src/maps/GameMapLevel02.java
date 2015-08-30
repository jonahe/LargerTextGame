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
		super("Level 2 - 'Something weird near the middle'", 26, 26, createOccupiedAreaList());
	}



	private static List<OccupiedArea> createOccupiedAreaList() {
		List<OccupiedArea> occupiedAreaList = new ArrayList<OccupiedArea>();
		ForrestBasic forest5x5 = 
				new ForrestBasic( 	"The dark forrest", 
									"Oh, do I smell rotting leafs and dirt?", 
									"Hm.. Trees, trees, trees. Forests can be pretty boring..", 
									"Phew, I thought the forest would never stop. But now I'm out!",  
									new Point(8,4), 
									3, 
									9
									);
		occupiedAreaList.add(forest5x5);
		return occupiedAreaList;
	}
	
	

}