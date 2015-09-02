package domain;

public enum Weapon {
	
	GUN (1, 17, "Bangbang!"),
	MELEE (2, 17, "StabbStabb!"),
	MAGIC (3, 17, "Abrahcadabrahh!"),
	WEIRDNESS (17, 5, "How can mirrors be real if eyes aren't real!?");
	
	private int id;
	private int damage;
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
	
	
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}

}
