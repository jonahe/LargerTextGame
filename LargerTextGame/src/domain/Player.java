package domain;

import java.awt.Point;

public class Player implements Damageble {
	
	private String name;
	private Weapon weapon;
	private int health;
	private boolean alive;
	private Point position;
	
	public Player(String name, Weapon weapon, int handicap) {
		this.name = name;
		this.weapon = weapon;
		health = 100;
		alive = true;
		position = new Point(0,0);
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
	
	public void setPosition(Point position){
		this.position = position;
	}
	
	public Point getPosition(){
		return position;
	}
	
	public void translatePlayerPosition(int dx, int dy){
		position.translate(dx, dy);
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

