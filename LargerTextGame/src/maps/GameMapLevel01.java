package maps;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import domain.EnemyBaseClass;
import domain.EnemyBird;
import domain.EnemyTroll;
import domain.EnemyWizard;
import domain.Player;
import domain.PowerUp;
import domain.PowerUp_MoreDamageOrMoreHealth;

/*
 * A 6 * 6 map with a 2 * 2 occupied square (forest) in the middle
 * 
 */

public class GameMapLevel01 extends GameMap {

	public GameMapLevel01(Player player) {
		super(	player,
				"Level 1 - 'Something green in the middle'", 
				6, 
				6, 
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
		ForrestBasic forest2x2 = 
				new ForrestBasic( 	"The dark forrest", 
									"Oh, do I smell rotting leafs and dirt?", 
									"Yeah, it was a forest. Trees, trees, trees. Forests can be pretty boring..", 
									"Phew, I thought the forest would never end. But now we're out in the open again!",  
									new Point(2,2), 
									2, 
									2
									);
		occupiedAreaList.add(forest2x2);
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
		// add enemies --  6*6 map (36 coordinates)
		
		// 3 birds
		for(int i = 0; i < 3; i++){
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
		int numberWanted = 9;
		for(int i = 0; i < numberWanted; i++){
			PowerUp powerUp = new PowerUp_MoreDamageOrMoreHealth(player);
			list.add(powerUp);
		}
		
		return list;
	}

}
