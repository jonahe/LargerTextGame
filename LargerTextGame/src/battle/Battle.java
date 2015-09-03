package battle;

import java.util.ArrayList;
import java.util.List;

import app.App;
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
	private List<EnemyBaseClass> killedEnemies;
	
	private String enemyOrEnemies = ""; // used to add "s" if there are many enemies, or "" if there is only one.
	
	public Battle(Player player, List<EnemyBaseClass> enemyList) {
		this.player = player;
		this.enemyList = enemyList;
		// if size bigger than one, "enemies", else "enemy".
		this.enemyOrEnemies = enemyList.size() > 1 ? "enemies" : "enemy";
		// initialize list
		killedEnemies = new ArrayList<EnemyBaseClass>();
	}
	
	public void startBattle(){
		System.out.printf("\nBATTLE! You stumbeled upon %d %s. Better watch out!\n", enemyList.size(), enemyOrEnemies);
		
		//TODO: flesh out the battle scenario.  Fight one at a time? Now enemies don't fight back. Make enemy "panic" and move on low health?
		
		//TODO: use time difference to decide how much damage each attack should be. (adds a skill element). eg. attack as close to 3 seconds as possible?
		
		// battle one enemy at the time
		for(EnemyBaseClass enemy : enemyList){
			currentEnemy = enemy;
			if(enemy.isAlive()){
				enemy.showAppearanceMessage();
			}
			// the fight
			while(enemy.isAlive()){
				showBattleHealthStatus();
				int response = askForAndGetNextInt("What do you want to do? 1) Attack, 2) Panic, run away! (Random direction)", 1, 2);
				if(response == ATTACK){
					player.useWeapon(enemy);
					// if enemy still alive, let enemy attack
					if(enemy.isAlive()){
						enemy.useWeapon(player);
						
						// if player is low on health, but alive - provide option to heal, or quit
						if(player.getHealth() < 13 && player.isAlive()){
							showBattleHealthStatus();
							System.out.println("WARNING: You are dangerously low on health.");
							int answer  = askForAndGetNextInt("What do you want to do? 1) Drink healing potion (adds 25 hp), 2) Gamble!", 1, 2);
							if(answer == 1){
								player.setHealth((player.getHealth()) + 35);
							}
						}
						// if player is dead..
						if(!player.isAlive()){
							enemy.showVictoryMessage();
							System.out.println("OH NOOOO! You DIED!");
							return; // breaks out of the whole method
						}
						
					}
					
				} else if(response == RUN_AWAY) {
					int direction = 0;
					// Guarantee that the random move doesn't move us off the map, or similar non-allowed direction
					while(true){
						direction = GameHelperClass.generateRandomNumberWithinSpan(1, 4);
						if(App.isValidMove(direction)){
							break;
						} // else continue loop until move is OK
					}
					player.move(direction);
					System.out.println("Phew.. That was close! Hope we're not being followed here.");
					break;
				}
			}
			// if enemy is dead (we need to check, because we could have come here by running from enemy too)
			if(!currentEnemy.isAlive()){
				killedEnemies.add(currentEnemy);
			}
			
		}
	}
	
	private void showBattleHealthStatus(){
		System.out.printf(	"Battle status: %s (%d hp) - %s (%d hp)\n", 
							player.getName(), 
							player.getHealth(),
							currentEnemy.getName(),
							currentEnemy.getHealth()
				);
		
	}
	
	
	public List<EnemyBaseClass> getKilledEnemies(){
		return killedEnemies;
	}
	
	

}
