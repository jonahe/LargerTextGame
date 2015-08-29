package domain;

public enum Enemy implements Damageble {

	WIZARD(	1,
			200, 
			"Muhahaha, fear the mighty Wizard! Feel my magic wrath!", 
			"Oh, please. Don't kill me. I'm just an old weak man!"),
	
	TROLL(	2,
			150, 
			"AaAhhahah, me be a trooll. You be scared! Ahhaha!",
			"Ow, ow, ow.. My poor body hurts! Please stop?"
			),
	
	BIRD(	3,
			30,
			"Beep beeep. Beeeeeeeeeep. Annoying, right? Haha!", 
			"Skewwweeek. Don't kill me, I'm just a little defensless bird!"
			)
	;
	
	private int id;
	private int health;
	private String appearanceText;
	private String lowHealthText;
	private String highHealthText = "Ha? Is that all you got? Pathetic!";
	private String victoryText = "Hope you like eternal nothingness, because I just KILLED YOU!";
	private boolean alive;
	
	Enemy(int id, int health, String apperanceText, String lowHealthText) {
		this.health = health;
		this.appearanceText = apperanceText;
		this.lowHealthText = lowHealthText;
		alive = true;
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
	
	
	public void showAppearanceText() {
		System.out.println(appearanceText);
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}
	
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
