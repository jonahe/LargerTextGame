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
 * A 26 * 26 map with a big (3 * 9) rectangle forest near the middle.
 * 
 */

public class GameMapLevel02 extends GameMap {

	public GameMapLevel02() {
		super(	"Level 2 - 'Something weird near the middle'", 
				26, 
				26, 
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
		// add enemies -- need many on a 26*26 (~650 points)
		
		// 20 birds
		for(int i = 0; i < 20; i++){
			enemyList.add(new EnemyBird());
		}
		// 10 Trolls
		for(int i = 0; i < 10; i++){
			enemyList.add(new EnemyTroll());
		}
		// 5 Wizards
		for(int i = 0; i < 5; i++){
			enemyList.add(new EnemyWizard());
		}
		


		return enemyList;
	}
	
	


}