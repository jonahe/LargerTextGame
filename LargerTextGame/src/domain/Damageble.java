package domain;

/*
 * The property of being able to be damaged. All agents that has a health has this
 */
public interface Damageble {

	void updateHealth(int damage);
	boolean isAlive();
	
}
