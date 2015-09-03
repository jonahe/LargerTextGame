package maps;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.EnemyBaseClass;
import domain.EnemyBird;
import domain.EnemyTroll;
import domain.EnemyWizard;
import domain.Player;
import domain.PowerUp;
import domain.PowerUp_MoreDamageOrMoreHealth;

/*
 * A 10 * 10 map with a 5 * 5 occupied square (forest) in top right corner
 * 
 */

public class GameMapLevel02 extends GameMap {

	public GameMapLevel02(Player player) {
		super(	player,
				"Level 2 - 'Something green in the corner'", 
				10, 
				10, 
				createOccupiedAreaList(),
				createEnemyList(),
				createPowerUpList(player)
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
									new Point(5,0), 
									5, 
									5
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
		
		// 6 birds
		for(int i = 0; i < 6; i++){
			enemyList.add(new EnemyBird());
		}
		// 3 Trolls
		for(int i = 0; i < 3; i++){
			enemyList.add(new EnemyTroll());
		}
		// 1 Wizards
		for(int i = 0; i < 1; i++){
			enemyList.add(new EnemyWizard());
		}
		
		return enemyList;
		
	}
	
	private static List<PowerUp> createPowerUpList(Player player){
		List<PowerUp> list = new ArrayList<>();
		
		// make x copies of this
		int numberWanted = 7;
		for(int i = 0; i < numberWanted; i++){
			PowerUp powerUp = new PowerUp_MoreDamageOrMoreHealth(player);
			list.add(powerUp);
		}
		
		return list;
	}

}
