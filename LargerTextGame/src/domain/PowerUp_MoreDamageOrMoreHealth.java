package domain;


/**
 * PowerUp that gives Player option to add damage to their attack, or adds to their health
 * @author Erik
 *
 */

public class PowerUp_MoreDamageOrMoreHealth extends PowerUp {
	

	public PowerUp_MoreDamageOrMoreHealth(	Player receiver) {
		super(	"You found a PowerUp! You can choose to 1) Add 10 damage points to your weapon, 2) Add 30 health points to player", 
				1, 
				2);
		// TODO Auto-generated constructor stub
	}


	// This is the only part specific to the child class
	@Override
	public void runChoiceLogic(int choice, Player receiver) {
		if(choice == 1){
			int newHandicap = receiver.getHandicap() + 10;
			receiver.setHandicap(newHandicap);
		} else {
			// choice 2
			int newHealth = receiver.getHealth() + 30;
			receiver.setHealth(newHealth);
		}
	}

}
