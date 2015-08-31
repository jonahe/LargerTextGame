package domain;

/*
 * The property of being able to be damaged. All agents that has a health has this
 */
public interface Damageable {

	void updateHealth(int damage);
	boolean isAlive();
	int getHealth();
	void setHealth(int newHealth);
	
}
