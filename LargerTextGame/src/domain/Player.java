package domain;

public class Player implements Damageble {
	
	private String name;
	private Weapon weapon;
	private int health;
	private boolean alive;
	
	public Player(String name, Weapon weapon, int handicap) {
		super();
		this.name = name;
		this.weapon = weapon;
		this.health = 100;
		this.alive = true;
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
	}

	@Override
	public boolean isAlive() {
		return alive;
	}
	
	
}

