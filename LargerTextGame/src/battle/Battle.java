package battle;

import java.util.List;

import domain.EnemyBaseClass;
import domain.Player;
import helper.GameHelperClass;

import static app.App.askForAndGetNextInt;  // made _public_ static so that it's reachable from here.

/**
 * Battle - Containing methods for fight between player and one or more enemies
 */

public class Battle {
	
	private final int ATTACK = 1;
	private final int RUN_AWAY = 2;
	
	private Player player;
	private List<EnemyBaseClass> enemyList;
	private EnemyBaseClass currentEnemy; // for easy access to the current enemy
	
	private String enemyOrEnemies = ""; // used to add "s" if there are many enemies, or "" if there is only one.
	
	public Battle(Player player, List<EnemyBaseClass> enemyList) {
		this.player = player;
		this.enemyList = enemyList;
		// if size bigger than one, "enemies", else "enemy".
		this.enemyOrEnemies = enemyList.size() > 1 ? "enemies" : "enemy";
	}
	
	public void startBattle(){
		System.out.printf("\nBATTLE! You stumbeled upon %d %s. Better watch out!\n", enemyList.size(), enemyOrEnemies);
		
		//TODO: flesh out the battle scenario.  Fight one at a time? Now enemies don't fight back. Make enemy "panic" and move on low health?
		
		for(EnemyBaseClass enemy : enemyList){
			currentEnemy = enemy;
			if(enemy.isAlive()){
				enemy.showAppearanceMessage();
			}
			while(enemy.isAlive()){
				showBattleHealthStatus();
				int response = askForAndGetNextInt("What do you want to do? 1) Attack, 2) Panic, run away! (Random direction) : ", 1, 2);
				if(response == ATTACK){
					player.useWeapon(enemy);
				} else if(response == RUN_AWAY) {
					//TODO: move weapon using ability to a class where both player and enemy can inherit.
					int direction = GameHelperClass.generateRandomNumberWithinSpan(1, 4);
					player.move(direction);
					System.out.println("Phew.. That was close! Hope we're not being followed here.");
					break;
				}
			}
			
		}
	}
	
	private void showBattleHealthStatus(){
		System.out.printf(	"Battle status: %s (%d hp) - %s (%d hp)\n", 
							player.getName(), 
							player.getHealth(),
							currentEnemy.getTypeName(),
							currentEnemy.getHealth()
				);
		
	}
	
	private void updateEnemyOrEnemiesString(){
		enemyOrEnemies = enemyList.size() > 1 ? "enemies" : "enemy";
	}
	
	

}
