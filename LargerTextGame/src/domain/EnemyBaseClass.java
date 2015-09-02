package domain;

import java.awt.Point;
import java.util.Random;

/**
 * Base-class for Enemies. Includes messages for low/high health in battle.
 *
 */

public abstract class EnemyBaseClass extends GameAgent implements Damageable {

	private static int count; // for id creation
	
	private int id;
	private String appearanceText;
	private String lowHealthText;
	private String highHealthText = "Ha? Is that all you got? Pathetic!";
	private String victoryText = "Hope you like eternal nothingness, because I just KILLED YOU!";
	
	/**
	 * Enemy constructor
	 * 
	 * @param typeName The type of enemy. eg "Bird", "Wizard" etc.
	 * @param initialHealth Starting health point
	 * @param apperanceText Text to show upon first appearance
	 * @param lowHealthText Text to show upon low health.
	 */
	
	EnemyBaseClass(String typeName, int initialHealth, Weapon weapon, String apperanceText, String lowHealthText) {
		// send start position and speed to parent class
		super(	new Point(0,0),
				1,
				typeName,
				initialHealth,
				weapon
				);
		this.health = initialHealth;
		this.appearanceText = apperanceText;
		this.lowHealthText = lowHealthText;
		alive = true;
		this.id = count++;
	}
	
	
	public int getId(){
		return id;
	}
	
	// need special implementation because we want damage to fluctuate.
	@Override
	public void useWeapon(GameAgent victim){
		Random randGenerator = new Random();
		double effectiveness = randGenerator.nextDouble();
		int damage = (int) (weapon.getDamage() * effectiveness);
		// minimum damage = 10
		if(damage < 10){
			damage = 10;
		}
		weapon.makeSound(name);
		victim.updateHealth(damage);
	}
	
	
	
	/**
	 * Like parent, but also triggers health related message
	 */
	@Override
	public void updateHealth(int damage) {
		super.updateHealth(damage);
		showHealthRelatedMessage();

	}
	
	public void showHealthRelatedMessage() {
		// dead
		if(health <= 0) {
			alive = false;
			System.out.println(getName() + ": " + "Ooooww. You.. you KILLED me..");
		}
		else{
			// low health
			if(health <= 10) System.out.println(getName() + ": " + lowHealthText);
			// high health
			else System.out.println(getName() + ": " + highHealthText);
			
		}
	}
	
	
	public void showAppearanceMessage() {
		System.out.println(getName() + ": " + appearanceText);
	}
	
	public void showVictoryMessage(){
		System.out.println(getName() + ": " + victoryText);
	}
	

}
