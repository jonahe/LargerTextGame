package domain;

import java.awt.Point;

/**
 * Base-class for Enemies. Includes messages for low/high health in battle.
 *
 */

public abstract class EnemyBaseClass extends MovableObject implements Damageble {

	private static int count; // for id creation
	
	private String typeName;
	private int id;
	private int health;
	private String appearanceText;
	private String lowHealthText;
	private String highHealthText = "Ha? Is that all you got? Pathetic!";
	private String victoryText = "Hope you like eternal nothingness, because I just KILLED YOU!";
	private boolean alive;
	
	/**
	 * Enemy constructor
	 * 
	 * @param typeName The type of enemy. eg "Bird", "Wizard" etc.
	 * @param initialHealth Starting health point
	 * @param apperanceText Text to show upon first appearance
	 * @param lowHealthText Text to show upon low health.
	 */
	
	EnemyBaseClass(String typeName, int initialHealth, String apperanceText, String lowHealthText) {
		// send start position and speed to parent class
		super(new Point(0,0), 1);
		this.typeName = typeName;
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
	
	public String getTypeName(){
		return typeName;
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
			System.out.println(typeName + ": " + "Ooooww. You.. you KILLED me..");
		}
		else{
			// low health
			if(health <= 10) System.out.println(typeName + ": " + lowHealthText);
			// high health
			else System.out.println(typeName + ": " + highHealthText);
			
		}
	}
	
	
	public void showAppearanceMessage() {
		System.out.println(typeName + ": " + appearanceText);
	}
	
	public void showVictoryMessage(){
		System.out.println(typeName + ": " + victoryText);
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}
	

}
