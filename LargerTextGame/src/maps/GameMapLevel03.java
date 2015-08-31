package maps;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.EnemyBaseClass;
import domain.EnemyBird;
import domain.EnemyTroll;
import domain.EnemyWizard;

/*
 * A 12 * 12 map with a big (3 * 9) rectangle forest near the middle.
 * 
 */

public class GameMapLevel03 extends GameMap {

	public GameMapLevel03() {
		super(	"Level 3 - 'Something green near the middle'", 
				12, 
				12, 
				createOccupiedAreaList(),
				createEnemyList()
				);
	}


	//TODO: figure out how to avoid this code / documentation duplication between maps
	
	/**
	 * Creates the structures in the map (buildings/forests/etc)
	 * 
	 * @return List of all map structures
	 */

	private static List<OccupiedArea> createOccupiedAreaList() {
		List<OccupiedArea> occupiedAreaList = new ArrayList<OccupiedArea>();
		ForrestBasic forest5x5 = 
				new ForrestBasic( 	"The dark forrest", 
									"Oh, do I smell rotting leafs and dirt?", 
									"Yeah, it was a forest. Trees, trees, trees. Forests can be pretty boring..", 
									"Phew, I thought the forest would never end. But now we're out in the open again!",
									new Point(8,4), 
									3, 
									9
									);
		occupiedAreaList.add(forest5x5);
		return occupiedAreaList;
	}
	
	//TODO: figure out how to avoid this code / documentation duplication between maps
	
	/**
	 * Creates all the enemies in the map.
	 * 
	 * @return List of all enemies in this map.
	 */
	
	private static List<EnemyBaseClass> createEnemyList(){
		List<EnemyBaseClass> enemyList = new ArrayList<EnemyBaseClass>();
		// add enemies -- need many on a 12*12 (144 coordinates)
		
		// 8 birds
		for(int i = 0; i < 8; i++){
			enemyList.add(new EnemyBird());
		}
		// 4 Trolls
		for(int i = 0; i < 4; i++){
			enemyList.add(new EnemyTroll());
		}
		// 2 Wizards
		for(int i = 0; i < 2; i++){
			enemyList.add(new EnemyWizard());
		}
		
		return enemyList;
	}
	
	


}