package domain;

import java.awt.Point;

/**
 * GameAgent - The basic functionality of both Player and Enemy - like weapon use, being damageable, and ability to move
 */

public class GameAgent extends MovableObject implements Damageable {
	
	protected String name; // name of player, or name of EnemyType (Wizard/Bird etc)
	protected int health;
	protected boolean alive;
	protected Weapon weapon;

	/**
	 * 
	 * @param startPosition
	 * @param speedOfmovement
	 * @param name Name of player, or name of EnemyType (Wizard/Bird etc)
	 * @param initialHealth
	 * @param weapon
	 */
	public GameAgent(Point startPosition, int speedOfmovement, String name, int initialHealth, Weapon weapon) {
		super(startPosition, speedOfmovement);
		this.name = name;
		this.health = initialHealth;
		this.weapon = weapon;
		this.alive = true;
		
	}
	
	
	public String getName(){
		return name;
	}

	
	// weapon related
	
	/**
	 * Use weapon to inflict damage on victim - Also triggers weapon sound.
	 * 
	 * @param victim The GameAgent that weapon will be used ON.
	 */
	public void useWeapon(GameAgent victim) {
		weapon.makeSound(name);
		victim.updateHealth(weapon.getDamage());
	}
	
	public Weapon getWeapon(){
		return weapon;
	}
	
	public void setWeapon(Weapon newWeapon){
		weapon = newWeapon;
	}
	
	
	// Methods from interface Damageable
	
	@Override
	public int getHealth(){
		return health;
	}
	
	@Override
	public void setHealth(int newHealth){
		this.health = newHealth;
	}
	
	@Override
	public void updateHealth(int damage) {
		health -= damage;
		if(health <= 0){
			alive = false;
		}
	}

	@Override
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean isAlive){
		alive = isAlive;
	}

}
