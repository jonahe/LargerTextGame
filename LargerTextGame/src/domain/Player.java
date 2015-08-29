package domain;

import java.awt.Point;

/*
 * Basic player. The ability to move is inherrited from MoveableObject
 */

public class Player extends MoveableObject implements Damageble {
	
	private String name;
	private Weapon weapon;
	private int health;
	private boolean alive;
	
	public Player(String name, Weapon weapon, int handicap) {
		super(new Point(0,0), 1);
		this.name = name;
		this.weapon = weapon;
		health = 100;
		alive = true;
		// set handicap
		this.weapon.setHandicap(handicap);
	}
	
	public void useWeapon(Enemy enemy) {
		enemy.updateHealth(weapon.getDamage());
	}
	
	public String getName(){
		return name;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	
	public int getHealth() {
		return health;
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
	
	
}

