package domain;

public enum Weapon {
	
	GUN (1, 10, "Bangbang!"),
	MELEE (2, 8, "StabbStabb!"),
	MAGIC (3, 5, "Abrahcadabrahh!"),
	WEIRDNESS (4, 5, "How can mirrors be real if eyes aren't real!?");
	
	private int id;
	private int damage;
	private int handicap;
	private String sound;
	
	Weapon(int id, int damage, String sound){
		this.id = id;
		this.damage = damage;
		this.sound = sound;
	}
	
	/**
	 * Print the sound of the weapon. eg "Wizard: Stabb stabb!"
	 * @param agentName Name of the one using the weapon
	 */
	public void makeSound(String agentName){
		System.out.println(agentName + " used " + this.toString() + ": " + sound);
	}
	
	public int getId(){
		return id;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public void setHandicap(int handicap){
		this.handicap = handicap;
		// add handicap to weapon damage
		this.damage += handicap;
	}
	
	public int getHandicap(){
		return handicap;
	}
	
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}

}
