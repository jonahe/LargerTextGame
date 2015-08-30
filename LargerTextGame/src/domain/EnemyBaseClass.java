package domain;

import java.awt.Point;

/**
 * Base-class for Enemies. Includes messages for low/high health in battle.
 *
 */

public abstract class EnemyBaseClass extends MoveableObject implements Damageble {

	private static int count; // for id creation
	
	private int id;
	private int health;
	private String appearanceText;
	private String lowHealthText;
	private String highHealthText = "Ha? Is that all you got? Pathetic!";
	private String victoryText = "Hope you like eternal nothingness, because I just KILLED YOU!";
	private boolean alive;
	
	EnemyBaseClass(int initialHealth, String apperanceText, String lowHealthText) {
		super(new Point(0,0), 1);
		this.health = initialHealth;
		this.appearanceText = apperanceText;
		this.lowHealthText = lowHealthText;
		alive = true;
		this.id = count++;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getId(){
		return id;
	}
	
	@Override
	public void updateHealth(int damage) {
		health -= damage;
		showHealthRelatedMessage();

	}
	
	public void showHealthRelatedMessage() {
		// dead
		if(health <= 0) {
			alive = false;
			System.out.println("Ooooww. You.. you KILLED me..");
		}
		else{
			// low health
			if(health <= 10) System.out.println(lowHealthText);
			// high health
			else System.out.println(highHealthText);
			
		}
	}
	
	
	public void showAppearanceMessage() {
		System.out.println(appearanceText);
	}
	
	public void showVictoryMessage(){
		System.out.println(victoryText);
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}
	

}
